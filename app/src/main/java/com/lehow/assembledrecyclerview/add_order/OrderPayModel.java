package com.lehow.assembledrecyclerview.add_order;


import com.lehow.assembledrecyclerview.component.ProxyAdapterModel;

import java.util.ArrayList;

public class OrderPayModel extends ProxyAdapterModel<OrderPayViewAdapter> {
    private ArrayList<String> sendTypes;
    private boolean needList;
    private ArrayList<String> payTypes;

    public ArrayList<String> getSendTypes() {
        return sendTypes;
    }

    public void setSendTypes(ArrayList<String> sendTypes) {
        this.sendTypes = sendTypes;
    }

    public boolean isNeedList() {
        return needList;
    }

    public void setNeedList(boolean needList) {
        this.needList = needList;
    }

    public ArrayList<String> getPayTypes() {
        return payTypes;
    }

    public void setPayTypes(ArrayList<String> payTypes) {
        this.payTypes = payTypes;
    }

}
