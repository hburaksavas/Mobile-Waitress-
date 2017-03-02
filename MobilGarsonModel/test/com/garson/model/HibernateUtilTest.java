/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model;

import org.hibernate.SessionFactory;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class HibernateUtilTest {
    
    public HibernateUtilTest() {
    }

    /**
     * Test of getSessionFactory method, of class HibernateUtil.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory expResult = null;
        SessionFactory result = HibernateUtil.getSessionFactory();
    }

    /**
     * Test of shutdown method, of class HibernateUtil.
     */
    @Test
    public void testShutdown() {
        System.out.println("shutdown");
        HibernateUtil.shutdown();
    }
    
}
