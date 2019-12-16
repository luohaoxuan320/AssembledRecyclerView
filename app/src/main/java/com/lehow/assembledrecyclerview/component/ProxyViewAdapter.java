package com.lehow.assembledrecyclerview.component;

import android.util.Log;

import androidx.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 将数据AdapterModel映射到ViewHolder上，与数据 强相关
 * @param <P>
 * @param <V>
 * @param <R>
 */
public abstract class ProxyViewAdapter<P,V extends ProxyViewHolder,R> {

    private Class<V> classViewHolder;

    /**
     * 若里面嵌套了RecyclerView，最好不要在onBindViewHolder这个方法里new Adapter。
     * @param viewHolder
     * @param entity
     */
    public abstract void onBindViewHolder(@NonNull V viewHolder, P entity);

    public Class<V> getVHType(){
        if (classViewHolder == null) {
            Log.i("TAG", "getVHType: "+this.getClass());
            Type genericSuperclass = this.getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                classViewHolder = (Class<V>) actualTypeArguments[1];
            } else {
                throw new IllegalArgumentException(this.getClass() + " 必须通过泛型约束要适配显示的ProxyViewHolder 子类");
            }
        }
        return classViewHolder;
    }
}
