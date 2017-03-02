/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.RestaurantAdmin;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class RestaurantAdminDAOTest
{
    
    public RestaurantAdminDAOTest()
    {
    }

    /**
     * Test of addAdmin method, of class RestaurantAdminDAO.
     */
//    @Test
//    public void testAddAdmin()
//    {
//        System.out.println("addAdmin");
//        RestaurantAdmin admin = new RestaurantAdmin().setMail("hburaksaavass").setPassword("1234").setRestaurantid(1);
//        RestaurantAdminDAO instance = new RestaurantAdminDAO();
//        RestaurantAdmin expResult = null;
//        RestaurantAdmin result = instance.addAdmin(admin);
//
//    }

    /**
     * Test of updateAdmin method, of class RestaurantAdminDAO.
     */
    @Test
    public void testUpdateAdmin()
    {
        System.out.println("updateAdmin");
        RestaurantAdmin admin = null;
        RestaurantAdminDAO instance = new RestaurantAdminDAO();
        RestaurantAdmin expResult = null;
        RestaurantAdmin result = instance.updateAdmin(admin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAdmin method, of class RestaurantAdminDAO.
     */
    @Test
    public void testDeleteAdmin()
    {
        System.out.println("deleteAdmin");
        int id = 0;
        RestaurantAdminDAO instance = new RestaurantAdminDAO();
        boolean expResult = false;
        boolean result = instance.deleteAdmin(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdminListByRestaurantid method, of class RestaurantAdminDAO.
     */
    @Test
    public void testGetAdminListByRestaurantid()
    {
        System.out.println("getAdminListByRestaurantid");
        long id = 0L;
        RestaurantAdminDAO instance = new RestaurantAdminDAO();
        List<RestaurantAdmin> expResult = null;
        List<RestaurantAdmin> result = instance.getAdminListByRestaurantid(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class RestaurantAdminDAO.
     */
    @Test
    public void testLogin()
    {
        System.out.println("login");
        long restid = 1L;
        String mail = "hburaksaavass";
        String password = "1234";
        RestaurantAdminDAO instance = new RestaurantAdminDAO();
        RestaurantAdmin expResult = null;
        RestaurantAdmin result = instance.login(restid, mail, password);

        System.out.println(result.getId()+" "+result.getMail());
    }
    
}
