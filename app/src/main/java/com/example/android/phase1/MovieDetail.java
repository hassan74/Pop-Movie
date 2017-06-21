package com.example.android.phase1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by mohamed on 23/08/2016.
 */
public class MovieDetail extends Activity {
    private TextView title  ,overView ,date ,vote;
    private ImageView backdrop ;
    private movie mov ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        mov =(movie)getIntent().getSerializableExtra("movie") ;


        title = (TextView) findViewById(R.id.tit) ;
        overView= (TextView)findViewById(R.id.overview);
        date= (TextView)findViewById(R.id.date);
        backdrop =(ImageView)findViewById(R.id.backdrop) ;
        vote =(TextView)findViewById(R.id.vote) ;




        title.setText(mov.getTitle());
        overView.setText(mov.getOverview());
        date.setText(mov.getRelease_date());
        String url ="http://image.tmdb.org/t/p/w185/"+mov.getImageUrl() ;
        Picasso.with(this).load(url).into(backdrop) ;
        vote.setText(mov.getVote_average()+"/10");


    }
}
