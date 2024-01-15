package com.example.appecommercephp.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.model.CartModel;
import com.example.appecommercephp.model.ItemCartModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class CartViewModel extends BaseObservable implements Parcelable {

    public ObservableField<String> stringObservableField = new ObservableField<>("");

    public ObservableArrayList<CartModel> cartModelObservableArrayList = new ObservableArrayList<>();

    public ObservableBoolean observableBoolean = new ObservableBoolean(false);

    public ObservableField<Integer> totalPriceCarts = new ObservableField<>(0);

    private Disposable disposable2;

    private Disposable disposable ;



  public CartViewModel()
    {

        ApiService.API_SERVICE.getCartUser(HomeFragment.idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CartModel>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<CartModel> cartModels) {

                        Log.d("ódodfsdfasdf",cartModels.get(0).getItemCartModels().get(0).getIdShop());

                        cartModelObservableArrayList.addAll(cartModels);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {


                    }

                });

    }

    protected CartViewModel(Parcel in) {
        observableBoolean = in.readParcelable(ObservableBoolean.class.getClassLoader());
    }

    public static final Creator<CartViewModel> CREATOR = new Creator<CartViewModel>() {
        @Override
        public CartViewModel createFromParcel(Parcel in) {
            return new CartViewModel(in);
        }

        @Override
        public CartViewModel[] newArray(int size) {
            return new CartViewModel[size];
        }
    };

    public ObservableField<String> getStringObservableField() {
        return stringObservableField;
    }

    public void setStringObservableField(ObservableField<String> stringObservableField) {
        this.stringObservableField = stringObservableField;
    }

    public void incrementPrice()
    {
        Log.d("kiemtradulieudatanglen","faafafa");
    }

    public ArrayList<ItemCartModel> getDataCart()
    {
        ArrayList<ItemCartModel> list = new ArrayList<>();

        for ( CartModel cm : cartModelObservableArrayList) {
            for ( ItemCartModel item: cm.getItemCartModels()) {
                 if (item.isCheck())
                 {
                     list.add(item);
                 }
            }
        }
        return list ;

    }



    public void changeSelectCarts( ItemCartModel itemCartModel  , CartModel cartModel )
    {

        Log.d("foishdfoasdfsd",itemCartModel.isCheck() + "");
//         itemCartModel.setCheck(itemCartModel.isCheck());
        if(itemCartModel.isCheck())
        {

//            int x =  this.totalPriceCarts.get() ;
            int total = Integer.parseInt(itemCartModel.getCartPrice()) * Integer.parseInt(itemCartModel.getCartQuantity()) +  this.totalPriceCarts.get();
            this.totalPriceCarts.set(total);


            boolean isCheck = true ;

            for (int i = 0 ; i < cartModel.getItemCartModels().size() ; i++)
            {

                if(!cartModel.getItemCartModels().get(i).isCheck())
                {
                    isCheck = false ;
                    break;
                }

            }

            if(!isCheck)
            {
                cartModel.setCheck(false);
            }else {
                cartModel.setCheck(true);
            }

        }
        else {


            int total = this.totalPriceCarts.get()  - (Integer.parseInt(itemCartModel.getCartPrice()) * Integer.parseInt(itemCartModel.getCartQuantity()));
            this.totalPriceCarts.set(total);

            boolean isCheck = true ;
            for (int i = 0 ; i < cartModel.getItemCartModels().size() ; i++)
            {
                // chỉ cần 1  item   trong giỏ hàng ko check thi chung ta khong check cho shop
                if(!cartModel.getItemCartModels().get(i).isCheck())
                {
                    isCheck = false ;
                    break;
                }
            }

//            dòng này kiểm tra để check lại cửa hàng
            if(!isCheck)
            {
                cartModel.setCheck(false);
            }else {
                cartModel.setCheck(true);
            }

        }


    }

    public ObservableArrayList<CartModel> getCartModelObservableArrayList() {
        return cartModelObservableArrayList;
    }

    public void setCartModelObservableArrayList(ObservableArrayList<CartModel> cartModelObservableArrayList) {
        this.cartModelObservableArrayList = cartModelObservableArrayList;
    }

    public void incrementCart(ItemCartModel cartModel)
    {

        cartModel.setCartQuantity(String.valueOf((Integer.parseInt(cartModel.getCartQuantity()) + 1)));
         if(cartModel.isCheck())
         {
             Log.d("fosishfs","dfhs");
//             int price = Integer.parseInt(cartModel.getCartPrice()) * Integer.parseInt(cartModel.getCartQuantity()) ;
             this.totalPriceCarts.set( this.totalPriceCarts.get() + Integer.parseInt(cartModel.getCartPrice()) );
         }
//        Log.d("SFdfasdfasdfsadf",cartModel.getCartQuantity() + " ");
    }

    public void decrementCart(ItemCartModel cartModel)
    {
       if( Integer.parseInt(cartModel.getCartQuantity()) > 1 )
       {

           cartModel.setCartQuantity(String.valueOf((Integer.parseInt(cartModel.getCartQuantity()) - 1)));

           if(cartModel.isCheck())
           {
               Log.d("fosishfs","dfhs");
//             int price = Integer.parseInt(cartModel.getCartPrice()) * Integer.parseInt(cartModel.getCartQuantity()) ;
               this.totalPriceCarts.set( this.totalPriceCarts.get() - Integer.parseInt(cartModel.getCartPrice()) );
           }

//           Log.d("SFdfasdfasdfsadf",cartModel.getCartQuantity() + " ");
       }
       else
       {
//         nếu người dùng xóa liên tục sản phẩm về số 0 thì xử lý xóa sản phẩm khỏi list cart
//         và hiển thị lên thông báo
           deleteItemCart(cartModel.getIdProduct(),HomeFragment.idUser);
//           CartModel cartModel1 = cartModelObservableArrayList
           ArrayList<CartModel> cartModels = new ArrayList<>();
           cartModels.addAll(CartViewModel.this.getCartModelObservableArrayList());

           for (int i = 0; i < cartModels.size() ; i++)
           {

               for (int j = 0 ; j < cartModels.get(i).getItemCartModels().size() ; j++)
               {
                  if(cartModels.get(i).getItemCartModels().get(j).getIdProduct().equals(cartModel.getIdProduct()))
                  {
                      cartModels.get(i).getItemCartModels().remove(j);
                  }
               }
           }

           CartViewModel.this.cartModelObservableArrayList.clear();
           CartViewModel.this.cartModelObservableArrayList.addAll(cartModels);
//           cartModelObservableArrayList.clear();
//           cartModelObservableArrayList.addAll(cartModels);

       }
    }

