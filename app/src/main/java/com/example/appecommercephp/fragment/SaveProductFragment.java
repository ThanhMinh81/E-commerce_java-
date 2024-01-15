    package com.example.appecommercephp.fragment;

    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.Observer;
    import androidx.lifecycle.ViewModelProvider;
    import androidx.recyclerview.widget.GridLayoutManager;

    import com.example.appecommercephp.Adapter.SaveAdapter;
    import com.example.appecommercephp.databinding.FragmentSaveproductBinding;
    import com.example.appecommercephp.interfaceUI.CustomViewModelFactory;
    import com.example.appecommercephp.interfaceUI.IUpdateSave;
    import com.example.appecommercephp.model.ProductsData;
    import com.example.appecommercephp.viewmodel.SaveProductViewModel;

    import java.util.ArrayList;
    import java.util.List;

    public class SaveProductFragment extends Fragment {

        private FragmentSaveproductBinding saveproductBinding ;
        private SaveProductViewModel saveProductViewModel ;
        public static SaveAdapter mainAdapter ;
        private  IUpdateSave iUpdateSave ;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            saveproductBinding = FragmentSaveproductBinding.inflate(inflater,container,false);


            List<ProductsData> productsData1 = new ArrayList<>();

            iUpdateSave = new IUpdateSave() {
                @Override
                public void updateUI() {

                }
            };

            // Khởi tạo Factory với các dependencies cần thiết
            CustomViewModelFactory factory = new CustomViewModelFactory();

            // Tạo ViewModel thông qua FactorSaveAdaptery
            saveProductViewModel = new ViewModelProvider(this, factory).get(SaveProductViewModel.class);

            mainAdapter = new SaveAdapter(productsData1 , iUpdateSave , saveProductViewModel);


            // Khởi tạo LayoutManager với GridLayoutManager và số cột là 2

            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

            // Set LayoutManager cho RecyclerView

            saveproductBinding.rcvSave.setLayoutManager(layoutManager);
            saveproductBinding.rcvSave.setAdapter(mainAdapter);


            final Observer<List<ProductsData>> observe = new Observer<List<ProductsData>>() {

                @Override
                public void onChanged(List<ProductsData> productsData) {

                    productsData1.clear();

//                    Toast.makeText(getActivity(),"Bỏ lưu sản  phẩm thành công" , Toast.LENGTH_SHORT).show();

                    productsData1.addAll(productsData);

                    mainAdapter.notifyDataSetChanged();

                }


            };

            saveProductViewModel.getListMutableLiveData().observe(getViewLifecycleOwner(),observe);


            final Observer<String> resultRemoveSave  = new Observer<String>() {
               @Override
               public void onChanged(String s) {
                   Toast.makeText(getActivity(), "" + s, Toast.LENGTH_SHORT).show();
               }
           };

           saveProductViewModel.getResultSave().observe(getViewLifecycleOwner(),resultRemoveSave);



           return saveproductBinding.getRoot();


        }



    }
