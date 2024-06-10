package com.example.barbershop_00

data class Master(
    val name: String,
    val workDays: Map<String, Pair<String, String>>,
    val photoRes: Int
)