// hàm xóa sản phẩm cart khi người dùng bấm trừ bé hơn 1 sản phẩm
    public void deleteItemCart(String idProduct , String idUser)
    {
        ApiService.API_SERVICE.deleteItemCart(idProduct, idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable2 = d ;
                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {

//                        Log.d("fasfasdfasdf",responseBody.toString());

                        if(responseBody.toString().contains("1"))
                        {
                            stringObservableField.set("1");
                        }else {
                            stringObservableField.set("-1");
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("loi khi xoa ung dung",e.toString());
                        disposable2.dispose();
                    }

                    @Override
                    public void onComplete() {
                        disposable2.dispose();
                    }
                });
    }

    public void updataAllCartShop(CartModel cartModel)
    {

        if(cartModel.isCheck() == true )
        {
          for (int i = 0 ; i < cartModel.getItemCartModels().size();i++)
          {
              if(cartModel.getItemCartModels().get(i).isCheck() == true)
              {
              }
              else
              {
                  cartModel.getItemCartModels().get(i).setCheck(true);
                  changeSelectCarts(cartModel.getItemCartModels().get(i),cartModel);
              }
          }
        }
        else
        {
            for (int i = 0 ; i < cartModel.getItemCartModels().size();i++)
            {
                cartModel.getItemCartModels().get(i).setCheck(false);
                changeSelectCarts(cartModel.getItemCartModels().get(i),cartModel);
            }
        }

    }

    public ArrayList<ItemCartModel> getAllListCart()
    {
        ArrayList<ItemCartModel> itemCartModels = new ArrayList<>();
        for (int i = 0 ; i < cartModelObservableArrayList.size() ; i++)
        {
            for(int j = 0 ; j < cartModelObservableArrayList.get(i).getItemCartModels().size() ; j++)
            {
                if( cartModelObservableArrayList.get(i).getItemCartModels().get(j).isCheck() ==  true)
                {
                    itemCartModels.add(cartModelObservableArrayList.get(i).getItemCartModels().get(j));
                }
            }
        }
        return  itemCartModels ;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@androidx.annotation.NonNull Parcel dest, int flags) {
        dest.writeParcelable(observableBoolean, flags);
    }
}
