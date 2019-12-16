package com.lehow.assembledrecyclerview.add_order;


import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.lehow.assembledrecyclerview.R;
import com.lehow.assembledrecyclerview.component.ProxyViewAdapter;
import com.lehow.assembledrecyclerview.component.ProxyViewHolder;
import com.lehow.assembledrecyclerview.component.VHPoolVM;

public class OrderListVIewAdapter extends ProxyViewAdapter<OrderListModel,OrderListVH, Void> {

    @Override
    public void onBindViewHolder(@NonNull OrderListVH viewHolder, OrderListModel entity) {
        viewHolder.setAdapter(new InnerAdapter());
    }

    class InnerAdapter extends RecyclerView.Adapter<InnerHolder>{
        private VHPoolVM vhPoolVM;
        @NonNull
        @Override
        public InnerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new InnerHolder(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull InnerHolder innerHolder, int i) {
        }


       @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            recyclerView.getContext();
            Log.i("TAG", "onAttachedToRecyclerView: "+recyclerView.getContext());
            if (recyclerView.getContext() instanceof FragmentActivity) {
                vhPoolVM = ViewModelProviders.of((FragmentActivity) recyclerView.getContext()).get(VHPoolVM.class);
                recyclerView.setRecycledViewPool(vhPoolVM.getRecycledViewPool());
            }else {
                throw new IllegalArgumentException(recyclerView.getContext() + " 必须 是FragmentActivity 的子类");
            }
        }

        @Override
        public int getItemViewType(int position) {
            return vhPoolVM.getViewHolderType(InnerHolder.class);
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }



    class InnerHolder extends ProxyViewHolder {

        public InnerHolder(@NonNull ViewGroup viewGroup) {
            super(viewGroup, R.layout.item_order_list_inner);
        }
    }
}
