package com.example.appecommercephp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.model.CommentsModel;
import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ItemCommentBinding;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {


    private ArrayList<CommentsModel> productsData ;
    private  Context context ;

    public CommentAdapter(ArrayList<CommentsModel> productsData) {
        this.productsData = productsData;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCommentBinding itemCommentBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_comment,parent,false);
        return new ViewHolder(itemCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        CommentsModel commentsModel = productsData.get(position);
        holder.itemCommentBinding.setCommentsModel(commentsModel);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1, RecyclerView.HORIZONTAL, false);

        ImagesAdapter imagesAdapter = new ImagesAdapter(commentsModel.getImagesComment());
        holder.itemCommentBinding.rcvimg.setAdapter(imagesAdapter);
        holder.itemCommentBinding.rcvimg.setLayoutManager(gridLayoutManager);

    }

    @Override
    public int getItemCount() {
        return productsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCommentBinding itemCommentBinding ;

        public ViewHolder(ItemCommentBinding itemCommentBinding) {
            super(itemCommentBinding.getRoot());
            this.itemCommentBinding = itemCommentBinding ;
        }
    }
}
