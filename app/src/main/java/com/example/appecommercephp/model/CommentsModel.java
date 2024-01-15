package com.example.appecommercephp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CommentsModel {

    @SerializedName("full_name_user")
    private String fullName ;
    @SerializedName("avt_img_user")
    private String imgUrl ;
    @SerializedName("content_comments")
    private String contentComment ;
    @SerializedName("ratingbar_comments")
    private String ratingbar ;
    @SerializedName("id_comment")
    private String idComment ;
    @SerializedName("url_image")
    private ArrayList<String>  imagesComment ;

    public CommentsModel(String fullName, String imgUrl, String contentComment, String ratingbar, String idComment, ArrayList<String> imagesComment) {
        this.fullName = fullName;
        this.imgUrl = imgUrl;
        this.contentComment = contentComment;
        this.ratingbar = ratingbar;
        this.idComment = idComment;
        this.imagesComment = imagesComment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public String getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(String ratingbar) {
        this.ratingbar = ratingbar;
    }

    public String getIdComment() {
        return idComment;
    }

    public void setIdComment(String idComment) {
        this.idComment = idComment;
    }

    public ArrayList<String> getImagesComment() {
        return imagesComment;
    }

    public void setImagesComment(ArrayList<String> imagesComment) {
        this.imagesComment = imagesComment;
    }

    @BindingAdapter({"imageUrlCmt"})
    public static void loadImage(ImageView view, String url)
    {
        Glide.with(view.getContext()).load(url).into(view);
    }

}
