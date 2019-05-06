package com.example.bpsl;

public class ShoppingList {
    private int id;
    private String title;
    private String date;
    private String time;
    private double total_price;

    public ShoppingList(int id, String title, String date, String time, double total_price) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.time = time;
        this.total_price = total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String toString(){
        return "ID:"+this.getId()+"\nTITLE:"+this.getTitle()+"\nDATE;"+this.getDate()+"\nTIME:"+this.getTime();
    }
}
