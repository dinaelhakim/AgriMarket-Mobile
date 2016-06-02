package com.example.lenovo.testslidenerd;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import webservice_connection_volley.UniversalImageLoaderConfiguration;

public class GenericAdapter extends BaseAdapter {
    Context context;
    String[] title;
    String[] subTitle=null;
    ArrayList<String> titlesList;
    ArrayList<String> subTitlesList;


//    int[] images;
    Boolean visible=true;

    //--------------------
    ArrayList<String> imagesUrl;
    //------------------


    public GenericAdapter(Context context,String[] title,String[] subTitle,int[] images) {
        this.title=title;
        this.subTitle=subTitle;
//        this.images=images;
        this.context=context;
    }

    public GenericAdapter(Context context, ArrayList<String> titlesList,ArrayList<String> imagesUrl) {          //used @MainActivity
        this.context = context;
        this.titlesList = titlesList;
        this.imagesUrl= imagesUrl;
        this.visible=false;
    }
    public GenericAdapter(Context context, String[] title,int[] images) {
        this.context = context;
        this.title = title;
//        this.images = images;
        this.visible=false;
    }

    @Override
    public int getCount() {
        return titlesList.size();
    }

    @Override
    public Object getItem(int position) {
        return titlesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.custom_row,parent,false);
        }else {
            row=convertView;
        }
        ImageView imageView1=(ImageView)row.findViewById(R.id.imageView1);
        TextView textView1=(TextView)row.findViewById(R.id.textView1);
        TextView textView2=(TextView)row.findViewById(R.id.textView2);

        UniversalImageLoaderConfiguration.setConfig(context);
        ImageLoader.getInstance().displayImage(imagesUrl.get(position),  imageView1);


        textView1.setText(titlesList.get(position));
        if(visible){
            textView2.setText(subTitle[position]);
        }else {
            textView2.setVisibility(View.INVISIBLE);
        }
        ImageView image2=(ImageView)row.findViewById(R.id.voice);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mPlayer = MediaPlayer.create(context, R.raw.hello);
                mPlayer.start();
            }
        });
        return row;
    }
}
