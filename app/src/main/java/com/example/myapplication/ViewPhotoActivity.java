package com.example.myapplication;

//import static com.example.myapplication.MainActivity.getPhotoById;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewPhotoActivity extends AppCompatActivity {
    ImageView iv_detail_photo;
    TextView tv_detail_title,tv_detail_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);
        iv_detail_photo = findViewById(R.id.iv_detail_photo);
        tv_detail_title = findViewById(R.id.tv_detail_title);
        tv_detail_desc = findViewById(R.id.tv_detail_desc);
        int id = (int)getIntent().getLongExtra("id",0);
        Article current = ArticleData.getPhotoFromId(id);
        Picasso.get().load(current.getArticle_image()).resize(600,600).centerCrop().into(iv_detail_photo);
        tv_detail_title.setText(current.getArticle_title());
        tv_detail_desc.setText(current.getArticle_description());
    }
}