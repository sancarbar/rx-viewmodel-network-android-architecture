package com.bogota.gdg.reactivexnetworkarchitecture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bogota.gdg.reactivexnetworkarchitecture.R;
import com.bogota.gdg.reactivexnetworkarchitecture.json.Team;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Santiago Carrillo
 */
public class TeamsAdapter
    extends RecyclerView.Adapter<TeamsAdapter.ViewHolder>
{
    private final List<Team> teams;

    private Context context;

    public TeamsAdapter( List<Team> teams )
    {
        this.teams = teams;
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i )
    {
        context = viewGroup.getContext();
        View view = LayoutInflater.from( context ).inflate( R.layout.team_row, viewGroup, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int position )
    {
        Team team = teams.get( position );
        viewHolder.name.setText( team.getName() );
        viewHolder.shortName.setText( team.getShortName() );
        Picasso.with( context ).load( team.getImageUrl() ).into( viewHolder.logo );
    }

    @Override
    public int getItemCount()
    {
        return teams.size();
    }

    static class ViewHolder
        extends RecyclerView.ViewHolder
    {

        TextView name;

        TextView shortName;

        ImageView logo;

        ViewHolder( View view )
        {
            super( view );
            name =  view.findViewById( R.id.name );
            shortName =  view.findViewById( R.id.shortName );
            logo = view.findViewById( R.id.logo );
        }
    }
}
