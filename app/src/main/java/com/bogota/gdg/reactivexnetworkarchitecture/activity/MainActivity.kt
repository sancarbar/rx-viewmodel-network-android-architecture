package com.bogota.gdg.reactivexnetworkarchitecture.activity

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.bogota.gdg.reactivexnetworkarchitecture.R
import com.bogota.gdg.reactivexnetworkarchitecture.adapter.TeamsAdapter
import com.bogota.gdg.reactivexnetworkarchitecture.json.Team
import com.bogota.gdg.reactivexnetworkarchitecture.network.Network
import io.reactivex.observers.DisposableObserver

import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : Activity() {

    private var executorService = Executors.newFixedThreadPool(1)

    private var network = Network()

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureRecyclerView()
        executorService.execute {
            try {
                network.getTeams(object : DisposableObserver<List<Team>>() {
                    override fun onComplete() {
                        Log.d(TAG, "onComplete()")
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "onError()", e)
                    }

                    override fun onNext(teams: List<Team>) {
                        recyclerView.adapter = TeamsAdapter(teams)
                    }
                })
            } catch (e: IOException) {
                Log.e(TAG, "onError()", e)
            }
        }
    }

    private fun configureRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }


}
