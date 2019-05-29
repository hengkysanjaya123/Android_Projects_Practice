<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUsersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('users', function (Blueprint $table) {
            $table->uuid('id')->primary();
            $table->uuid('role_id');
            $table->string('username')->unique();
            $table->string('name');
            $table->string('password');
            $table->string('email')->nullable();
            $table->string('phone')->nullable();
            $table->string('gender')->nullable();
            $table->rememberToken();
            $table->timestamps();

            $table->foreign('role_id')->on('roles')->references('id');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('users');
    }
}
