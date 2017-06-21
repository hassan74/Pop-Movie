package com.example.android.phase1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Movie;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    GridView gridView ;
    List <movie> moviesList ;
    private movie movieObj ;
    //class that load data from api
    private control _control ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // _control=new control(MainActivity.this) ;
       // moviesList=loadDataFromAPI() ;
        //getMovieList();

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        loadDataFromAPI() ;
    }

    public void loadDataFromAPI() {

        String url = "http://api.themoviedb.org/3/movie/popular?api_key=c2a845daa01463fbf94d9b12347c88e1";

        Ion.with(MainActivity.this)

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
                        List<movie> movies =ParsingUtils.parseResponse(result);
                        MainActivity.this.moviesList = movies ;
                          Log.e("mytag2", moviesList.size()+"hello");
                        setboard();
                    }
                });

           //    return  moviesList ;


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movi_menu, menu);
        return true;
    }
    public void getMovieList()
    {
        moviesList=_control
                .getMoviesList() ;
    }
    public void setboard ()

    {     //   Toast.makeText(this, moviesList.size()+" size ", Toast.LENGTH_SHORT).show();

        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this ,moviesList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(MainActivity.this ,MovieDetail.class) ;
                movieObj =moviesList.get(position) ;
                intent.putExtra("movie",movieObj);
                startActivity(intent);
               //getIntent().getSerializableExtra("") ;
            }
        });
    }
}