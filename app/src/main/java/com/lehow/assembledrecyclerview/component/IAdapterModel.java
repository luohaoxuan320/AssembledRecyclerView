package com.lehow.assembledrecyclerview.component;

/**
 * 数据视图模型，抽象的子Adapter数据的基类
 *
 * @param <V>
 */
public interface IAdapterModel<V extends ProxyViewAdapter> {

     V getViewAdapter() ;
}
