package com.example.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.data.mapper.*
import com.example.data.models.JogsResponse
//import com.example.data.models.JogUpdateResponse
import com.example.data.models.UUIDLogin
import com.example.data.models.UUIDResponse
import com.example.data.remote.api.JogsApi
import com.example.data.utils.SharedPrefsHelper
import com.example.domain.models.FeedbackEntity
import com.example.domain.models.JogEntity
import com.example.domain.models.JogsEntity
import com.example.domain.repositories.JogTrackerApiRepository
import io.reactivex.Completable
import io.reactivex.Observable

class JogTrackerApiRepositoryImpl(
    private val jogsApi: JogsApi,
    private val sharedPrefsHelper: SharedPrefsHelper
) : JogTrackerApiRepository {

    fun loginWithUUID(uuid: String): Observable<UUIDResponse> {
        return jogsApi.loginWithUUID(UUIDLogin(uuid))
    }

    override fun addNewJog(jogEntity: JogEntity): Completable {
        return jogsApi.addNewJog(
            "Bearer ${sharedPrefsHelper.getAccessToken().toString()}",
            jogEntity.toJogRequest()
        )
    }

    override fun getAllJogs(): Observable<JogsEntity> {
        return jogsApi.getJogs("Bearer ${sharedPrefsHelper.getAccessToken().toString()}").map {
            it.toJogsEntity()
        }
    }
    override fun updateCurrentJog(jogEntity: JogEntity) : Completable {
        return jogsApi.updateJog(
            "Bearer ${sharedPrefsHelper.getAccessToken().toString()}",
            jogEntity.toJogUpdateRequest()
        )
    }

    override fun sendFeedback(feedbackEntity: FeedbackEntity): Completable {
        return jogsApi.sendFeedback(
            "Bearer ${sharedPrefsHelper.getAccessToken().toString()}",
            feedbackEntity.toFeedbackRequest()
        )
    }

}