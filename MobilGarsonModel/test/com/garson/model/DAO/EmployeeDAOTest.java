/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Address;
import com.garson.model.entity.Employee;
import java.util.List;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author BurakS
 */
public class EmployeeDAOTest {
    
    public EmployeeDAOTest() {
    }

    /**
     * Test of addEmployee method, of class EmployeeDAO.
     */
    @Test
    public void testAddEmployee() {
        System.out.println("addEmployee");
        Address adres = new Address()
                .setCity("İstanbul")
                .setDistrict("Avcılar")
                .setStreet("Gümüşpala Mah")
                .setAdresstext("Erdoğmuş sokak No 5 Daire 13");
        
        AddressDAO dao = new AddressDAO();
        long id = dao.addAddress(adres).getId();
       
        Employee emp = new Employee()
                .setMail("dd@hotmail.com")
                .setName("Dilan")
                .setPassword("d1234")
                .setPhone("05348695")
                .setSurname("Aşkın")
                .setTcNo("23232323236")
                .setAdressid(id)
                .setRestaurantid(new Long(1));
        
        EmployeeDAO instance = new EmployeeDAO();
        Employee result = instance.addEmployee(emp);
        Long idd = result.getId();
        if(idd == null){
            fail();
        }
    }

    /**
     * Test of updateEmployee method, of class EmployeeDAO.
     */
    @Test
    public void testUpdateEmployee() {
        System.out.println("updateEmployee");
        Employee emp = new Employee()
                .setMail("dd323232@hotmail.com")
                .setName("Dilan323232")
                .setPassword("d1234323232")
                .setPhone("0534869232325")
                .setSurname("Aşkın")
                .setTcNo("2324444323")
                .setAdressid(new Long(8))
                .setRestaurantid(new Long(1))
                .setId(new Long(5));
        EmployeeDAO instance = new EmployeeDAO();
        Employee result = instance.updateEmployee(emp);
    }

    /**
     * Test of deleteEmployee method, of class EmployeeDAO.
     */
    @Test
    public void testDeleteEmployee() {
        System.out.println("deleteEmployee");
        int id = 5;
        EmployeeDAO instance = new EmployeeDAO();
        boolean result = instance.deleteEmployee(id);
    }

    /**
     * Test of getRestaurantEmployees method, of class EmployeeDAO.
     */
    @Test
    public void testGetRestaurantEmployees() {
        System.out.println("getRestaurantEmployees");
        int restaurantID = 1;
        EmployeeDAO instance = new EmployeeDAO();
        List<Employee> result = instance.getRestaurantEmployees(restaurantID);
        // TODO review the generated test code and remove the default call to fail.
        result.forEach(x->System.out.println(x.getId()+" "+x.getMail()+" "+x.getName()+" "+x.getSurname()));
    }

    /**
     * Test of updateScoreAndVoteCount method, of class EmployeeDAO.
     */
    @Test
    public void testUpdateScoreAndVoteCount() {
        System.out.println("updateScoreAndVoteCount");
        int score = 1;
        long id = 11;
        EmployeeDAO instance = new EmployeeDAO();
        Employee result = instance.updateScore(id,score);
        
    }

    /**
     * Test of login method, of class EmployeeDAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        long restaurantid = 1;
        String mail = "dd@hotmail.com";
        String password = "d1234";
        EmployeeDAO instance = new EmployeeDAO();
       
        Employee result = instance.login(restaurantid, mail, password);
        
        System.out.println("UserNAME:::"+result.getName());
    }

    /**
     * Test of getByID method, of class EmployeeDAO.
     */
    @Test
    public void testGetByID() {
        System.out.println(  "getByID");
        int id = 11;
        EmployeeDAO instance = new EmployeeDAO();
        Employee expResult = null;
        Employee result = instance.getByID(id);
       
        System.out.println(result.getMail());
        
        System.err.println("gettedByID");
    }
    
}
