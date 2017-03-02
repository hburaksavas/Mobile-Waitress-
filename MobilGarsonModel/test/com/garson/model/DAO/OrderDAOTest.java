/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Order;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class OrderDAOTest {

    public OrderDAOTest() {
    }

    /**
     * Test of addOrder method, of class OrderDAO.
     */
    @Test
    public void testAddOrder() {
        System.out.println("addOrder");
        Order order = new Order()
                .setMasaid(1l)
                .setPiece(4)
                .setRestaurantid(1l)
                .setStatu("Beklemede")
                .setUrunid(1l)
                .setClock(DAO.getCurrentClock())
                .setDate(DAO.getCurrentDate());
        
        OrderDAO instance = new OrderDAO();
        Order expResult = null;
        Order result = instance.addOrder(order);
    }

    /**
     * Test of updateOrder method, of class OrderDAO.
     */
//    @Test
//    public void testUpdateOrder() {
//        System.out.println("updateOrder");
//
//        OrderDAO instance = new OrderDAO();
//        Order order = instance.getByID(2l);
//        order.setStatu("Gönderildi");
//        order.setClock(DAO.getCurrentClock());
//        Order result = instance.updateOrder(order);
//    }

    /**
     * Test of deleteOrder method, of class OrderDAO.
     */
//    @Test
//    public void testDeleteOrder() {
//        System.out.println("deleteOrder");
//        long orderid = 1L;
//        OrderDAO instance = new OrderDAO();
//        boolean result = instance.deleteOrder(orderid);
//    }

    /**
     * Test of deleteOrdersByTableId method, of class OrderDAO.
     */
//    @Test
//    public void testDeleteOrdersByTableId() {
//        System.out.println("deleteOrdersByTableId");
//        long tableid = 2L;
//        OrderDAO instance = new OrderDAO();
//        boolean result = instance.deleteOrdersByTableId(tableid);
//    }

    /**
     * Test of deleteRestaurantOrders method, of class OrderDAO.
     */
//    @Test
//    public void testDeleteRestaurantOrders() {
//        System.out.println("deleteRestaurantOrders");
//        long restaurantid = 1L;
//        OrderDAO instance = new OrderDAO();
//        boolean result = instance.deleteRestaurantOrders(restaurantid);
//    }
    /**
     * Test of getRestaurantOrders method, of class OrderDAO.
     */
    @Test
    public void testGetRestaurantOrders() {
        System.out.println("getRestaurantOrders");
        long restaurantid = 1L;
        OrderDAO instance = new OrderDAO();
        List<Order> result = instance.getRestaurantOrders(restaurantid);

        result.forEach(x -> System.out.println(x.getMasaid() + " " + x.getClock()));
    }

    /**
     * Test of getRestaurantOrderByDinnerTableid method, of class OrderDAO.
     */
    @Test
    public void testGetRestaurantOrderByDinnerTableid() {
        System.out.println("getRestaurantOrderByDinnerTableid");
        long restaurantid = 1L;
        long tableid = 1L;
        OrderDAO instance = new OrderDAO();
        List<Order> expResult = null;
        List<Order> result = instance.getRestaurantOrderByDinnerTableid(restaurantid, tableid);
        result.forEach(x -> System.out.println(x.getMasaid() + " " + x.getClock()));
    }

    /**
     * Test of getRestaurantOrdersByStatu method, of class OrderDAO.
     */
    @Test
    public void testGetRestaurantOrdersByStatu() {
        System.out.println("getRestaurantOrdersByStatu");
        long restaurantid = 1L;
        String statu = "Beklemede";
        OrderDAO instance = new OrderDAO();
        List<Order> expResult = null;
        List<Order> result = instance.getRestaurantOrdersByStatu(restaurantid, statu);

    }

    /**
     * Test of getByID method, of class OrderDAO.
     */
    @Test
    public void testGetByID() {
        System.out.println("getByID");
        long id = 20L;
        OrderDAO instance = new OrderDAO();
        Order expResult = null;
        Order result = instance.getByID(id);
        System.out.println(result);
    }

}
