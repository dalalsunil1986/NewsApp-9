package com.example.admin.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 09-04-2018.
 */

public class Query {
    private Query(){}

    public static ArrayList<News> extractData(String data){
        ArrayList<News> news=new ArrayList<>();
        try {
            JSONObject base=new JSONObject(data);
            JSONArray docs=base.getJSONArray("articles");
            for (int i=0;i<docs.length();i++)
            {
                JSONObject temp=docs.getJSONObject(i);
                String hl=temp.getString("title");
                String url=temp.getString("url");
                String des=temp.getString("description");
                String iu=temp.getString("urlToImage");
               // Log.d("class",""+iu);
                String du="https://www.jainsusa.com/images/store/landscape/not-available.jpg";
//                URL default_image_url=new URL("https://www.jainsusa.com/images/store/landscape/not-available.jpg");
//                Bitmap default_image=BitmapFactory.decodeStream(default_image_url.openConnection().getInputStream());
//                //Bitmap default_image= decodeSampledBitmapFromResource(default_image_url.openConnection().getInputStream(),100,100);
//                Bitmap bmp=default_image;
//
//                try{
//                URL image_url=new URL(iu);
//                bmp= BitmapFactory.decodeStream(image_url.openConnection().getInputStream());
//                //    bmp=decodeSampledBitmapFromResource(image_url.openConnection().getInputStream(),100,100);
//                }catch (MalformedURLException e){ news.add(new News(hl, url, des/*, bmp*/));}catch (IOException e){ news.add(new News(hl, url, des/*, bmp*/));}
//                news.add(new News(hl, url, des/*, bmp*/));
                if(iu=="null")
                {
                  //  Log.d("","image url is null");
                    iu=du;
                    //Log.d("class",""+iu);
                }
                news.add(new News(hl,url,des,iu));
            }

        }catch (JSONException e) {}
//        catch (MalformedURLException e) {} catch (IOException e) {}
        return news;
    }
}
