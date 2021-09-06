package com.example.data.remote.api

import com.example.data.models.*
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*

interface JogsApi {

    @POST("v1/auth/uuidLogin")
    fun loginWithUUID(
        @Body uuidLogin: UUIDLogin
    ): Observable<UUIDResponse>

    @POST("v1/data/jog")
    fun addNewJog(
        @Header("Authorization") token: String,
        @Body jogRequest: JogRequest
    ): Completable

    @GET("v1/data/sync")
    fun getJogs(
        @Header("Authorization") token: String,
    ): Observable<JogsResponse>

    @PUT("v1/data/jog")
    fun updateJog(
        @Header("Authorization") token: String,
        @Body jogUpdateRequest: JogUpdateRequest
    ): Completable

    @POST("v1/feedback/send")
    fun sendFeedback(
        @Header("Authorization") token: String,
        @Body feedbackRequest: FeedbackRequest
    ) : Completable


}

