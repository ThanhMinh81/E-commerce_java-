package com.example.appecommercephp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appecommercephp.R;
import com.example.appecommercephp.databinding.ItemSaveproductBinding;
import com.example.appecommercephp.interfaceUI.IUpdateSave;
import com.example.appecommercephp.model.ProductsData;
import com.example.appecommercephp.viewmodel.SaveProductViewModel;

import java.util.List;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder> {

    private List<ProductsData> listData ;
    private IUpdateSave  iUpdateSave ;

    private SaveProductViewModel saveProductViewModel ;


    public SaveAdapter(List<ProductsData> listData , IUpdateSave  iUpdateSave  ,SaveProductViewModel saveProductViewModel  ) {
        this.listData = listData ;
        this.iUpdateSave = iUpdateSave ;
//        this.saveProductViewModel = new SaveProductViewModel()
        this.saveProductViewModel = saveProductViewModel ;
    }

    @NonNull
    @Override
    public SaveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemSaveproductBinding saveproductBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_saveproduct,parent,false);


          return  new SaveAdapter.ViewHolder(saveproductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SaveAdapter.ViewHolder holder, int position) {

            ProductsData data = listData.get(position) ;

            holder.saveproductBinding.setProducts(data);
            holder.saveproductBinding.iconSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveProductViewModel.removeProductSave(String.valueOf(data.getIdProduct()));
                }
            });


    }

    @Override
    public int getItemCount() {

        if(listData != null)

        {
            return listData.size();
        }

        return 0 ;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemSaveproductBinding saveproductBinding ;

        public ViewHolder(ItemSaveproductBinding saveproductBinding) {

            super(saveproductBinding.getRoot());

            this.saveproductBinding = saveproductBinding ;

        }
    }
}
