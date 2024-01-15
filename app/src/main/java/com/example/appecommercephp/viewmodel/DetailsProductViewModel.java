package com.example.appecommercephp.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.Adapter.CommentAdapter;
import com.example.appecommercephp.Server.ApiService;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.interfaceView;
import com.example.appecommercephp.model.CommentsModel;
import com.example.appecommercephp.model.ProductFavourite;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DetailsProductViewModel  extends BaseObservable {
     private Context context ;

//     cái biến ObservableArray này nó không có hoạt động trong 1 class chung đâu à
    private  ObservableArrayList<CommentsModel> listComments = new ObservableArrayList<>();

    public   ObservableArrayList<ProductFavourite> productFavourites = new ObservableArrayList<>();
    interfaceView.detailsProduct detailseProduct;

    //   obj sản phẩm tạo ra để check xem nó có null hay không
    ProductFavourite myData = new ProductFavourite() ;

//    private   ObservableArrayList<ProductsData> productsDataFavourite = new ObservableArrayList<>();
    public    ObservableField<Boolean> booleanObservableShowImage = new ObservableField<>(false);
    private  Disposable disposable2 ;

    private int idProduct ;

    SaveProductViewModel saveProductViewModel ;


    public DetailsProductViewModel() {
    }

    public DetailsProductViewModel(int idProduct , Context context ,   interfaceView.detailsProduct detailseProduct) {

           saveProductViewModel = new SaveProductViewModel()  ;

        this.context = context ;
        this.idProduct = idProduct ;
      this.detailseProduct = detailseProduct ;

        ApiService.API_SERVICE.detailsProduct(idProduct)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CommentsModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable2 = d ;
                    }

                    @Override
                    public void onNext(@NonNull List<CommentsModel> commentsModels) {

                        Log.d("fosfoasfioasdfhaiocnoehwow",commentsModels.size() + " ");

                        listComments.addAll(commentsModels);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        disposable2.dispose();
                    }
                });

        ApiService.API_SERVICE.
                getListFavorite(HomeFragment.idUser,String.valueOf(idProduct),"getFavourite").subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {

                        // Trích xuất nội dung từ ResponseBody dưới dạng chuỗi
                        String jsonString = null;
                        try {
                            jsonString = responseBody.string();

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        if(jsonString.contains("empty"))
                        {

                        }else {

                            Gson gson = new Gson();
                            Type listType = new TypeToken<ArrayList<ProductFavourite>>() {}.getType();
                            ArrayList<ProductFavourite>  arrayList = gson.fromJson(jsonString, listType) ;

                            productFavourites.addAll(arrayList);
                            Log.d("sfosfhasidfas","đúng rồi" + DetailsProductViewModel.this.getProductFavourites().size() );

                            DetailsProductViewModel.this.setProductFavourites(productFavourites);

                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                        Optional<ProductFavourite> result  =   productFavourites.stream()
                                .filter(productFavourite ->  Integer.valueOf(productFavourite.getId_product()) ==  idProduct)
                                .findFirst();

                        if(result.isPresent())
                        {
                            booleanObservableShowImage.set(true);
                        }
                        else
                        {
                            booleanObservableShowImage.set(false);
                        }

                    }
                });

    }

    public ObservableArrayList<CommentsModel> getListComments() {
        return listComments;
    }

    public void setListComments(ObservableArrayList<CommentsModel> listComments) {
        this.listComments = listComments;
    }


    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }


    public ObservableArrayList<ProductFavourite> getProductFavourites() {
        return productFavourites;
    }

    public void setProductFavourites(ObservableArrayList<ProductFavourite> productFavourites) {
        this.productFavourites = productFavourites;
    }

    public void updateListFavourite(boolean check , String idProduct)
    {


//        Optional<ProductFavourite> result  =   productFavourites.stream()
//                .filter(productFavourite ->  Integer.valueOf(productFavourite.getId_product()) ==  idProduct)
//                .findFirst();
         if(check)
         {
 //   Xóa sản phẩm khỏi list yêu thích
//             xóa sản phẩm : trạng thái true khi sản phẩm đó đang có trong ds yêu thích
//             và người dùng bấm nút xóa thì ta xử lý xóa

             ApiService.API_SERVICE.updateFavourite(HomeFragment.idUser,idProduct , "Delete")
                     .subscribeOn(AndroidSchedulers.mainThread())
                     .subscribe(new Observer<ResponseBody>() {
                         @Override
                         public void onSubscribe(@NonNull Disposable d) {

                         }

                         @Override
                         public void onNext(@NonNull ResponseBody responseBody) {
                             String s = null;
                             try {
                                 s = responseBody.string();
                             } catch (IOException e) {
                                 throw new RuntimeException(e);
                             }


                                 if(s.contains("DeleteSuccess"))
                                 {

//                               xóa sản phẩm thành công
                                     DetailsProductViewModel.this.booleanObservableShowImage.set(false);

                                     for (ProductFavourite productFavourite : productFavourites)
                                     {
                                         if(productFavourite.getId_product().equals(idProduct))
                                         {
                                             DetailsProductViewModel.this.getProductFavourites().remove(productFavourite);
                                         }
                                     }

//                                     saveProductViewModel.loadDataNeed();



                                 }else if(s.contains("DeleteFalse"))
                                 {
//                                 xóa sản phẩm thất bại
                                     DetailsProductViewModel.this.booleanObservableShowImage.set(true);
                                 }



                         }

                         @Override
                         public void onError(@NonNull Throwable e) {

                         }

                         @Override
                         public void onComplete() {
                             if(DetailsProductViewModel.this.booleanObservableShowImage.get() == false)
                             {
                                 detailseProduct.showMess(" Bỏ lưu sản phẩm thành công !");
                             }else if(DetailsProductViewModel.this.booleanObservableShowImage.get() == true) {
                                 detailseProduct.showMess("Bỏ lưu sản phẩm thất bại , Vui lòng thử lại sau  !");
                             }

                         }
                     });

         }
         else if(!check)
         {
//       thêm sản phẩm vào list yêu thích
           ApiService.API_SERVICE.updateFavourite(HomeFragment.idUser,idProduct,"Add")
                   .subscribeOn(Schedulers.io())
                   .subscribeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Observer<ResponseBody>() {
                       @Override
                       public void onSubscribe(@NonNull Disposable d) {
                       }

                       @Override
                       public void onNext(@NonNull ResponseBody responseBody) {

                           Log.d("kiemtramainthred",Thread.currentThread().getName());

                           Gson gson = new Gson();
                           String json = null;
                           try {
                               json = responseBody.string();
                           } catch (IOException e) {
                               throw new RuntimeException(e);
                           }
                           myData = gson.fromJson(json, ProductFavourite.class);
                           productFavourites.add(myData);
                           booleanObservableShowImage.set(true);
                           Log.d("kiemtra",productFavourites.size() + "");

                       }

                       @Override
                       public void onError(@NonNull Throwable e) {
                         Log.d("error","Lỗi khi call api list yêu thích");
                       }

                       @Override
                       public void onComplete() {
                           if(myData.getId_product() != null)
                           {
                               detailseProduct.showMess("Lưu sản phẩm thành công !");
//                               saveProductViewModel.loadDataNeed();
                               booleanObservableShowImage.set(true);
                           }else {
                               detailseProduct.showMess("Lưu sản phẩm thất bại , Vui lòng thử lại sau !");
                               booleanObservableShowImage.set(false);
                           }
                       }
                   });



         }



     }



     public void getAllProductFavourite()
     {
           SharedPreferences preferences ;





     }



    @BindingAdapter({"listProductCMT"})
    public static void getDATa(RecyclerView view, ObservableArrayList<CommentsModel> commentsModels  )
    {

           CommentAdapter commentAdapter = new CommentAdapter(commentsModels);
           view.setAdapter(commentAdapter);

    }





}
