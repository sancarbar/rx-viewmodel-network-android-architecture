package com.bogota.gdg.reactivexnetworkarchitecture.network;

import android.util.Log;


import com.bogota.gdg.reactivexnetworkarchitecture.json.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Santiago Carrillo
 */
public class Network
{

    private final TeamsService service;

    public Network()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(
            "https://raw.githubusercontent.com/sancarbar/starting-android-lists/master/" ).
            addConverterFactory(GsonConverterFactory.create() ).build();
        service = retrofit.create( TeamsService.class );
    }

    public List<Team> getTeamsFromUrl( String serverUrl )
    {
        List<Team> teams = new ArrayList<>();
        try
        {
            URL url = new URL( serverUrl );
            InputStreamReader inputStreamReader = new InputStreamReader( url.openStream() );
            BufferedReader bufferedReader = new BufferedReader( inputStreamReader );

            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ( ( line = bufferedReader.readLine() ) != null )
            {
                stringBuilder.append( line );
            }

            inputStreamReader.close();
            String json = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray( json );
            for ( int i = 0; i < jsonArray.length(); i++ )
            {
                JSONObject jsonObject = jsonArray.getJSONObject( i );
                teams.add( new Team( jsonObject ) );
            }

        }
        catch ( Exception e )
        {
            e.printStackTrace();
            Log.e( Network.class.getSimpleName(), "Error loading network data", e.getCause() );
        }
        return teams;
    }

    public List<Team> getTeams()
        throws IOException
    {
        Call<List<Team>> call = service.listTeams();
        Response<List<Team>> response = call.execute();
        return response.body();
    }
}