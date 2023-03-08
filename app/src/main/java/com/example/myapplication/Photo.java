package com.example.myapplication;

public class Photo {
    private int id;
    private String source_photo;
    private String title_photo;
    private String desc_photo;

    public Photo() {
    }

    public Photo(int id, String source_photo, String title_photo, String desc_photo) {
        this.id = id;
        this.source_photo = source_photo;
        this.title_photo = title_photo;
        this.desc_photo = desc_photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource_photo() {
        return source_photo;
    }

    public void setSource_photo(String source_photo) {
        this.source_photo = source_photo;
    }

    public String getTitle_photo() {
        return title_photo;
    }

    public void setTitle_photo(String title_photo) {
        this.title_photo = title_photo;
    }

    public String getDesc_photo() {
        return desc_photo;
    }

    public void setDesc_photo(String desc_photo) {
        this.desc_photo = desc_photo;
    }
}
