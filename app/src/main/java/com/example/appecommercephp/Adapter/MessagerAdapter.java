package com.example.appecommercephp.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ItemChatBinding;
import com.example.appecommercephp.fragment.HomeFragment;
import com.example.appecommercephp.model.ChatModel;
import com.example.appecommercephp.model.MessagerModel;

import java.util.ArrayList;

public class MessagerAdapter extends RecyclerView.Adapter<MessagerAdapter.ViewHolder> {

    private ItemChatBinding itemChatBinding ;
    private  ObservableArrayList<ChatModel>   messagerModels ;

    public MessagerAdapter( ObservableArrayList<ChatModel> messagerModels) {
        this.messagerModels = messagerModels;
    }

    @NonNull
    @Override
    public MessagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemChatBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_chat,parent,false);
        return new ViewHolder(itemChatBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagerAdapter.ViewHolder holder, int position) {
        ChatModel messagerModel = messagerModels.get(position);

       if(HomeFragment.idUser.equals(String.valueOf(messagerModel.getIdUser())))
       {

           holder.itemChatBinding.left.setVisibility(View.GONE);
           holder.itemChatBinding.right.setVisibility(View.VISIBLE);
           holder.itemChatBinding.setChatModel(messagerModel);

       }else  {

           holder.itemChatBinding.left.setVisibility(View.VISIBLE);
           holder.itemChatBinding.right.setVisibility(View.GONE);
           holder.itemChatBinding.setChatModel(messagerModel);

       }
    }

    @Override
    public int getItemCount() {
        return messagerModels != null ? messagerModels.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemChatBinding itemChatBinding ;

        public ViewHolder(@NonNull  ItemChatBinding itemChatBinding) {
            super(itemChatBinding.getRoot());
            this.itemChatBinding = itemChatBinding ;
        }
    }
}
