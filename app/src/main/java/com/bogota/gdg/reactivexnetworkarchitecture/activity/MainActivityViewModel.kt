package com.bogota.gdg.reactivexnetworkarchitecture.activity

import android.arch.lifecycle.ViewModel
import android.content.ContentValues
import android.util.Log
import com.bogota.gdg.reactivexnetworkarchitecture.json.Team
import com.bogota.gdg.reactivexnetworkarchitecture.network.Network
import io.reactivex.observers.DisposableObserver

/**
 * @author Santiago Carrillo
 * 4/16/18.
 */
class MainActivityViewModel : ViewModel() {


    private var network = Network()

    var teams: List<Team>? = null


    fun getTeams(disposableObserver: DisposableObserver<List<Team>>){

        if (teams != null)
            disposableObserver.onNext(teams!!)

        else
            network.getTeams(object : DisposableObserver<List<Team>>() {
                override fun onComplete() {
                    Log.d(ContentValues.TAG, "onComplete()")
                }

                override fun onError(e: Throwable) {
                    Log.e(ContentValues.TAG, "onError()", e)
                }

                override fun onNext(teamList: List<Team>) {
                    teams = teamList
                    disposableObserver.onNext(teamList)
                }
            })
    }

}