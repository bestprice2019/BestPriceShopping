package com.example.bpsl;

public class Item {
    private String supermarket;
    private String item_name;
    private String price;

    public Item(String supermarket, String item_name, String price) {
        this.supermarket = supermarket;
        this.item_name = item_name;
        this.price = price;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String toString(){
        return "Supermarket:"+this.getSupermarket()+"\nItem:"+this.getItem_name()+"\nPrice:"+this.getPrice()+"$";
    }
}
