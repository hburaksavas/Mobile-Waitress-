/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garson.model.DAO;

import com.garson.model.entity.Address;
import java.util.List;

/**
 *
 * @author BurakS
 */
public class AddressDAO extends DAO<Address> {

    public AddressDAO() {
        super();
    }

    public Address addAddress(Address address) {
        return add(address);
    }

    public Address updateAddres(Address address) {
        return update(address);
    }

    public boolean deleteAddress(long id) {
        return delete(Address.class, id);
    }

    public List<Address> getAddressList() {
        return getAllRecords(Address.class);
    }

    public Address getByID(long id) {
        return findByID(Address.class, id);
    }
}
