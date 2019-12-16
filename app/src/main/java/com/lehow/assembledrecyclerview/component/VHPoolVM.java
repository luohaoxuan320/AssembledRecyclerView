package com.lehow.assembledrecyclerview.component;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 管理ViewHolder 的itemType，避免冲突，同时共享一个recycledViewPool
 * 这个对象的作用域就是当前View（Activity或者Fragment级别的
 * 当前的recycledViewPool 和ItemType是跟当前View（Activity或者Fragment）绑定的，避免嵌套时传参麻烦，以及全局单例导致recycledViewPool过大
 */
public class VHPoolVM extends ViewModel {
    private ArrayList<Class<? extends ProxyViewHolder>> vhTypes = new ArrayList<>();//自动分配ViewHolder的itemType，保证itemType不冲突

    private RecyclerView.RecycledViewPool recycledViewPool=new RecyclerView.RecycledViewPool();

    public int getViewHolderType(Class<? extends ProxyViewHolder> viewHolderClass) {
        String canonicalName = viewHolderClass.getCanonicalName();
        int indexOf = vhTypes.indexOf(viewHolderClass);
        if(indexOf ==-1){
            vhTypes.add(viewHolderClass);
            return vhTypes.size()-1;
        }else{
            return indexOf;
        }
    }


    public  <V extends ProxyViewHolder> V onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        try {
            return (V) vhTypes.get(viewType).getConstructor(ViewGroup.class).newInstance(viewGroup);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(" ProxyViewHolder 与ViewHolder不符合定义的约束规则，请检查");
    }
    public RecyclerView.RecycledViewPool getRecycledViewPool() {
        return recycledViewPool;
    }


    /**
     * 当前界面跨或者嵌套RecyclerView，同类型的data 共享一个ViewAdapter的能力
     * TODO: 这里可以考虑用LruCache来优化这个缓存
     */
    private HashMap<String, ProxyViewAdapter> viewAdapterMap = new HashMap<>();

    /**
     * 处理ViewAdapter对象实例的缓存
     * @param adapterModel
     * @return
     */
    private ProxyViewAdapter getModelViewAdapter(IAdapterModel adapterModel){
        String dataCanonicalName =  adapterModel.getClass().getCanonicalName();
        ProxyViewAdapter proxyViewAdapter = viewAdapterMap.get(dataCanonicalName);
        if (proxyViewAdapter == null) {
            proxyViewAdapter = adapterModel.getViewAdapter();
            viewAdapterMap.put(dataCanonicalName, proxyViewAdapter);
        }
        return proxyViewAdapter;
    }

    @Override
    protected void onCleared() {
        viewAdapterMap.clear();
        vhTypes.clear();
        super.onCleared();
    }
}
