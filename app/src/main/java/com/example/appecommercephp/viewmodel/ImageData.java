package com.example.appecommercephp.viewmodel;

import com.google.gson.annotations.SerializedName;

public class ImageData {

    @SerializedName("id_image")
    private String idImage ;
    @SerializedName("url_image")
    private String urlImage ;

    public ImageData(String idImage, String urlImage) {
        this.idImage = idImage;
        this.urlImage = urlImage;
    }

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
