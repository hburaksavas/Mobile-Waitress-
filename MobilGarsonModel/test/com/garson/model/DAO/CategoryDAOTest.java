/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Category;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class CategoryDAOTest {
    
    public CategoryDAOTest() {
    }

    /**
     * Test of addCategory method, of class CategoryDAO.
     */
//    @Test
//    public void testAddCategory() {
//        System.out.println("addCategory");
//        String name = "Soğuk İçecekler";
//        int restauranID = 1;
//        CategoryDAO instance = new CategoryDAO();
//       
//        Category result = instance.addCategory(name, restauranID);
//    }

    /**
     * Test of updateCategory method, of class CategoryDAO.
     */
    @Test
    public void testUpdateCategory() {
        System.out.println("updateCategory");
        Category category = new Category().setId(new Long(2)).setName("Sıcacık").setRestaurantid(1);
        CategoryDAO instance = new CategoryDAO();
        Category result = instance.updateCategory(category);
    }

    /**
     * Test of deleteCategory method, of class CategoryDAO.
     */
    @Test
    public void testDeleteCategory() {
        System.out.println("deleteCategory");
        int id = 2;
        CategoryDAO instance = new CategoryDAO();
        boolean result = instance.deleteCategory(id);
    }

    /**
     * Test of getAllCategories method, of class CategoryDAO.
     */
    @Test
    public void testGetAllCategories() {
       
        System.out.println("getAllCategories");
        int restaurantID = 1;
        CategoryDAO instance = new CategoryDAO();

        List<Category> result = instance.getAllCategories(restaurantID);
        
        result.forEach(x-> System.out.println(x.getName()+" "+x.getRestaurantid()));
        
    }

    /**
     * Test of getByID method, of class CategoryDAO.
     */
    @Test
    public void testGetByID() {
        System.out.println("getByID");
        int id = 3;
        CategoryDAO instance = new CategoryDAO();
       
        Category result = instance.getByID(id);
        
        System.out.println(result.getRestaurantid()+" "+result.getName());
    }
    
}
