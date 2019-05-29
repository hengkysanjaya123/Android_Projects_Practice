package com.example.myapplication.Models

import java.io.Serializable

data class Product(val id : String, val name : String, val price : String, val stock : String) : Serializable{}
