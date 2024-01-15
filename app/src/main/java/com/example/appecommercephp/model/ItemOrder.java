package com.example.appecommercephp.model;

import com.google.gson.annotations.SerializedName;

public class ItemOrder {

    @SerializedName("id_order")
    private int idItemOrder ;

    @SerializedName("id_product")
    private int idProduct ;

    @SerializedName("quantity")
    private String quantity ;

    @SerializedName("name_product")
    private String nameProduct ;

    @SerializedName("image_product")
    private String imageProduct ;

    @SerializedName("price_product")
    private String priceProduct ;

    @SerializedName("state_order")
    private String stateOrder ;

    @SerializedName("id_shop")
    private  String idShop ;  

    @SerializedName("name_shop")
    private String nameShop ;


    public ItemOrder(int idItemOrder, int idProduct, String quantity, String nameProduct, String imageProduct, String priceProduct, String stateOrder, String idShop, String nameShop) {
        this.idItemOrder = idItemOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.nameProduct = nameProduct;
        this.imageProduct = imageProduct;
        this.priceProduct = priceProduct;
        this.stateOrder = stateOrder;
        this.idShop = idShop;
        this.nameShop = nameShop;
    }

    public int getIdItemOrder() {
        return idItemOrder;
    }

    public void setIdItemOrder(int idItemOrder) {
        this.idItemOrder = idItemOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getStateOrder() {
        return stateOrder;
    }

    public void setStateOrder(String stateOrder) {
        this.stateOrder = stateOrder;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }


    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }
}
