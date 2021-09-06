package com.example.data.mapper

import com.example.data.models.*
import com.example.data.utils.toDateTime
import com.example.domain.models.*


fun JogEntity.toJogRequest() =
    JogRequest(
        distance = distance.toFloat(),
        date = date,
        time = time
    )

fun JogResponse.toJogEntity(): JogEntity =
    JogEntity(
        id = id,
        userId = userId,
        distance = distance.toDouble(),
        time = time,
        date = (date.toLong() * 1000).toDateTime()
    )

fun JogEntity.toJogUpdateRequest(): JogUpdateRequest =
    JogUpdateRequest(userId, id, distance.toFloat(), time, date)

fun AllJogsResponse.toAllJogsEntity(): AllJogsEntity =
    AllJogsEntity(jogs = this.jogs.map {
        it.toJogEntity()
    })


fun JogsResponse.toJogsEntity(): JogsEntity =
    JogsEntity(response.toAllJogsEntity())


fun FeedbackEntity.toFeedbackRequest(): FeedbackRequest =
    FeedbackRequest(text = text, topicId = topicId)


fun UserResponse.toUserEntity(): UserEntity =
    UserEntity(
        id,
        email,
        phone,
        role,
        firstName,
        lastName
    )

fun UserEntity.toUserResponse(): UserResponse =
    UserResponse(
        id,
        email,
        phone,
        role,
        firstName,
        lastName
    )


fun UUIDEntity.toUUID(): UUID =
    UUID(accessToken, tokenType, userResponse.toUserResponse())


fun UUID.toUUID(): UUIDEntity =
    UUIDEntity(accessToken, tokenType, userResponse.toUserEntity())


fun UUIDResponse.toUUDDomain(): UUIDDomain =
    UUIDDomain(response.toUUID())