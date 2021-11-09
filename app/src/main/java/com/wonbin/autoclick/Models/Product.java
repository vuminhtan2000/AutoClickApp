package com.wonbin.autoclick.Models;

public class Product {
    private int img;
    private String name_Product;
    private long percent;
    private long price;
    private long sole;

    public Product(int img, String name_Product, long percent, long price, long sole) {
        this.img = img;
        this.name_Product = name_Product;
        this.percent = percent;
        this.price = price;
        this.sole = sole;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName_Product() {
        return name_Product;
    }

    public void setName_Product(String name_Product) {
        this.name_Product = name_Product;
    }

    public long getPercent() {
        return percent;
    }

    public void setPercent(long percent) {
        this.percent = percent;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getSole() {
        return sole;
    }

    public void setSole(long sole) {
        this.sole = sole;
    }
}
