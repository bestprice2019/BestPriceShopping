package com.example.bpsl;

import java.util.ArrayList;

public class ShoppingList {
    private String id;
    private String title;
    private String date;
    private String time;
    private String total_price;
    private ArrayList<Item> list;
    private String direction;

    public ShoppingList(String id, String title, String date, String time, String total_price, ArrayList<Item> list, String direction) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.total_price = total_price;
        this.list = list;
        this.direction = direction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public ArrayList<Item> getList() {
        return this.list;
    }

    public void setList(ArrayList<Item> newlist) {
        this.list=newlist;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction=direction;
    }

    public String toString(){
        return "ID:"+this.getId()+"\nTITLE:"+this.getTitle()+"\nDATE;"+this.getDate()+"\nTIME:"+this.getTime()+"\nDirection:"+this.getDirection();
    }
}
