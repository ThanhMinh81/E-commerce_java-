package com.example.appecommercephp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appecommercephp.databinding.FragmentUpdateStateBinding;
import com.example.appecommercephp.interfaceUI.IGetValueUpdateState;
import com.example.appecommercephp.model.ItemOrder;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetUpdateStateFragment extends BottomSheetDialogFragment {

    FragmentUpdateStateBinding binding ;
    IGetValueUpdateState iGetValueUpdateState ;


    ItemOrder itemOrder ;

    public BottomSheetUpdateStateFragment(IGetValueUpdateState iGetValueUpdateState , ItemOrder itemOrder) {
        this.iGetValueUpdateState = iGetValueUpdateState;
        this.itemOrder = itemOrder ;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        binding = FragmentUpdateStateBinding.inflate(inflater,container,false);

        binding.spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = (String) parent.getItemAtPosition(position);
//                selector = selectedValue;
                itemOrder.setStateOrder(selectedValue);
                Log.d("oihfsdo",selectedValue.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.updateState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Trả về dữ liệu cho activity gọi
//                Intent intent = new Intent();
                iGetValueUpdateState.getStateItemOrder(itemOrder);
//                intent.putExtra("result_key", selector);
//                getActivity().setResult(Activity.RESULT_OK, intent);
                dismiss();

            }
        });

        binding.cancelState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return binding.getRoot();

    }

}
