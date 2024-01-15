package com.example.appecommercephp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.appecommercephp.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemCartModel extends BaseObservable implements Parcelable {

    @SerializedName("id_shop")
    private String idShop ;

    @SerializedName("id_products")
    private String idProduct ;

    @SerializedName("name_product")
    private String nameProduct ;

    @SerializedName("url_image")
    private String imgProduct ;

    @SerializedName("id_carts")
    private String idCart ;

    @SerializedName("cart_price")
    private String cartPrice ;

    @SerializedName("cart_quantity")
    private String cartQuantity  ;

    @SerializedName("name_shop")
    private String nameShop ;


    @Expose(serialize = false, deserialize = false)
    private boolean isCheck = false ;

    @Expose(serialize = false, deserialize = false)
    private boolean isCheckParentCall = false ;

    protected ItemCartModel(Parcel in) {
        idShop = in.readString();
        idProduct = in.readString();
        nameProduct = in.readString();
        imgProduct = in.readString();
        idCart = in.readString();
        cartPrice = in.readString();
        cartQuantity = in.readString();
        isCheck = in.readByte() != 0;
        isCheckParentCall = in.readByte() != 0;
    }

    public static final Creator<ItemCartModel> CREATOR = new Creator<ItemCartModel>() {
        @Override
        public ItemCartModel createFromParcel(Parcel in) {
            return new ItemCartModel(in);
        }

        @Override
        public ItemCartModel[] newArray(int size) {
            return new ItemCartModel[size];
        }
    };

    @Bindable
    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
        notifyPropertyChanged(BR.check);
    }


    @Bindable
    public String getCartQuantity() {
        return cartQuantity;
    }


    public void setCartQuantity(String cartQuantity) {
        this.cartQuantity = cartQuantity;
        notifyPropertyChanged(BR.cartQuantity);
    }


//    public ItemCartModel() {
//    }


    public ItemCartModel(String idShop, String idProduct, String nameProduct, String imgProduct, String idCart, String cartPrice, String cartQuantity, String nameShop, boolean isCheck, boolean isCheckParentCall) {
        this.idShop = idShop;
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.imgProduct = imgProduct;
        this.idCart = idCart;
        this.cartPrice = cartPrice;
        this.cartQuantity = cartQuantity;
        this.nameShop = nameShop;
        this.isCheck = isCheck;
        this.isCheckParentCall = isCheckParentCall;
    }

    public String getIdShop() {
        return idShop;
    }

    public void setIdShop(String idShop) {
        this.idShop = idShop;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }



    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    @Bindable
    public boolean isCheckParentCall() {
        return isCheckParentCall;
    }

    public void setCheckParentCall(boolean checkParentCall) {
        isCheckParentCall = checkParentCall;
        notifyPropertyChanged(BR.checkParentCall);
    }

    @BindingAdapter({"imageLoadCart"})
    public static void loadImage(ImageView view, String url)
    {
        Glide.with(view.getContext()).load(url).into(view);
    }


    @Override
    public String toString() {
        return "ItemCartModel{" +
                "idProduct='" + idProduct + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", imgProduct='" + imgProduct + '\'' +
                ", idCart='" + idCart + '\'' +
                ", cartPrice='" + cartPrice + '\'' +
                ", cartQuantity='" + cartQuantity + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(idShop);
        dest.writeString(idProduct);
        dest.writeString(nameProduct);
        dest.writeString(imgProduct);
        dest.writeString(idCart);
        dest.writeString(cartPrice);
        dest.writeString(cartQuantity);
        dest.writeByte((byte) (isCheck ? 1 : 0));
        dest.writeByte((byte) (isCheckParentCall ? 1 : 0));
    }
}

