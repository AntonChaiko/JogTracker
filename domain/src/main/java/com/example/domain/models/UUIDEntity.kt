package com.example.domain.models


data class UUIDEntity(
    val accessToken: String,
    val tokenType: String,
    val userResponse: UserEntity
)
data class UUIDDomain(val response:UUIDEntity)