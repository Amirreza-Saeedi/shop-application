package com.example.shopapplication;

public class StorageProperty {
    private int id;
    private int storageId;
    private String daily;
    private String monthly;
    private String yearly;
    private int amount;
    private double value;

    public StorageProperty(int id, int storageId, String daily, String monthly, String yearly, int amount, double value) {
        this.id = id;
        this.storageId = storageId;
        this.daily = daily;
        this.monthly = monthly;
        this.yearly = yearly;
        this.amount = amount;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getStorageId() {
        return storageId;
    }

    public String getDaily() {
        return daily;
    }

    public String getMonthly() {
        return monthly;
    }

    public String getYearly() {
        return yearly;
    }

    public int getAmount() {
        return amount;
    }

    public double getValue() {
        return value;
    }
}
