package com.example.appecommercephp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {


    @SerializedName("id_list_order")
    private int idOrder ;


    @SerializedName("id_user")
    private int idUser  ;

    @SerializedName("name_user")
    private String nameUser ;

    @SerializedName("phone_number")
    private String phoneNumber ;

    @SerializedName("address")
    private String address ;

    @SerializedName("time")
    private String time ;

    @SerializedName("orderModels")
    private List<ItemOrder> itemOrderList ;

    public OrderModel() {
    }

    public OrderModel(int idOrder, int idUser, String nameUser, String address, String phoneNumber, String time,List<ItemOrder> itemOrderList) {
        this.idOrder = idOrder;
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.time = time;
        this.itemOrderList = itemOrderList ;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ItemOrder>  getItemOrderList() {
        return itemOrderList;
    }

    public void setItemOrderList(ArrayList<ItemOrder> itemOrderList) {
        this.itemOrderList = itemOrderList;
    }
}
