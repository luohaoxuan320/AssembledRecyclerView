package com.lehow.assembledrecyclerview.component;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 转换构造器，子类实现ViewHolder会很清楚对应的layout和控件id的关系,自己可以被复用
 */
public abstract class ProxyViewHolder extends RecyclerView.ViewHolder {
    public ProxyViewHolder(@NonNull ViewGroup viewGroup, @LayoutRes int layoutId) {
        super(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId,viewGroup,false));
    }
}
