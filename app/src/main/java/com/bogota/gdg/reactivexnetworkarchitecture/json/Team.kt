package com.bogota.gdg.reactivexnetworkarchitecture.json

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

/**
 * @author Santiago Carrillo
 */
class Team {
    private var id: Int = 0

    var name: String? = null
        private set

    var shortName: String? = null
        private set

    var imageUrl: String? = null
        private set

    constructor() {}

    constructor(jsonObject: JSONObject) {
        try {
            id = jsonObject.getInt("id")
            name = jsonObject.getString("name")
            shortName = jsonObject.getString("shortName")
            imageUrl = jsonObject.getString("imageUrl")
        } catch (e: JSONException) {
            Log.e(Team::class.java.simpleName, "Error creating object from jsonObject: $jsonObject", e)
        }

    }
}
