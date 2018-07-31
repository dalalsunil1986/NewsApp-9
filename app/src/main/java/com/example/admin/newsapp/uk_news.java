package com.example.admin.newsapp;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class uk_news extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {
    public static final String DATA_URL="https://newsapi.org/v2/everything?domains=mirror.co.uk,economist.com&apiKey=efcb4792515b458290b7ccf3a558633b&language=en";
    public static final int loader_id=0;
    public static ArrayList<News> nlist=new ArrayList<>();
    private NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // Log.d("class","Activity created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uk_news);
//        news task=new newsTask();
//        task.execute();
        ListView listView = (ListView) findViewById(R.id.list_uk);
       // Log.d("class","new adapter created");
        newsAdapter = new NewsAdapter(this, nlist);
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                News current = (News) adapterView.getItemAtPosition(position);
                Uri url = Uri.parse(current.getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW, url);
                startActivity(i);
            }
        });
        getLoaderManager().initLoader(loader_id,null,this);
    }
    @Override
    public Loader<ArrayList<News>> onCreateLoader(int id,Bundle args){
       // Log.d("","create called");
        newsLoader nLoader=new newsLoader(uk_news.this);
        return nLoader;
    }
    public void onLoadFinished(Loader<ArrayList<News>> loader,ArrayList<News> news)
    {
       // Log.d("class","finished called");
        View prog_indi=findViewById(R.id.prog_bar);
        prog_indi.setVisibility(View.GONE);
       newsAdapter.clear();
        if(news !=null && !news.isEmpty())
        {
         //     Log.d("class",""+news.size());
              newsAdapter.clear();
           //   Log.d("class","Adding data");
              newsAdapter.addAll(news);
           // Log.d("class","Added data");

        }
        else
        {
            //Log.d("class","news is null");
        }
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader)
    {
        //Log.d("class","reset called");
        newsAdapter.clear();
    }
//    private void updateUI(ArrayList<News> news)
//    {
//        ListView listView = (ListView) findViewById(R.id.list_uk);
//        NewsAdapter newsAdapter = new NewsAdapter(this, news);
//        listView.setAdapter(newsAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                News current = (News) adapterView.getItemAtPosition(position);
//                Uri url = Uri.parse(current.getUrl());
//                Intent i = new Intent(Intent.ACTION_VIEW, url);
//                startActivity(i);
//            }
//        });
//    }
    public static class newsLoader extends AsyncTaskLoader<ArrayList<News>> {
        public newsLoader(Context context){
            super(context);
        }
        @Override
        protected void onStartLoading(){
          //  Log.d("class","load called");
            forceLoad();
        }
        @Override
        public ArrayList<News> loadInBackground(){
            //Log.d("class","background called");
            URL url=null;
            try {
              url=new URL(DATA_URL);
            } catch (MalformedURLException e) {

            }
            String JSONresponse="";
            try
            {
             JSONresponse=makeHTTPrequest(url);
            }catch (IOException e){}
//            ArrayList<News> news=Query.extractData(JSONresponse);
            nlist=Query.extractData(JSONresponse);
            return  nlist;
        }
//        @Override
//        protected void onPostExecute(ArrayList<News> news)
//        {
//            if (news==null)
//            {
//                return;
//            }
//            updateUI(news);
//        }
        private String makeHTTPrequest(URL url) throws IOException{
            String JSONresponse="";
            HttpURLConnection urlConnection=null;
            InputStream inputStream=null;
            try {
                urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();
                inputStream=urlConnection.getInputStream();
                JSONresponse=read(inputStream);
            }catch (IOException e)
            {}finally {
                if(urlConnection!=null){urlConnection.disconnect();}
                if(inputStream!=null){inputStream.close();}
            }
            return JSONresponse;
        }
        private String read(InputStream input){
            StringBuilder builder=new StringBuilder();
            if(input!=null)
            {
                InputStreamReader i_reader=new InputStreamReader(input, Charset.forName("UTF-8"));
                BufferedReader reader=new BufferedReader(i_reader);
                try {
                    String line=reader.readLine();
                    while (line!=null)
                    {
                        builder.append(line);
                        line=reader.readLine();
                    }
                } catch (IOException e) {}
            }
            return  builder.toString();
        }
    }
}

