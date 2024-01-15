package com.example.appecommercephp.Server;

import com.example.appecommercephp.model.CartModel;
import com.example.appecommercephp.model.CommentsModel;
import com.example.appecommercephp.model.MessagerModel;
import com.example.appecommercephp.model.OrderModel;
import com.example.appecommercephp.model.ProductsData;
import com.example.appecommercephp.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface ApiService {

  //    public static String ipV4 = "192.168.1.183";
      public static String ipV4 = "192.168.1.7";

//    public static String ipV4 =  "192.168.101.233";


    public static String server = "http://" + ipV4 + "/appapi/";

    HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //    THỜI GIAN TỐI ĐA ĐỂ GET ĐƯỢC API
    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30,TimeUnit.SECONDS)
//        QUÁ TRÌNH CALL API MÀ GẶP LỖI THÌ NÓ SẼ THỬ LẠI
            .retryOnConnectionFailure(true)

            .addInterceptor(HTTP_LOGGING_INTERCEPTOR);



    ApiService  API_SERVICE = new Retrofit.Builder()
            .baseUrl(server)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okBuilder.build())
            .build()
            .create(ApiService.class);

    @GET("product/getAllSmartPhone.php")
    Observable<List<ProductsData>> callApi();

    @GET("product/getAllLaptop.php")
    Observable<List<ProductsData>> CallGetLaptop();

    @FormUrlEncoded
    @POST("detailsProduct.php")
    Observable<List<CommentsModel>> detailsProduct(@Field("id") int idProduct);

    @FormUrlEncoded
    @POST("favourite.php")
   Observable<Void> addListFavourite(@Field(("idProduct")) String idProduct);

    @FormUrlEncoded
    @POST("register.php")
    Observable<ResponseBody> registerUser(@Field(("email")) String email , @Field("passwords") String pass);

    @FormUrlEncoded
    @POST("login.php")
    Observable<List<User>> loginUser(@Field(("email")) String email , @Field("passwords") String pass);

    @FormUrlEncoded
    @POST("carts/cartProducts.php")
    Observable<List<CartModel>> getCartUser(@Field("idUser") String idUser);

    @Multipart
    @POST("carts/addProductToCart.php")
    Observable<ResponseBody> addProductCart(@PartMap Map<String, RequestBody> data);


    @POST("getCommentProduct.php")
    Observable<List<CommentsModel>> getCommentProduct(@Field("idProduct") String idProduct);

    @FormUrlEncoded
    @POST("carts/getProductQuantityCart.php")
    Observable<List<String>> getProductQuantityCart(@Field("idProduct") String idProduct , @Field("idUser") String idUser);


    @FormUrlEncoded
    @POST("carts/deleteProductCart.php")
    Observable<ResponseBody> deleteItemCart(@Field("id_products") String idProduct ,
                                            @Field("id_user") String idUser);
    @FormUrlEncoded
    @POST("favourite/getAllFavourite.php")
    Observable<ResponseBody> getListFavorite(@Field("idUser") String idUser ,
                                             @Field("idProduct") String idProduct ,
                                             @Field("action") String action);

    @FormUrlEncoded
    @POST("favourite/favourite.php")

    Observable<ResponseBody> updateFavourite(@Field("idUser") String idUser ,
                                             @Field("idProduct") String idProduct ,
                                             @Field("action") String action);


    @FormUrlEncoded
    @POST("messager/messager.php")

    Observable<List<MessagerModel>> getAllMessager(@Field("id_user1") String idUser1 ,
                                                   @Field("id_user2") String idUser2);


    // RequestBody cai nay de khi ma minh gui file hoac du lieu phuc tap len server ne

    @FormUrlEncoded
    @POST("/purchase/addPurchase.php")
    Observable<Response> paymentProduct(@Body Map<String,RequestBody> objects);



    @FormUrlEncoded
    @POST("save/getAllSaveProduct.php")

    Observable<List<ProductsData>> getListSaveProduct(@Field("idUser") String idUser1);

    //  @FormUrlEncoded

    // muon gui 1 object thi bo cai  @FormUrlEncoded nay di
    @POST("order/order.php")
    Observable<ResponseBody> addOrder(@Body OrderModel order);


    @FormUrlEncoded
    @POST("order/getListOrder.php")
    Observable<ArrayList<OrderModel>> getListShoppingOrder(@Field("idUser") String idUser);


    @FormUrlEncoded
    @POST("admin/getAllListOrder.php")
    Observable<ArrayList<OrderModel>> getListOrders(@Field("idAdminShop") String idAdminShop);

    @FormUrlEncoded
    @POST("admin/updateStateOrder.php")
    Observable<ResponseBody> updateStateOrder(@Field("state") String state , @Field("id") String id);






}
