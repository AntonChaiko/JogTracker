package com.example.jogtracker.ui.screens.loginfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.models.UUIDResponse
import com.example.data.repository.JogTrackerApiRepositoryImpl
import com.example.data.utils.SharedPrefsHelper
import com.example.domain.models.UUIDDomain
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(
    private val repositoryImpl: JogTrackerApiRepositoryImpl,
    private val sharedPrefsHelper: SharedPrefsHelper
) : ViewModel() {

    private val _loginSuccessListener = MutableLiveData<Boolean?>()
    val loginSuccessListener : LiveData<Boolean?> get() = _loginSuccessListener

    fun makeCall(uuid: String) {
        repositoryImpl.loginWithUUID(uuid).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<UUIDResponse> {
                override fun onNext(response: UUIDResponse) {
                    sharedPrefsHelper.saveAccessToken(response.response.accessToken)
                    _loginSuccessListener.value = true
                    Log.d("TAG", "onNext: ${response.response.accessToken}")
                }

                override fun onError(e: Throwable) {
                    _loginSuccessListener.value = false
                    Log.d("TAG", "onError: ${e.message}")
                }

                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}
            })
    }


}