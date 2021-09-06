package com.example.domain.repositories

import com.example.domain.models.FeedbackEntity
import com.example.domain.models.JogEntity
import com.example.domain.models.JogsEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface JogTrackerApiRepository {
//    fun loginWithUUID(): Observable<UUIDDomain>
    fun addNewJog(jogEntity: JogEntity) : Completable
    fun getAllJogs() : Observable<JogsEntity>
    fun updateCurrentJog(jogEntity:JogEntity) : Completable
    fun sendFeedback(feedbackEntity: FeedbackEntity): Completable

}