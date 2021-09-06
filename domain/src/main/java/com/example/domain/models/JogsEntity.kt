package com.example.domain.models

data class JogEntity(
    val id: Int,
    val userId: String,
    val distance: Double,
    val time: Int,
    val date: String
)

data class AllJogsEntity(
    val jogs: List<JogEntity>
)

data class JogsEntity(
    val response: AllJogsEntity
)

