package com.moonshot.nativedemo;

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

    public static List<Demo> createDefaultDemos() {
        ArrayList<Demo> items = new ArrayList<>();
        items.add(new Demo("Teapot AR Vuforia", "This is an example of using the Vuforia SDK to launch an Augmented Reality Exprience", "ar", "https://learningscenejs.files.wordpress.com/2010/03/teapot-example1.jpg"));
        items.add(new Demo("Open GL NativeActivity", "This is an exmple of using dependent native modules to create a native view using OpenGL", "native", "https://learningscenejs.files.wordpress.com/2010/03/teapot-example1.jpg"));
        items.add(new Demo("Open GL Graph GlView", "This is an exmple module that creates a graph of accelerometer data", "accelerometer", "http://www.larsivar.com/cp/images/android_AccForces.png"));
        items.add(new Demo("Video AR Vuforia", "This is an exmple module using Vuforia SDK to render a video on a target", "arvideo", "http://answers.unity3d.com/storage/temp/81928-screenshot-2016-11-10-14-46-59.png"));

        return items;
    }
}
