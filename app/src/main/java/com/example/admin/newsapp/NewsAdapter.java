package com.example.admin.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 09-04-2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, List<News> news)
    {
        super(context,0,news);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listView=convertView;
        if(listView==null)
        {
            listView= LayoutInflater.from(getContext()).inflate(R.layout.news_layout,parent,false);
        }
        News current=getItem(position);
        TextView title=(TextView) listView.findViewById(R.id.title);
        TextView d=(TextView) listView.findViewById(R.id.title2);
        ImageView im=(ImageView) listView.findViewById(R.id.image);
        title.setText(current.getTitle());
        d.setText(current.getDes());
        Picasso.with(getContext()).load(current.getImg()).into(im);
        //im.setImageBitmap(current.getBMP());
        return listView;
    }
}
