package com.bogota.gdg.reactivexnetworkarchitecture.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.bogota.gdg.reactivexnetworkarchitecture.R
import com.bogota.gdg.reactivexnetworkarchitecture.adapter.TeamsAdapter
import com.bogota.gdg.reactivexnetworkarchitecture.json.Team
import com.bogota.gdg.reactivexnetworkarchitecture.network.Network

import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : Activity() {

    private var executorService = Executors.newFixedThreadPool(1)

    private var network = Network()

    private lateinit var teams: List<Team>

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureRecyclerView()
        executorService.execute {
            try {
                teams = network.teams
                runOnUiThread { recyclerView.adapter = TeamsAdapter(teams) }
            } catch (e: IOException) {

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
