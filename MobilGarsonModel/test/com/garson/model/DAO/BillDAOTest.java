/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Bill;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class BillDAOTest {

    public BillDAOTest() {
    }

    /**
     * Test of addBill method, of class BillDAO.
     */
    @Test
    public void testAddBill() {
        System.out.println("addBill");
        long dinnertableid = 1L;
        long restaurantid = 1L;
        BillDAO instance = new BillDAO();
        Bill expResult = null;
        Bill result = instance.addBill(dinnertableid, restaurantid);

    }

    /**
     * Test of updateBill method, of class BillDAO.
     */
    @Test
    public void testUpdateBill() {
        System.out.println("updateBill");
        Bill bill = null;
        BillDAO instance = new BillDAO();
        Bill expResult = null;
        Bill result = instance.updateBill(bill);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteBill method, of class BillDAO.
     */
    @Test
    public void testDeleteBill() {
        System.out.println("deleteBill");
        long id = 0L;
        BillDAO instance = new BillDAO();
        boolean expResult = false;
        boolean result = instance.deleteBill(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRestaurantBills method, of class BillDAO.
     */
//    @Test
//    public void testGetRestaurantBills() {
//
//        System.out.println("getRestaurantBills");
//        long restaurantid = 1L;
//        BillDAO instance = new BillDAO();
//        List<Bill> expResult = null;
//        List<Bill> result = instance.getRestaurantBills(restaurantid);
//
//        System.out.println(result.get(0).getOrders());
//
//        List<Order> order = BillDAOTest.getList(Order[].class, result.get(0).getOrders());
//        order.forEach(x-> System.out.println(x.getDate()));
        
//        Gson gson = new Gson();
//        Type listType = new TypeToken<ArrayList<Order>>(){}.getType();
//        List<Order> list ;
//        Order[] orderr = gson.fromJson(result.get(0).getOrders(), Order[].class);
//        list = Arrays.asList(orderr);
//        list.forEach(x-> System.out.println("ÜRÜNİDİD:::"+x.getUrunid()));
    


}
