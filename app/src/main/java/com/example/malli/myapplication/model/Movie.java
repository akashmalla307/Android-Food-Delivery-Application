package com.example.malli.myapplication.model;

import java.util.ArrayList;

/**
 * Created by malli on 2/22/2016.
 */

public class Movie {
    //private  int qty;
    private  String qty1;
    private String title, thumbnailUrl;
    private String year;
    private double rating;
    private Integer rating1;
    private ArrayList<String> genre;
    private int id;
    Integer cost;
    private String totalcost;
    public Movie() {
    }

    public Movie(String name, String thumbnailUrl, String year, double rating,
                 ArrayList<String> genre,String qty1) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
        this.qty1 = qty1;
    }

    public String getTitle() {
        return title;
    }
   /* public String getId() {
        return id;
    }
*/
   public void setqty(String qty1) {
       this.qty1 = qty1;
   }
    public String getqty() {
        return qty1;
    }
   public void setTotalCost(String totalcost) {
       this.totalcost = totalcost;
   }
    public String getTotalCost() {
        return totalcost;
    }
    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    /*public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }*/

    public Integer getitemid() {
        return id;
    }
    public void setitemid(Integer id) {
        this.id = id;
    }

    public Integer getCost() {
        return cost;
    }
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public double getRating() {
        return rating;
    }

    public Integer getRating1() {
        return rating1;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

}