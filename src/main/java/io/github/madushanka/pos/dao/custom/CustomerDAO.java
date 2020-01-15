package io.github.madushanka.pos.dao.custom;


import io.github.madushanka.pos.dao.CrudDAO;
import io.github.madushanka.pos.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    String getLastCustomerId() throws Exception;

}
