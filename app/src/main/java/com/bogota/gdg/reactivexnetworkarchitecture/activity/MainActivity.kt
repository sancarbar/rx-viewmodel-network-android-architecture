package com.bogota.gdg.reactivexnetworkarchitecture.activity

import android.arch.lifecycle.ViewModelProviders
import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.bogota.gdg.reactivexnetworkarchitecture.R
import com.bogota.gdg.reactivexnetworkarchitecture.adapter.TeamsAdapter
import com.bogota.gdg.reactivexnetworkarchitecture.json.Team
import io.reactivex.observers.DisposableObserver


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var mainActivityViewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


        configureRecyclerView()
        loadTeams()

    }

    private fun loadTeams() {
        mainActivityViewModel.getTeams((object : DisposableObserver<List<Team>>() {
                override fun onComplete() {
                    Log.d(ContentValues.TAG, "onComplete()")
                }

                override fun onError(e: Throwable) {
                    Log.e(ContentValues.TAG, "onError()", e)
                }

                override fun onNext(teams: List<Team>) {
                    recyclerView.adapter = TeamsAdapter(teams)
                }
            }))
    }

    private fun configureRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }


}
