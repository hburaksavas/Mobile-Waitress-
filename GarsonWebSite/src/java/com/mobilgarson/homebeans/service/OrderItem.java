package com.mobilgarson.homebeans.service;

import java.io.Serializable;

public class OrderItem implements Serializable
{

    int orderCount;
    long orderNo;
    long tableNo;
    String tableName;
    String productName;
    String clock;
    String statu;

    public OrderItem()
    {
    }

    public OrderItem(int orderCount, long orderNo, long tableNo, String tableName, String productName, String clock, String statu)
    {
        this.orderCount = orderCount;
        this.orderNo = orderNo;
        this.tableNo = tableNo;
        this.tableName = tableName;
        this.productName = productName;
        this.clock = clock;
        this.statu = statu;
    }

    public int getOrderCount()
    {
        return orderCount;
    }

    public void setOrderCount(int orderCount)
    {
        this.orderCount = orderCount;
    }

    public long getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(long orderNo)
    {
        this.orderNo = orderNo;
    }

    public long getTableNo()
    {
        return tableNo;
    }

    public void setTableNo(long tableNo)
    {
        this.tableNo = tableNo;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getClock()
    {
        return clock;
    }

    public void setClock(String clock)
    {
        this.clock = clock;
    }

    public String getStatu()
    {
        return statu;
    }

    public void setStatu(String statu)
    {
        this.statu = statu;
    }

}
