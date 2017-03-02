/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Restaurant;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class RestaurantDAOTest {
    
    public RestaurantDAOTest() {
    }

    /**
     * Test of addRestaurant method, of class RestaurantDAO.
     */
    @Test
    public void testAddRestaurant() {
        System.out.println("addRestaurant");
        Restaurant restaurant = new Restaurant().setName("Aşkın Restaurant")
                .setDefinition("Boğazın kıyısında, yemek keyfi")
                ;
        RestaurantDAO instance = new RestaurantDAO();
       
        Restaurant result = instance.addRestaurant(restaurant);
    }

    /**
     * Test of updateRestaurant method, of class RestaurantDAO.
     */
    @Test
    public void testUpdateRestaurant() {
        System.out.println("updateRestaurant");
        Restaurant restaurant = new Restaurant().setId(new Long(1))
                .setDefinition("Ataşehirde seçeneği olmayan tek restaurant")
                .setName("Savaş Kebap")
                .setVoteCount(1)
                .setServiceScore(10d);
        
        RestaurantDAO instance = new RestaurantDAO();
        Restaurant result = instance.updateRestaurant(restaurant);

    }

    /**
     * Test of deleteRestaurant method, of class RestaurantDAO.
     */
    @Test
    public void testDeleteRestaurant() {
        System.out.println("deleteRestaurant");
        int restaurantid = 13;
        RestaurantDAO instance = new RestaurantDAO();
        boolean expResult = true;
        boolean result = instance.deleteRestaurant(restaurantid);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRestaurants method, of class RestaurantDAO.
     */
    @Test
    public void testGetRestaurants() {
        System.out.println("getRestaurants");
        RestaurantDAO instance = new RestaurantDAO();
        List<Restaurant> result = instance.getRestaurants();
  
        result.forEach(x-> System.out.println(x.getId()+" "+x.getDefinition()+" "+x.getName()));
    }

    /**
     * Test of updateScoreAndVoteCount method, of class RestaurantDAO.
     */
    @Test
    public void testUpdateScoreAndVoteCount() {
        System.out.println("updateScoreAndVoteCount");

        long id = 9;
        RestaurantDAO instance = new RestaurantDAO();
        Restaurant result = instance.updateScore(id, 3);
   
        System.out.println(result.getServiceScore());
        
    }

    /**
     * Test of getByID method, of class RestaurantDAO.
     */
    @Test
    public void testGetByID() {
        System.out.println("getByID");
        int id = 1;
        RestaurantDAO instance = new RestaurantDAO();
        Restaurant result = instance.getByID(id);
          System.out.println(result.getName());
    }
    
}
