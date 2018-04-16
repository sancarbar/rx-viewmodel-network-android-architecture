package com.bogota.gdg.reactivexnetworkarchitecture.json;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Santiago Carrillo
 */
public class Team
{
    private int id;

    private String name;

    private String shortName;

    private String imageUrl;

    public Team()
    {
    }

    public Team( JSONObject jsonObject )
    {
        try
        {
            id = jsonObject.getInt( "id" );
            name = jsonObject.getString( "name" );
            shortName = jsonObject.getString( "shortName" );
            imageUrl = jsonObject.getString( "imageUrl" );
        }
        catch ( JSONException e )
        {
            Log.e( Team.class.getSimpleName(), "Error creating object from jsonObject: " + jsonObject, e );
        }
    }

    public String getName()
    {
        return name;
    }

    public String getShortName()
    {
        return shortName;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
}
