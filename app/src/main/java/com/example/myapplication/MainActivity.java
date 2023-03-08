package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Photo> listphotos;
    final String url = "https://6404aa683bdc59fa8f3e8693.mockapi.io/api/v1/photo";
    GridView gridView;
    private AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this,ViewPhotoActivity.class);
            intent.putExtra("id",gridView.getAdapter().getItemId(i));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listphotos = new ArrayList<>();
        addPhotos();

    }
    public void addPhotos(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject photoObject = response.getJSONObject(i);
                        Photo photo = new Photo();
                        photo.setId(Integer.parseInt(photoObject.getString("id")));
                        photo.setDesc_photo(photoObject.getString("description").toString());
                        photo.setSource_photo(photoObject.getString("photo").toString());
                        photo.setTitle_photo(photoObject.getString("title").toString());
                        listphotos.add(photo);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                gridView = findViewById(R.id.gridview);
                PhotoAdapter adapter = new PhotoAdapter(listphotos,getApplicationContext());
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(onitemclick);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonArrayRequest);
    };
    public static Photo getPhotoById(int id){
        ArrayList<Photo> photos = (ArrayList<Photo>) listphotos.clone();
        for (int i = 0 ;i<photos.size();i++){
            if(photos.get(i).getId()==id){
                return photos.get(i);
            }
        }
        return null;
    };
}