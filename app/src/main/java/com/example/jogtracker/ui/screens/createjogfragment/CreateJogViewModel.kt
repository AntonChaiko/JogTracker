package com.example.jogtracker.ui.screens.createjogfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repository.JogTrackerApiRepositoryImpl
import com.example.domain.models.JogEntity
import com.google.android.play.core.internal.t
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer

class CreateJogViewModel(private val repositoryImpl: JogTrackerApiRepositoryImpl) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _isCreateJogSuccess = MutableLiveData<Boolean?>()
    val isCreateJogSuccess: LiveData<Boolean?> get() = _isCreateJogSuccess

    fun addNewJog(jogEntity: JogEntity) {
        compositeDisposable.add(repositoryImpl.addNewJog(jogEntity)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    successWhenCreateJog()
                },
                {
                    failWhenCreateJog()
                }
            )
        )
    }

     fun createJogEntity(
        id: Int,
        userId: String,
        distance: Double,
        time: Int,
        date: String
    ): JogEntity = JogEntity(id,userId,distance, time, date)

    private fun failWhenCreateJog() {
        _isCreateJogSuccess.value = false
    }

    private fun successWhenCreateJog() {
        _isCreateJogSuccess.value = true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}