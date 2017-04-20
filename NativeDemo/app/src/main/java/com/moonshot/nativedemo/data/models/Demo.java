package com.moonshot.nativedemo.data.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by armando_contreras on 4/18/17.
 */

public class Demo implements Serializable {

    public String title;
    public String description;
    public String type;
    public String thumbnail;


    public Demo(String title, String description, String type, String thumbnail) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.thumbnail = thumbnail;
    }

    //Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getThumbnail() { return thumbnail; }
    public String getType() { return type; }

}
