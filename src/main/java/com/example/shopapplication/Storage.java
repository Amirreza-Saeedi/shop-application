package com.example.shopapplication;

public class Storage {
    Integer row;
    Integer id;
    String manager;
    String name;
    Integer amount;
    String address;
    Integer value;


    @Override
    public String toString() {
        return "row = " + row + ",id = " + id + ",name = " + name + ",amount = " + amount + ", manager = " + manager + ", address = " + address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getManager() {
        return manager;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public Storage(String name, Integer row, Integer id, Integer amount, Integer value, String address, String manager) {
        this.name = name;
        this.row = row;
        this.id = id;
        this.amount = amount;
        this.address = address;
        this.manager = manager;
        this.value = value;
    }
}
