package com.example.admin.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

 //   public static final String DATA_URL="https://newsapi.org/v2/everything?domains=thehindu.com,timesofindia.indiatimes.com.com&apiKey=efcb4792515b458290b7ccf3a558633b&language=en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showInNews(View view){
        Intent i=new Intent(this,indian_news.class);
        startActivity(i);
    }
    public void showUSNews(View view){
        Intent i=new Intent(this,us_news.class);
        startActivity(i);
    }
    public void showUKNews(View view){
        Intent i=new Intent(this,uk_news.class);
        startActivity(i);
    }
}
