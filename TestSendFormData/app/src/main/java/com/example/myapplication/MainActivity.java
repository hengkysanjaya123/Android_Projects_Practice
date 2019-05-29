package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Helper().execute();
            }
        });
    }

    class Helper extends AsyncTask<String, Integer, String> {

        void createfile() {
            final String TESTSTRING = new String("Hello Android");

            /* We have to use the openFileOutput()-method
             * the ActivityContext provides, to
             * protect your file from others and
             * This is done for security-reasons.
             * We chose MODE_WORLD_READABLE, because
             *  we have nothing to hide in our file */
            FileOutputStream fOut = null;
            try {
                fOut = openFileOutput("samplefile.txt",
                        MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            // Write the string to the file
            try {
                osw.write(TESTSTRING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            /* ensure that everything is
             * really written out and close */
            try {
                osw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                // Connect to the web server endpoint
                URL serverUrl =
                        new URL("http://10.0.2.2:8000/upload");
                HttpURLConnection urlConnection = (HttpURLConnection) serverUrl.openConnection();

                String boundaryString = "----SomeRandomText";

                String fileUrl = "samplefile.txt";
                File logFileToUpload = new File(fileUrl);

// Indicate that we want to write to the HTTP request body
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);


                OutputStream outputStreamToRequestBody = urlConnection.getOutputStream();
                BufferedWriter httpRequestBodyWriter =
                        new BufferedWriter(new OutputStreamWriter(outputStreamToRequestBody));

// Include value from the myFileDescription text area in the post data
                httpRequestBodyWriter.write("\n\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data; name=\"file\"");
                httpRequestBodyWriter.write("\n\n");
                httpRequestBodyWriter.write("Log file for 20150208");

// Include the section to describe the file
                httpRequestBodyWriter.write("\n--" + boundaryString + "\n");
                httpRequestBodyWriter.write("Content-Disposition: form-data;"
                        + "name=\"file\";"
                        + "filename=\"" + logFileToUpload.getName() + "\""
                        + "\nContent-Type: text/plain\n\n");
                httpRequestBodyWriter.flush();

// Write the actual file contents
                FileInputStream inputStreamToLogFile = new FileInputStream(logFileToUpload);

                int bytesRead;
                byte[] dataBuffer = new byte[1024];
                while ((bytesRead = inputStreamToLogFile.read(dataBuffer)) != -1) {
                    outputStreamToRequestBody.write(dataBuffer, 0, bytesRead);
                }

                outputStreamToRequestBody.flush();

// Mark the end of the multipart http request
                httpRequestBodyWriter.write("\n--" + boundaryString + "--\n");
                httpRequestBodyWriter.flush();

// Close the streams
                outputStreamToRequestBody.close();
                httpRequestBodyWriter.close();

                Log.d("test", urlConnection.getResponseMessage());
            } catch (Exception ex) {
                Log.d("test", ex.toString());
            }

            return "still empty";
        }
    }
}
