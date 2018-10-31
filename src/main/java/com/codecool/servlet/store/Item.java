package com.codecool.servlet.store;

public class Item {
    private static int count = 0;
    private int id;
    private String name;
    private double price;

    public Item(String name, double price){
        setId(ItemStore.store.size());
        setName(name);
        setPrice(price);
        ItemStore.store.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
