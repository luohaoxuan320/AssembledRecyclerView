package com.lehow.assembledrecyclerview.add_order;


import com.lehow.assembledrecyclerview.component.ProxyAdapterModel;

public class AddressModel extends ProxyAdapterModel<AddressViewAdapter> {
    private String name;
    private String phone;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

