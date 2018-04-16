package com.bogota.gdg.reactivexnetworkarchitecture.network

import com.bogota.gdg.reactivexnetworkarchitecture.json.Team
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author Santiago Carrillo.
 */

interface TeamsService {
    @GET("teams.json")
    fun listTeams(): Call<List<Team>>
}
