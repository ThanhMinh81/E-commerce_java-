package com.example.appecommercephp.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.appecommercephp.BR;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CartModel extends BaseObservable {

    @SerializedName("id_shop")
    private String idShop ;

    @SerializedName("name_shop")
    private String nameShop ;

    @SerializedName("products")
    private ArrayList<ItemCartModel> itemCartModels ;

    private boolean isCheck = false ;

    @Bindable
    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check)
    {
         this.isCheck = check ;
        notifyPropertyChanged(BR.check);
    }

    public CartModel(String idShop, String nameShop, ArrayList<ItemCartModel> itemCartModels, boolean isCheck) {
        this.idShop = idShop;
        this.nameShop = nameShop;
        this.itemCartModels = itemCartModels;
        this.isCheck = isCheck;
    }

    public String getIdShop()
    {
        return idShop;
    }

    public void setIdShop(String idShop)
    {
        this.idShop = idShop;
    }

    public String getNameShop()
    {
        return nameShop;
    }

    public void setNameShop(String nameShop)
    {
        this.nameShop = nameShop;
    }

    public ArrayList<ItemCartModel> getItemCartModels()
    {
        return itemCartModels;
    }

    public void setItemCartModels(ArrayList<ItemCartModel> itemCartModels)
    {
        this.itemCartModels = itemCartModels;
    }


}
