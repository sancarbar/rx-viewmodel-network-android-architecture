package com.bogota.gdg.reactivexnetworkarchitecture.network

import android.util.Log


import com.bogota.gdg.reactivexnetworkarchitecture.json.Team
import org.json.JSONArray
import org.json.JSONObject

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.util.ArrayList

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Santiago Carrillo
 */
class Network {

    private val service: TeamsService

    val teams: List<Team>
        @Throws(IOException::class)
        get() {
            val call = service.listTeams()
            val response = call.execute()
            return response.body()
        }

    init {
        val retrofit = Retrofit.Builder().baseUrl(
                "https://raw.githubusercontent.com/sancarbar/starting-android-lists/master/").addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(TeamsService::class.java)
    }

    fun getTeamsFromUrl(serverUrl: String): List<Team> {
        val teams = ArrayList<Team>()
        try {
            val url = URL(serverUrl)
            val inputStreamReader = InputStreamReader(url.openStream())
            val bufferedReader = BufferedReader(inputStreamReader)

            var line: String
            val stringBuilder = StringBuilder()
            line = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = bufferedReader.readLine()
            }

            inputStreamReader.close()
            val json = stringBuilder.toString()
            val jsonArray = JSONArray(json)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                teams.add(Team(jsonObject))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(Network::class.java.simpleName, "Error loading network data", e.cause)
        }

        return teams
    }
}