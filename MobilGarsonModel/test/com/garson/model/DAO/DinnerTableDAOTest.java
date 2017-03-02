/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.DinnerTable;
import java.util.List;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class DinnerTableDAOTest {
    
    public DinnerTableDAOTest() {
    }

    /**
     * Test of addDinnerTable method, of class DinnerTableDAO.
     */
    @Test
    public void testAddDinnerTable() {
        System.out.println("addDinnerTable");
        DinnerTable table = new DinnerTable().setCapacity(10)
                .setName("A4")
                .setRestaurantid(1l)
                .setStatu(0);
        
        DinnerTableDAO instance = new DinnerTableDAO();
        DinnerTable result = instance.addDinnerTable(table);
        
        if(result == null){fail();}
    }


    @Test
    public void testUpdateDinnerTable() {
        System.out.println("updateDinnerTable");
               DinnerTable table = new DinnerTable().setCapacity(10)
                .setName("A5")
                .setRestaurantid(1l)
                .setStatu(1);
               table.setId(1l);
               
        DinnerTableDAO instance = new DinnerTableDAO();
        DinnerTable result = instance.updateDinnerTable(table);

    }

    @Test
    public void testDeleteDinnerTable()
    {
        System.out.println("deleteDinnerTable");
        DinnerTable table = null;
        DinnerTableDAO instance = new DinnerTableDAO();
        boolean result = instance.deleteDinnerTable(23);

    }

    /**
     * Test of getRestaurantTables method, of class DinnerTableDAO.
     */
    @Test
    public void testGetRestaurantTables() {
        System.out.println("getRestaurantTables");
        long restaurantID = 1;
        DinnerTableDAO instance = new DinnerTableDAO();
        List<DinnerTable> expResult = null;
        List<DinnerTable> result = instance.getRestaurantTables(restaurantID);

        result.forEach(x-> System.out.println(x.getName()));
        
    }

    /**
     * Test of getRastauranTablesByStatu method, of class DinnerTableDAO.
     */
    @Test
    public void testGetRastauranTablesByStatu() {
        System.out.println("getRastauranTablesByStatu");
        long restaurantID = 1;
        int statu = 0;
        DinnerTableDAO instance = new DinnerTableDAO();
        List<DinnerTable> expResult = null;
        List<DinnerTable> result = instance.getRastauranTablesByStatu(restaurantID, statu);
               result.forEach(x-> System.out.println(x.getName()));
    }

    /**
     * Test of getByID method, of class DinnerTableDAO.
     */
    @Test
    public void testGetByID() {
        
        System.out.println("getByID");
        long id = 24l;
        DinnerTableDAO instance = new DinnerTableDAO();
        DinnerTable expResult = null;
        DinnerTable result = instance.getByID(id);

        System.out.println(result.getName());
    }
    
}
