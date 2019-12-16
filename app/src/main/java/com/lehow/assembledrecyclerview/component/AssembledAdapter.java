package com.lehow.assembledrecyclerview.component;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 抽象出来的基类
 * 负责分发事件，调度缓存，至于IAdapterModel数据集的定义是List还是其他的类型交给子类去实现
 *
 *      * 如果只有一种类型的ViewHolder，可以直接指定重写指定 getItemViewType
 *      * vhPoolVM.getViewHolderType(InnerHolder.class);
 */
public abstract class AssembledAdapter extends RecyclerView.Adapter<ProxyViewHolder> {

    protected VHPoolVM vhPoolVM;
    @NonNull
    @Override
    public ProxyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        //根据viewType 获取ViewHolder实例
        return vhPoolVM.onCreateViewHolder(viewGroup,viewType);
    };

    @Override
    public void onBindViewHolder(@NonNull ProxyViewHolder viewHolder, int i) {
        vhPoolVM.getModelViewAdapter(getAdapterModel(i)).onBindViewHolder(viewHolder, getAdapterModel(i));
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //读取当前页面的VHPoolVM 设置recyclerView的共享RecycledViewPool
        if (recyclerView.getContext() instanceof FragmentActivity) {
            vhPoolVM = ViewModelProviders.of((FragmentActivity) recyclerView.getContext()).get(VHPoolVM.class);
            recyclerView.setRecycledViewPool(vhPoolVM.getRecycledViewPool());
        }else {
            throw new IllegalArgumentException(recyclerView.getContext() + " 必须 是FragmentActivity 的子类");
        }
    }


    /**
     * 如果只有一种类型的ViewHolder，可以直接指定重写指定 getItemViewType
     * vhPoolVM.getViewHolderType(InnerHolder.class);
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //根据数据Model获取对应的ViewHolder的type id
        return vhPoolVM.getViewHolderTypeByModel(getAdapterModel(position));
    }

    public abstract IAdapterModel getAdapterModel(int position);
}
