package com.lehow.assembledrecyclerview.add_order;

import androidx.lifecycle.ViewModel;

public class OrderViewModel extends ViewModel {

    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
