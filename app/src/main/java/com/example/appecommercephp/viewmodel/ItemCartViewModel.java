package com.example.appecommercephp.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;

import com.example.appecommercephp.model.ItemCartModel;

import java.util.ArrayList;

public class ItemCartViewModel extends BaseObservable {

    public ObservableArrayList<ItemCartModel> itemCartViewModels = new ObservableArrayList<>();

    public ItemCartViewModel( ) {
//         this.itemCartViewModels.addAll(itemCartViewModels);
    }

    public ObservableArrayList<ItemCartModel> getItemCartViewModels() {
        return itemCartViewModels;
    }

    public void setItemCartViewModels(ObservableArrayList<ItemCartModel> itemCartViewModels) {
        this.itemCartViewModels = itemCartViewModels;
    }

    public ArrayList<ItemCartModel> getAllListCart()
    {
        ArrayList<ItemCartModel> itemCartModels = new ArrayList<>();
        for (int i = 0 ; i < itemCartViewModels.size() ; i++)
        {
            if(itemCartViewModels.get(i).isCheck() == true)
            {
                itemCartModels.add(itemCartViewModels.get(i));
            }
        }
        return itemCartModels ;
    }

}
