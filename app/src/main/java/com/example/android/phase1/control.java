package com.example.android.phase1;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 13/09/2016.
 */
public class control {
    private List<movie> moviesList =new ArrayList<movie>();
    Context context ;
    public control(Context context)
    {
        this.context = context;
      //  loadDataFromAPI();

    }
    //return the data
    public List<movie> getMoviesList() {
        return moviesList;
    }

    //load data from api
    public List<movie>  loadDataFromAPI() {

        String url = "http://api.themoviedb.org/3/movie/popular?api_key=c2a845daa01463fbf94d9b12347c88e1";

        Ion.with(context)

                .load("GET", url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        // stop the swipe to refresh
                        // swipeRefreshLayout.setRefreshing(false);

                        // check error
                        if (e != null) {
                            return;
                        }
                        Log.e("mytag", result);
                        // parse the data
                        moviesList = ParsingUtils.parseResponse(result);
                      //  Log.e("mytag2", moviesList.size()+"hello");


                    }
                });

        return  moviesList ;
    }
}
