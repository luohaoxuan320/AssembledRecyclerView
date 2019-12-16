package com.lehow.assembledrecyclerview.add_order;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.lehow.assembledrecyclerview.component.ProxyViewAdapter;

public class AddressViewAdapter extends ProxyViewAdapter<AddressModel, AddressViewHolder> {

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder viewHolder, AddressModel entity) {
        OrderViewModel orderViewModel = getViewModel(viewHolder, OrderViewModel.class);
        Log.i("TAG", "onBindViewHolder: "+orderViewModel.getName());;
    }
}
