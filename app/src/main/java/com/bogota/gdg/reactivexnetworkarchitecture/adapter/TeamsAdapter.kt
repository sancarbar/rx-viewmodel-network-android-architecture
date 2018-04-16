package com.bogota.gdg.reactivexnetworkarchitecture.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bogota.gdg.reactivexnetworkarchitecture.R
import com.bogota.gdg.reactivexnetworkarchitecture.json.Team
import com.squareup.picasso.Picasso

/**
 * @author Santiago Carrillo
 */
class TeamsAdapter(private val teams: List<Team>) : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        context = viewGroup.context
        val view = LayoutInflater.from(context).inflate(R.layout.team_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val team = teams[position]
        viewHolder.name.text = team.name
        viewHolder.shortName.text = team.shortName
        Picasso.with(context).load(team.imageUrl).into(viewHolder.logo)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.name)

        var shortName: TextView = view.findViewById(R.id.shortName)

        var logo: ImageView = view.findViewById(R.id.logo)

    }
}
