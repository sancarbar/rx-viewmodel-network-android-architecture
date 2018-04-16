package com.bogota.gdg.reactivexnetworkarchitecture.network;

import com.bogota.gdg.reactivexnetworkarchitecture.json.Team;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

/**
 * @author Santiago Carrillo.
 */

public interface TeamsService
{
    @GET( "teams.json" )
    Call<List<Team>> listTeams();
}
