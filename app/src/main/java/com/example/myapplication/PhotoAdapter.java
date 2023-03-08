package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PhotoAdapter extends BaseAdapter {
    private ArrayList<Photo> photo_list;
    private Context context;

    public PhotoAdapter(ArrayList<Photo> photo_list, Context context) {
        this.photo_list = photo_list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return photo_list.size();
    }

    @Override
    public Object getItem(int i) {
        return photo_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return photo_list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final MyView dataitem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null){
            dataitem = new MyView();
            view = inflater.inflate(R.layout.photo_disp_tbl,null);
            dataitem.iv_photo = view.findViewById(R.id.iv_photo);
            dataitem.tv_title = view.findViewById(R.id.tv_title);
            view.setTag(dataitem);
        }else{
            dataitem = (MyView) view.getTag();
        }
        Picasso.get().load(photo_list.get(i).getSource_photo()).resize(300,400).centerCrop().into(dataitem.iv_photo);
        dataitem.tv_title.setText(photo_list.get(i).getTitle_photo());
        return view;
    }
    public class MyView{
        ImageView iv_photo;
        TextView tv_title;
    }
}
