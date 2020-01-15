package io.github.madushanka.pos.dao.custom.impl;

import io.github.madushanka.pos.dao.CrudDAOImpl;
import io.github.madushanka.pos.dao.custom.CustomerDAO;
import io.github.madushanka.pos.entity.Customer;


public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO {

    @Override
    public String getLastCustomerId() throws Exception {
       return (String) entityManager.createNativeQuery("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1").getSingleResult();
    }
}
