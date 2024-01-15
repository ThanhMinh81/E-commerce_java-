package com.example.appecommercephp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.appecommercephp.BR;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductsData extends BaseObservable implements Parcelable {

    @SerializedName("id_product")
    private int idProduct ;

    @SerializedName("price_product")
    private String priceProduct ;

    @SerializedName("name_product")
    private String nameProduct;

    @SerializedName("des_product")
    private String descriptProduct ;

    @SerializedName("image")
    private ArrayList<String> imageViewModels ;

    @SerializedName("total_product")
    private String totalProduct ;

    @SerializedName("id_brand")
    private String idBrandProduct ;

    @SerializedName("id_shop")
    private String idShopProduct;

    @SerializedName("quantity")
    private String quantityProduct = "1" ;

    @SerializedName("id_user_owner")
    private String idUserOwner ;

    @SerializedName("name_shop")
    private String nameShop ;

    @SerializedName("address")
    private String addressShop ;


    public ProductsData(int idProduct, String priceProduct, String nameProduct, String descriptProduct, ArrayList<String> imageViewModels, String totalProduct, String idBrandProduct, String idShopProduct, String idUserOwner, String nameShop, String addressShop) {
        this.idProduct = idProduct;
        this.priceProduct = priceProduct;
        this.nameProduct = nameProduct;
        this.descriptProduct = descriptProduct;
        this.imageViewModels = imageViewModels;
        this.totalProduct = totalProduct;
        this.idBrandProduct = idBrandProduct;
        this.idShopProduct = idShopProduct;
        this.idUserOwner = idUserOwner;
        this.nameShop = nameShop;
        this.addressShop = addressShop;
    }

    // day la constructor de get list save product cua nguoi dung
    // gia lai 31.12.23

    public ProductsData(int idProduct, String priceProduct, String nameProduct, String descriptProduct, ArrayList<String> imageViewModels, String totalProduct, String idBrandProduct) {
        this.idProduct = idProduct;
        this.priceProduct = priceProduct;
        this.nameProduct = nameProduct;
        this.descriptProduct = descriptProduct;
        this.imageViewModels = imageViewModels;
        this.totalProduct = totalProduct;
        this.idBrandProduct = idBrandProduct;
    }

    protected ProductsData(Parcel in) {
        idProduct = in.readInt();
        priceProduct = in.readString();
        nameProduct = in.readString();
        descriptProduct = in.readString();
        imageViewModels = in.createStringArrayList();
        totalProduct = in.readString();
        idBrandProduct = in.readString();
        idShopProduct = in.readString();
        idUserOwner = in.readString();
        nameShop = in.readString();
        addressShop = in.readString();
    }

    public static final Creator<ProductsData> CREATOR = new Creator<ProductsData>() {
        @Override
        public ProductsData createFromParcel(Parcel in) {
            return new ProductsData(in);
        }

        @Override
        public ProductsData[] newArray(int size) {
            return new ProductsData[size];
        }
    };

    public int getIdProduct() {
        return idProduct;
    }

    @Bindable
    public String getPriceProduct() {
        return priceProduct;
    }



    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
        notifyPropertyChanged(BR.priceProduct);
    }


    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescriptProduct() {
        return descriptProduct;
    }

    public void setDescriptProduct(String descriptProduct) {
        this.descriptProduct = descriptProduct;
    }

    public ArrayList<String> getImageViewModels() {
        return imageViewModels;
    }

    public void setImageViewModels(ArrayList<String> imageViewModels) {
        this.imageViewModels = imageViewModels;
    }

    public String getIdUserOwner() {
        return idUserOwner;
    }

    public void setIdUserOwner(String idUserOwner) {
        this.idUserOwner = idUserOwner;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public String getAddressShop() {
        return addressShop;
    }

    public void setAddressShop(String addressShop) {
        this.addressShop = addressShop;
    }

    public String getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(String totalProduct) {
        this.totalProduct = totalProduct;
    }

    public String getIdBrandProduct() {
        return idBrandProduct;
    }

    public void setIdBrandProduct(String idBrandProduct) {
        this.idBrandProduct = idBrandProduct;
    }

    public String getIdShopProduct() {
        return idShopProduct;
    }

    public void setIdShopProduct(String idShopProduct) {
        this.idShopProduct = idShopProduct;
    }




    @Bindable
    public String getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(String quantityProduct) {
        this.quantityProduct = quantityProduct;
        notifyPropertyChanged(BR.quantityProduct);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url)
    {
        Glide.with(view.getContext()).load(url).into(view);
    }



    @BindingAdapter({"priceProduct"})
    public static void formatPrice(TextView view , String price)
    {

           DecimalFormat formatter = new DecimalFormat("###,###,###");
           view.setText(formatter.format(Integer.valueOf(price)) + "vnđ");

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idProduct);
        dest.writeString(priceProduct);
        dest.writeString(nameProduct);
        dest.writeString(descriptProduct);
        dest.writeStringList(imageViewModels);
        dest.writeString(totalProduct);
        dest.writeString(idBrandProduct);
        dest.writeString(idShopProduct);
        dest.writeString(idUserOwner);
        dest.writeString(nameShop);
        dest.writeString(addressShop);
    }





    public void lowItemCart(int quantityCurrent , boolean data)
    {

         if(data == false)
         {
//             thực hiện phép trừ
             if(quantityCurrent > 1)
             {

                 Log.d("Fsfsafa","Fsfasf");
                 quantityCurrent -= 1;
                 this.setQuantityProduct(String.valueOf(quantityCurrent));

             }else {

             }
         }else {
            quantityCurrent  = quantityCurrent + 1 ;
             this.setQuantityProduct(String.valueOf(quantityCurrent));

         }

    }

}
