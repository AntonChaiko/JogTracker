package com.example.jogtracker.ui.screens.feedbackfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repository.JogTrackerApiRepositoryImpl
import com.example.domain.models.FeedbackEntity
import com.example.domain.models.JogEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FeedbackViewModel(private val repository: JogTrackerApiRepositoryImpl) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _isFeedbackSend = MutableLiveData<Boolean?>()
    val isFeedbackSend: LiveData<Boolean?> get() = _isFeedbackSend

    fun sendFeedback(topicId: Int, text: String) {
        val feedbackEntity = FeedbackEntity(
            text,
            topicId
        )
        compositeDisposable.add(repository.sendFeedback(feedbackEntity)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    feedbackSend()
                },
                {
                    feedbackFailed()
                }
            )
        )
    }

    private fun feedbackSend() {
        _isFeedbackSend.value = true
    }

    private fun feedbackFailed() {
        _isFeedbackSend.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

    }
}