package com.example.appecommercephp.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.appecommercephp.databinding.FragmentBottomSheetBinding;
import com.example.appecommercephp.model.ProductsData;
import com.example.appecommercephp.viewmodel.BottomSheetViewModel;
import com.example.appecommercephp.viewmodel.BottomSheetViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    FragmentBottomSheetBinding fragmentBottomSheetBinding ;

    ProductsData productsData ;
    Context context ;



    public BottomSheetFragment(ProductsData productsData , Context context) {
        this.productsData = productsData;
        this.context = context ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


         fragmentBottomSheetBinding = FragmentBottomSheetBinding.inflate(inflater,container,false);
         fragmentBottomSheetBinding.setProducts(productsData);

//         BottomSheetViewModel bottomSheetViewModel = new BottomSheetViewModel(getContext());

BottomSheetViewModel bottomSheetViewModel =
        new ViewModelProvider(this,new BottomSheetViewModelFactory(this.getActivity()))
                .get(BottomSheetViewModel.class);



         fragmentBottomSheetBinding.setBottomSheetViewModel(bottomSheetViewModel);
//         bottomSheetViewModel.stringMutableLiveData.observe(this, s -> {
//
//               Log.d("fsfasodfoasfasfasfasfas",s);
//               Toast.makeText(getContext(), "" + s, Toast.LENGTH_SHORT).show();
//
//         });

        bottomSheetViewModel.getStringMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(context, "Thêm vào giỏ hàng " + s, Toast.LENGTH_SHORT).show();
            }
        });

         if(!HomeFragment.idUser.contains("ko_co_id"))
         {
             bottomSheetViewModel.getQuantityCart(String.valueOf(productsData.getIdProduct()),HomeFragment.idUser);
         }

         return fragmentBottomSheetBinding.getRoot();

    }

    @BindingAdapter({"loadImageProductCard" ,  "loadImageFalse"})
    public static void loadImage(ImageView view, String url , Drawable error)
    {
       if(url != null)
       {
           Glide.with(view.getContext()).load(url).into(view);
       }else {
           ImageView imageView = (ImageView) view;
           imageView.setImageDrawable(error);
       }
    }


}