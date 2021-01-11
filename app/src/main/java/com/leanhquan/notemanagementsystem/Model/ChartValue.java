package com.leanhquan.notemanagementsystem.Model;

public class ChartValue {
    private String name;
    private int value;

    public ChartValue() {
    }

    public ChartValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
