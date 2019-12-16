package com.lehow.assembledrecyclerview.add_order;


import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lehow.assembledrecyclerview.R;
import com.lehow.assembledrecyclerview.component.ProxyViewHolder;

public class OrderListVH extends ProxyViewHolder {
    private RecyclerView recyclerView;
    public OrderListVH(@NonNull ViewGroup viewGroup) {
        super(viewGroup, R.layout.item_order_list_vh);
        recyclerView = itemView.findViewById(R.id.recycler_view_inner);
        Log.i("TAG", "OrderListVH: "+viewGroup.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(viewGroup.getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setRecycleChildrenOnDetach(true);//RecyclerView从视图树移出时回收
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);

    }
}
