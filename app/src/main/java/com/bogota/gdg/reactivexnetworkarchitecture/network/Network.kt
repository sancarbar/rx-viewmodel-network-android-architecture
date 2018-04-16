package com.bogota.gdg.reactivexnetworkarchitecture.network


import android.content.ContentValues.TAG
import com.bogota.gdg.reactivexnetworkarchitecture.json.Team
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import android.os.SystemClock
import android.util.Log
import io.reactivex.ObservableSource
import java.util.concurrent.Callable
import io.reactivex.observers.DisposableObserver


/**
 * @author Santiago Carrillo
 */
class Network {

    private val service: TeamsService

    private val disposables = CompositeDisposable()

    init {
        val retrofit = Retrofit.Builder().baseUrl(
                "https://raw.githubusercontent.com/sancarbar/starting-android-lists/master/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(TeamsService::class.java)
    }

    fun getTeams(disposableObserver: DisposableObserver<List<Team>>) {
        disposables.add(teamsObservable()

                // Run on a background thread
                .subscribeOn(Schedulers.io())

                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver))
    }


    private fun teamsObservable(): Observable<List<Team>> {
        return Observable.defer {
            val call = service.listTeams()
            val response = call.execute()
            Observable.just(response.body())
        }
    }

}