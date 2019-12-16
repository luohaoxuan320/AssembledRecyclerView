package com.lehow.assembledrecyclerview.component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 数据视图模型，抽象的Adapter数据的基类
 * @param <V>
 */
public abstract class ProxyAdapterModel<V extends ProxyViewAdapter> implements IAdapterModel {

    public V getViewAdapter() {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class<V> vClass = (Class<V>) actualTypeArguments[0];
            try {
                return vClass.getConstructor().newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            throw new IllegalArgumentException(this.getClass() + " 通过泛型约束的ProxyViewAdapter 子类 需要默认无参构造");

        } else {
            throw new IllegalArgumentException(this.getClass() + " 必须通过泛型约束ProxyViewAdapter 子类");
        }
    };
}
