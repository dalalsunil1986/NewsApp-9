package com.example.admin.newsapp;

import android.graphics.Bitmap;

/**
 * Created by Admin on 09-04-2018.
 */

public class News {
    private String mtitle;
    private String murl;
    private String mdes;
   // private Bitmap mBMP;
    private String mimage;
    public News(String title,String url,String des/*,Bitmap bmp*/,String image){
        mtitle=title;
        murl=url;
        mdes=des;
        mimage=image;
       // mBMP=bmp;
    }
    public String getTitle(){return mtitle;}
    public String getUrl(){return murl;}
    public String getDes(){return mdes;}
    public String getImg(){return mimage;}
    //public Bitmap getBMP(){return mBMP;}
}
