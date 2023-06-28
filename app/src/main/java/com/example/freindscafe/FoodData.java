package com.example.freindscafe;

public class FoodData {

    String name;
    String mobile;
    String table;
   Long bill;
    String date;

    public String getname() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getdate() {
        return date;
    }

    public String getBill() {
        return bill.toString();
    }

    public String getTable() {
        return table;
    }

    public FoodData() {
    }

    public FoodData(String name,String date,String bill,String table) {
        this.name = name;
        this.mobile=getMobile();
        this.date=date;
        this.bill= Long.parseLong(bill);
        this.table=table;

    }
}
