/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.ProductImages;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class ProductImageDAOTest
{

    public ProductImageDAOTest()
    {
    }

    /**
     * Test of addProductImage method, of class ProductImageDAO.
     */
    @Test
    public void testAddProductImage()
    {
        System.out.println("addProductImage");
        ProductImages image = new ProductImages("asdf", 1);
        ProductImageDAO instance = new ProductImageDAO();
        ProductImages expResult = null;
        ProductImages result = instance.addProductImage(image);

    }

    /**
     * Test of updateProductImages method, of class ProductImageDAO.
     */
    @Test
    public void testUpdateProductImages()
    {
        System.out.println("updateProductImages");
        ProductImages image = null;
        ProductImageDAO instance = new ProductImageDAO();
        ProductImages expResult = null;
        ProductImages result = instance.updateProductImages(image);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProductImages method, of class ProductImageDAO.
     */
    @Test
    public void testDeleteProductImages()
    {
        System.out.println("deleteProductImages");
        long id = 0L;
        ProductImageDAO instance = new ProductImageDAO();
        boolean expResult = false;
        boolean result = instance.deleteProductImages(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductsImagesListById method, of class ProductImageDAO.
     */
    @Test
    public void testGetProductsImagesListById()
    {
        System.out.println("getProductsImagesListById");
        long productid = 0L;
        ProductImageDAO instance = new ProductImageDAO();
        List<ProductImages> expResult = null;
        List<ProductImages> result = instance.getProductsImagesListById(productid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductImagesById method, of class ProductImageDAO.
     */
    @Test
    public void testGetProductImagesById()
    {
        System.out.println("getProductImagesById");
        long id = 4L;
        ProductImageDAO instance = new ProductImageDAO();
        ProductImages expResult = null;
        ProductImages result = instance.getProductImagesById(id);

        String imageStr = result.getImagestring();
        byte[] decode = Base64.getDecoder().decode(imageStr);
        try
        {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(decode));
            File outputfile = new File("image.png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException ex)
        {
            Logger.getLogger(ProductImageDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of deleteProductImagesByProductId method, of class ProductImageDAO.
     */
    @Test
    public void testDeleteProductImagesByProductId()
    {
        System.out.println("deleteProductImagesByProductId");
        long productid = 1L;
        ProductImageDAO instance = new ProductImageDAO();
        boolean expResult = false;
        boolean result = instance.deleteProductImagesByProductId(productid);

    }

}
