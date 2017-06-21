package com.example.android.phase1;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohamed on 13/08/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private static LayoutInflater inflater=null;
    List<movie> mov =new ArrayList<movie>() ;
    public ImageAdapter(Context c  ,List<movie> mov) {
        mContext = c;
        this.mov=mov ;
    }


    @Override
    public int getCount() {
        return mov.size();
    }

    @Override
    public Object getItem(int position) {

        return mov.get(position);

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // to return view (grid view)
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = mInflater.inflate(R.layout.grid_item, null);
        final ImageView movimg = (ImageView) convertView.findViewById(R.id.idImg);
        TextView textView =(TextView) convertView.findViewById(R.id.tx1);
        textView.setText((CharSequence) mov.get(position).getTitle());
        String url ="http://image.tmdb.org/t/p/w185/"+mov.get(position).getImageUrl();
        Picasso.with(mContext).load(url).into(movimg);
        return convertView;
}
}
