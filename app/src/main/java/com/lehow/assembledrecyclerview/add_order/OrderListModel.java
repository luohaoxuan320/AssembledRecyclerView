package com.lehow.assembledrecyclerview.add_order;


import com.lehow.assembledrecyclerview.component.ProxyAdapterModel;

import java.util.ArrayList;

public class OrderListModel extends ProxyAdapterModel<OrderListVIewAdapter> {
    private ArrayList<Group> groups;
    private String summary;

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public OrderListVIewAdapter getViewAdapter() {
        return new OrderListVIewAdapter();
    }

    public static class Group{
        private String name;
        private ArrayList<Item> items;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public void setItems(ArrayList<Item> items) {
            this.items = items;
        }
    }

    public static class Item{
        private String name;
        private String price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
