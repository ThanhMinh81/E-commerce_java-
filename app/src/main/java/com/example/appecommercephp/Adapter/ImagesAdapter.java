package com.example.appecommercephp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ItemImageCommentBinding;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private ArrayList<String> strings ;

    public ImagesAdapter(ArrayList<String> strings) {
        this.strings = strings;
    }

    @NonNull
    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemImageCommentBinding itemImageCommentBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_image_comment
                ,parent,false);

        return new ViewHolder(itemImageCommentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesAdapter.ViewHolder holder, int position) {
         String s = strings.get(position);

         holder.itemImageCommentBinding.setImgUrl(s);

    }

    @Override
    public int getItemCount() {
        return strings.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemImageCommentBinding itemImageCommentBinding ;
        public ViewHolder(ItemImageCommentBinding itemImageCommentBinding) {
            super(itemImageCommentBinding.getRoot());
            this.itemImageCommentBinding = itemImageCommentBinding ;
        }
    }
}
