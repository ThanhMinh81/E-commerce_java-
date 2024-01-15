package com.example.appecommercephp.model;

import com.google.gson.annotations.SerializedName;

public class ProductFavourite {


    private int id_favourite ;
    private  String id_product ;


    private String name_product ;


    private String price_products ;


    private String url_image ;


    private String time_save ;

    private int id_user ;

    public ProductFavourite() {
    }

    public ProductFavourite(int id_favourite, String id_product, String name_product, String price_products, String url_image, String time_save, int id_user) {
        this.id_favourite = id_favourite;
        this.id_product = id_product;
        this.name_product = name_product;
        this.price_products = price_products;
        this.url_image = url_image;
        this.time_save = time_save;
        this.id_user = id_user;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getPrice_products() {
        return price_products;
    }

    public void setPrice_products(String price_products) {
        this.price_products = price_products;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getTime_save() {
        return time_save;
    }

    public void setTime_save(String time_save) {
        this.time_save = time_save;
    }

    public int getId_favourite() {
        return id_favourite;
    }

    public void setId_favourite(int id_favourite) {
        this.id_favourite = id_favourite;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "ProductFavourite{" +
                "id_product='" + id_product + '\'' +
                ", name_product='" + name_product + '\'' +
                ", price_products='" + price_products + '\'' +
                ", url_image='" + url_image + '\'' +
                ", time_save='" + time_save + '\'' +
                '}';
    }
}
