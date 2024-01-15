package com.example.appecommercephp.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.appecommercephp.BR;
import com.google.gson.annotations.SerializedName;


public class User extends BaseObservable {

    @SerializedName("id_user")
    private String id ;

    @SerializedName("full_name_user")
    private String fullName ;

    @SerializedName("avt_img_user")
    private String imgAvt ;

    @SerializedName("email_user")
    private String email ;

    @SerializedName("password_user")
    private String passwords ;

    @SerializedName("own_shop")
    private String ownShop ;

    public User() {
    }

    public User(String id, String fullName, String imgAvt, String email, String passwords, String ownShop) {
        this.id = id;
        this.fullName = fullName;
        this.imgAvt = imgAvt;
        this.email = email;
        this.passwords = passwords;
        this.ownShop = ownShop;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOwnShop() {
        return ownShop;
    }

    public void setOwnShop(String ownShop) {
        this.ownShop = ownShop;
    }

    public String getImgAvt() {
        return imgAvt;
    }

    public void setImgAvt(String imgAvt) {
        this.imgAvt = imgAvt;
    }




    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPasswords() {
        return passwords;
    }

    @Bindable
    public String getEmail() {
        return email;
    }
    public void setPasswords(String passwords) {
        this.passwords = passwords;
        notifyPropertyChanged(BR.passwords);

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", imgAvt='" + imgAvt + '\'' +
                ", email='" + email + '\'' +
                ", passwords='" + passwords + '\'' +
                '}';
    }
}
