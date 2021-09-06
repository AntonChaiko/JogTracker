package com.example.domain.models

data class UserEntity(
    val id: String,
    val email: String,
    val phone: String,
    val role: String,
    val firstName: String,
    val lastName: String
)