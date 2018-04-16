package com.bogota.gdg.reactivexnetworkarchitecture.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bogota.gdg.reactivexnetworkarchitecture.R;
import com.bogota.gdg.reactivexnetworkarchitecture.adapter.TeamsAdapter;
import com.bogota.gdg.reactivexnetworkarchitecture.json.Team;
import com.bogota.gdg.reactivexnetworkarchitecture.network.Network;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity
    extends Activity
{

    ExecutorService executorService = Executors.newFixedThreadPool( 1 );

    Network network = new Network();

    private List<Team> teams;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        configureRecyclerView();
        executorService.execute( new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    teams = network.getTeams();
                    runOnUiThread( new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            recyclerView.setAdapter( new TeamsAdapter( teams ) );
                        }
                    } );
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
            }
        } );


    }

    private void configureRecyclerView()
    {
        recyclerView = findViewById( R.id.recyclerView );
        recyclerView.setHasFixedSize( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );
    }


}
