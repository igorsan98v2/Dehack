package com.example.basefeature;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IssueInfo {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("category")
    @Expose
    private int category;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("coord")
    private LatLng coord;
    @SerializedName("issue_id")
    private long  id;
    @SerializedName("author")
    private String author;
    @SerializedName("date")
    private String dateString;
    @SerializedName("yes")
    private int yes;
    @SerializedName("no")
    private int no;

    private long  inner_id;

    public void setYes(int yes) {
        this.yes = yes;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public long getInner_id() {
        return inner_id;
    }

    public void setInner_id(long inner_id) {
        this.inner_id = inner_id;
    }

    public int getYes() {
        return yes;
    }

    public void setYes(String yes) {
        this.yes = Integer.getInteger(yes);
    }

    public int getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = Integer.getInteger(no);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public void setCoord(String coord) {
        String[] coords = coord.split(",");
        if(coords.length>1) {
            double latitude = Double.valueOf(coords[0]);
            double longtitude = Double.valueOf(coords[1]);
            this.coord = new LatLng(latitude,longtitude);
        }
        else this.coord = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //check about null !!! add new Exeption
    public LatLng getCoord() {
        return coord;
    }

    public void setCoord(LatLng coord) {
        this.coord = coord;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
