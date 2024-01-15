package com.example.appecommercephp.model;

public class Purchase {

     private String idPurchase ;
     private String idProduct;
     private String idUser;
     private String idShop ;
     private String time ;
     private String amount ;


    public Purchase(String idPurchase, String idProduct, String idUser, String idShop, String time, String amount) {
        this.idPurchase = idPurchase;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.idShop = idShop;
        this.time = time;
        this.amount = amount;
    }

    public String getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(String idPurchase) {
        this.idPurchase = idPurchase;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
