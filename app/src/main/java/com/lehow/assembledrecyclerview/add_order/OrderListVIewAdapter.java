package com.lehow.assembledrecyclerview.add_order;


import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.lehow.assembledrecyclerview.R;
import com.lehow.assembledrecyclerview.component.AssembledAdapter;
import com.lehow.assembledrecyclerview.component.IAdapterModel;
import com.lehow.assembledrecyclerview.component.ProxyViewAdapter;
import com.lehow.assembledrecyclerview.component.ProxyViewHolder;
public class OrderListVIewAdapter extends ProxyViewAdapter<OrderListModel,OrderListVH,Void> {

    @Override
    public void onBindViewHolder(@NonNull OrderListVH viewHolder, OrderListModel entity) {
        viewHolder.setAdapter(new InnerAdapter2());
    }


    class InnerAdapter2 extends AssembledAdapter {

        @Override
        public IAdapterModel getAdapterModel(int position) {
            return null;
        }

        @NonNull
        @Override
        public ProxyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            //如果不重写这个方法，那么内部类的InnerHolder必须是public static的，
            // 否则反射的时候无法直接调用构造器构造对象，因为内部类实例化必须依赖外部类对象的存在
            //return super.onCreateViewHolder(viewGroup, viewType);
            //可以重写,直接new 返回
            return new InnerHolder(viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull ProxyViewHolder viewHolder, int i) {
            //getAdapterModel 返回null，注释掉此处，否则报错
            //super.onBindViewHolder(viewHolder, i);
        }

        @Override
        public int getItemViewType(int position) {
            //只有一中类型的ViewHolder可以直接指定
            return vhPoolVM.getViewHolderType(InnerHolder.class);
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }


  public static class InnerHolder extends ProxyViewHolder {

        public InnerHolder(@NonNull ViewGroup viewGroup) {
            super(viewGroup,R.layout.item_order_list_inner);
        }
    }
}

