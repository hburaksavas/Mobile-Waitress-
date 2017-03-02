/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Product;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class ProductDAOTest {
    
    public ProductDAOTest() {
    }

    /**
     * Test of addProduct method, of class ProductDAO.
     */
    @Test
    public void testAddProduct() {
        System.out.println("addProduct");
        Product product = new Product().setCategoryid(4).setDefiniton("Çorbadır kendisi").setName("Tarhana").setPrice(13.56d).setRestaurantid(1).setStock(new Long(1000));
        
        ProductDAO instance = new ProductDAO();
        Product result = instance.addProduct(product);

    }

    /**
     * Test of updateProduct method, of class ProductDAO.
     */
    @Test
    public void testUpdateProduct() {
        System.out.println("updateProduct");
         Product product = new Product().setCategoryid(3).setDefiniton("Çorbadır kendisi").setName("Yayla").setPrice(11.56d).setRestaurantid(1).setStock(new Long(1000));
      
         product.setId(new Long(2));
        ProductDAO instance = new ProductDAO();
        Product result = instance.updateProduct(product);
    }

    /**
     * Test of deleteProduct method, of class ProductDAO.
     */
//    @Test
//    public void testDeleteProduct() {
//        System.out.println("deleteProduct");
//        int id = 4;
//        ProductDAO instance = new ProductDAO();
//        boolean result = instance.deleteProduct(id);
//    }

    /**
     * Test of getRestaurantProducts method, of class ProductDAO.
     */
    @Test
    public void testGetRestaurantProducts() {
        System.out.println("getRestaurantProducts");
        int restaurantid = 1;
        ProductDAO instance = new ProductDAO();
        List<Product> result = instance.getRestaurantProducts(restaurantid);
        
        result.forEach(x-> System.out.println(x.getName() + " " + x.getDefiniton()));
    }

    /**
     * Test of getRestaurantProductsByCategory method, of class ProductDAO.
     */
    @Test
    public void testGetRestaurantProductsByCategory() {
        System.out.println("getRestaurantProductsByCategory");
        int restaurantid = 1;
        int categoryid = 3;
        ProductDAO instance = new ProductDAO();
        List<Product> expResult = null;
        List<Product> result = instance.getRestaurantProductsByCategory(restaurantid, categoryid);
           result.forEach(x-> System.out.println(x.getName() + " " + x.getDefiniton()));
    
    }

    /**
     * Test of updateStock method, of class ProductDAO.
     */
    @Test
    public void testUpdateStock() {
        System.out.println("updateStock");
        int restaurantid = 1;
        int productid = 1;
        int stock = 0;
        ProductDAO instance = new ProductDAO();
        boolean result = instance.updateStock(restaurantid, productid, stock);

    }

    /**
     * Test of updateScoreAndVoteCount method, of class ProductDAO.
     */
    @Test
    public void testUpdateScoreAndVoteCount() {
        System.out.println("updateScoreAndVoteCount");
        int score =10;
        int id = 1;
        ProductDAO instance = new ProductDAO();
        Product result = instance.updateScore(score, id);
    }

    /**
     * Test of getByID method, of class ProductDAO.
     */
    @Test
    public void testGetByID() {
        System.out.println("getByID");
        long id = 1L;
        ProductDAO instance = new ProductDAO();
        Product result = instance.getByID(id);
    }
    
}
