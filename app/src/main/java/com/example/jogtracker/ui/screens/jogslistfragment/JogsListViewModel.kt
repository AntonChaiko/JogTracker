package com.example.jogtracker.ui.screens.jogslistfragment


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.repository.JogTrackerApiRepositoryImpl
import com.example.domain.models.JogEntity
import com.example.domain.models.JogsEntity
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat


class JogsListViewModel(
    private val repository: JogTrackerApiRepositoryImpl
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var _jogsList: MutableLiveData<List<JogEntity>> = MutableLiveData()
    val jogsList: LiveData<List<JogEntity>>
        get() = _jogsList

    private val _jogUpdateSuccessListener = MutableLiveData<Boolean?>()
    val jogUpdateSuccessListener: LiveData<Boolean?>
        get() = _jogUpdateSuccessListener

    private val _fromDateToDateListener = MutableLiveData<Boolean?>()
    val fromDateToDateListener: LiveData<Boolean?>
        get() = _fromDateToDateListener

    private val _fromDate = MutableLiveData<Long>()
    val fromDate: LiveData<Long>
        get() = _fromDate

    private val _toDate = MutableLiveData<Long>()
    val toDate: LiveData<Long>
        get() = _toDate

//    var liveDataMerger: MediatorLiveData<Long> = MediatorLiveData<Long>()

    fun getJogsList() {
        repository.getAllJogs().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<JogsEntity> {
                override fun onNext(response: JogsEntity) {
                    Log.d("TAG", "onNext: ${response.response.jogs[1].id} ")
                    Log.d("TAG", "onNext: ${response.response.jogs[1].userId} ")
                    _jogsList.value = response.response.jogs
                }

                override fun onError(e: Throwable) {
                    Log.d("TAG", "onError: ${e.message}")
                }

                override fun onSubscribe(d: Disposable) {}
                override fun onComplete() {}
            })
    }

    fun updateCurrentJog(jogEntity: JogEntity) {
        compositeDisposable.add(repository.updateCurrentJog(jogEntity)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { _jogUpdateSuccessListener.value = true },
                { t ->
                    _jogUpdateSuccessListener.value = false

                    t.printStackTrace()
                }
            )
        )

    }

    fun setFromListener() {
        _fromDateToDateListener.value = true
    }

    fun setToListener() {
        _fromDateToDateListener.value = false
    }

    fun dateFormatter(date: String): Long = SimpleDateFormat("dd.MM.yyyy").parse(date).time

    fun setFromDate(fromTime: Long): List<JogEntity>? {
        _fromDate.value = fromTime
        return _jogsList.value?.filter { jog ->
            dateFormatter(jog.date) >= fromTime
        }
    }


    fun setToDate(toTime: Long) {
        _toDate.value = toTime
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}