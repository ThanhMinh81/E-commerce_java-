package com.example.appecommercephp.model;

import com.google.gson.annotations.SerializedName;

public class idProductModel {

    @SerializedName("id_save")
    private String idSave ;

    @SerializedName("id_product")
    private String idProduct ;

    public idProductModel(String idSave, String idProduct) {
        this.idSave = idSave;
        this.idProduct = idProduct;
    }

    public String getIdSave() {
        return idSave;
    }

    public void setIdSave(String idSave) {
        this.idSave = idSave;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }
}
