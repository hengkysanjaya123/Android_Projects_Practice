package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.myapplication.Helper.BASE_URL
import com.example.myapplication.Helper.TOKEN
import com.example.myapplication.Helper.message
import kotlinx.android.synthetic.main.activity_upload_cat.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class UploadCatActivity : AppCompatActivity() {

    val SELECT_PICTURE = 0
    var byteArray: ByteArray? = null
    var file: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_cat)

        btnBrowse.setOnClickListener {
            val intt = Intent()
            intt.setType("image/*")
            intt.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intt, "Select Picture"), SELECT_PICTURE)
        }

        btnUpload.setOnClickListener {

            val boundary = "===${System.currentTimeMillis()}==="
            object : AsyncTask<String, Int, String>() {

                @SuppressLint("WrongThread")
                override fun doInBackground(vararg params: String): String {
                    val url = URL(params[0])
                    var sb = StringBuilder()
                    val writer: PrintWriter
                    val charset = "UTF-8"
                    val LINE_FEED = "\r\n"
                    val maxBufferSize = 1024 * 1024


                    try {
                        with(url.openConnection() as HttpURLConnection) {
                            requestMethod = "POST"

//                            setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
//                            setRequestProperty("Content-Type", "multipart/form-data;boundary=$boundary")
                            setRequestProperty("x-api-key", TOKEN)

//                            writer = PrintWriter(OutputStreamWriter(outputStream, charset), true)

//                        setRequestProperty("Content-Disposition", "form-data")

//                        val wr = DataOutputStream(outputStream)
//                        wr.writeBytes("file=" + byteArray)


//                            writer.append("--").append(boundary).append(LINE_FEED)
//                            writer.append("Content-Disposition: file;name=\"").append("file")
//                                .append("\";filename=\"").append("image").append("\"").append(LINE_FEED)
//                            writer.append("Content-Type: ").append("image/png").append(LINE_FEED)
//                            writer.append(LINE_FEED)
//                            writer.flush()

//                            val inputStream = FileInputStream(file)
//                            inputStream.copyTo(outputStream, maxBufferSize)

                            val delimiter = "--"
                            val os = outputStream
                            os.write((delimiter + boundary + "rn").toByteArray());
                            os.write(("Content-Disposition: form-data; name=\"abc\"; filename=\"myfilename\"rn").toByteArray());
//                            os.write(("Content-Type: application/octet-streamrn").toByteArray());
                            os.write(("Content-Type : application/x-www-form-urlencoded").toByteArray());

                            os.write(("Content-Transfer-Encoding: binaryrn").toByteArray());
                            os.write("rn".toByteArray());
                            os.write("file:".toByteArray())
                            os.write(byteArray);
                            os.write("\r\n".toByteArray());

                            var reader: BufferedReader? = null
                            val responseCode = responseCode
                            if (responseCode >= HttpURLConnection.HTTP_BAD_REQUEST) {
                                reader = BufferedReader(InputStreamReader(errorStream))
                            } else {
                                reader = BufferedReader(InputStreamReader(inputStream))
                            }

                            reader.forEachLine {
                                sb.append(it)
                            }

                            reader.close()
                        }
                    } catch (ex: Exception) {
                        Log.d("test", ex.toString())
                    }
                    return sb.toString()
                }

                override fun onPostExecute(result: String) {
                    super.onPostExecute(result)
                    message(this@UploadCatActivity, result)
                }
            }.execute("$BASE_URL/v1/images/upload")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImage = data.data
            imageView11.setImageURI(selectedImage)
//            file = File(selectedImage.path)

            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)
//
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bos)
            byteArray = bos.toByteArray()

//            val filePathColumn = { MediaStore.Images.Media.DATA } as Array<String>;
//
//            val cursor = contentResolver.query(selectedImage, filePathColumn, null, null, null)
//            cursor.moveToFirst()
//
//            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
//            val picturePath = cursor.getString(columnIndex)
//
//            cursor.close()
//
//            file = File(picturePath)

//            imageView11.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }
}
