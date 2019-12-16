package com.lehow.assembledrecyclerview.add_order;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lehow.assembledrecyclerview.R;
import com.lehow.assembledrecyclerview.component.ProxyViewHolder;


public class AddressViewHolder extends ProxyViewHolder {
    TextView tv_good_price;
    public AddressViewHolder(@NonNull ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_order_address);
    }

}
