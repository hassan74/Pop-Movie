package com.example.android.phase1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed on 7/25/2016.
 */
public class ParsingUtils
{
    /* constants */
    public static final String LOG_TAG = ParsingUtils.class.getSimpleName();

    /**
     * parses a card from a json string
     *
     * @return null if the format of jsonString is wrong
     */
    public static movie parseMovie(String jsonString)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(jsonString);

            movie mov = new movie();

            mov.setTitle(jsonObject.getString("title"));
            mov.setOverview(jsonObject.getString("overview"));
            mov.setImageUrl(jsonObject.getString("poster_path"));
            mov.setBackdrop_path(jsonObject.getString("backdrop_path"));
            mov.setRelease_date(jsonObject.getString("release_date"));
            mov.setPopularity(jsonObject.getDouble("popularity"));
            mov.setVote_count(jsonObject.getInt("vote_count"));
            mov.setVote_average(jsonObject.getDouble("vote_average"));


            return mov;
        } catch (JSONException e)
        {
            Log.e(LOG_TAG, "error in parseMov() " + e.getMessage());
            return null;
        }
    }

    /**
     * parses a list of cards from the API response
     *
     * @return null if the format of the resposne is wrong
     */
    public static List<movie> parseResponse(String responseString)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(responseString);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            List<movie> moviesList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++)
                moviesList.add(parseMovie(jsonArray.getJSONObject(i).toString()));
            return moviesList;
        } catch (JSONException e)
        {
            Log.e(LOG_TAG, "error in parseResponse() " + e.getMessage());
            return null;
        }
    }
}
