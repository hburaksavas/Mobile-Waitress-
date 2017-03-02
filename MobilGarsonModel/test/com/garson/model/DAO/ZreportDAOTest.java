/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Bill;
import com.garson.model.entity.Zreport;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class ZreportDAOTest
  {
    
    public ZreportDAOTest()
      {
      }

    @Test
    public void testGetRestaurantZReports()
      {
        System.out.println("getRestaurantZReports");
        long restaurantid = 1L;
        ZreportDAO instance = new ZreportDAO();
        List<Zreport> expResult = null;
        Zreport result = instance.getZreportById(restaurantid);
        
        List<Bill> bills = DAO.getListFromJSON(Bill[].class, result.getBills());
        
        bills.forEach(x-> System.out.println(x.getId()+" "+x.getDate()+" "+x.getOrders()));
        
      }
    
  }
