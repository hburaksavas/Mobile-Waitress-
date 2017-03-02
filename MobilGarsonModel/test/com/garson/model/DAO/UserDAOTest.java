package com.garson.model.DAO;

import com.garson.model.entity.Address;
import com.garson.model.entity.User;
import java.util.List;
import org.junit.Test;


public class UserDAOTest {
    
    public UserDAOTest() {
    }


    @Test
    public void testAddUser() {
        System.out.println("addUser");
        Address adres = new Address().setCity("istanbul").setDistrict("ataşehir")
                .setStreet("kocaceviz mah")
                .setAdresstext("erdoğmuş sok no 5 daire 3");
        AddressDAO dao = new AddressDAO();
        dao.addAddress(adres);
        User user = new User()
                .setGender(1)
                .setMail("hburaksavas")
                .setName("burak")
                .setPassword("1234")
                .setPhone("132213")
                .setSurname("savaş")
                .setAddresid(adres.getId());
        
        UserDAO instance = new UserDAO();
        User result = instance.addUser(user);
    }

 
//    @Test
//    public void testUpdateUser() {
//        System.out.println("updateUser");
//        User user = null;
//        UserDAO instance = new UserDAO();
//        User expResult = null;
//        User result = instance.updateUser(user);
//    }

//
//    @Test
//    public void testDeleteUser() {
//        System.out.println("deleteUser");
//        int id = 3;
//        UserDAO instance = new UserDAO();
//        boolean expResult = false;
//        boolean result = instance.deleteUser(id);
//    }

    /**
     * Test of getUsers method, of class UserDAO.
     */
    @Test
    public void testGetUsers() {
        
        System.out.println("getUsers");
        UserDAO instance = new UserDAO();
        List<User> expResult = null;
        List<User> result = instance.getUsers();
        result.forEach(x-> System.out.println(x.getMail()));
        
    }

    /**
     * Test of login method, of class UserDAO.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String mail = "hburaksavas";
        String password = "1234";
        UserDAO instance = new UserDAO();
        User result = instance.login(mail, password);
        
        System.out.println(result.getName());
    }
    
}
