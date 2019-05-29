package com.example.myapplication.Models

data class Product(
    val id: Int,
    val name: String,
    val id_customer: String,
    val price_customer: Int,
    val price_sales: Int
)