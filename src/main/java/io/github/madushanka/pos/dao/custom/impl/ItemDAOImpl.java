package io.github.madushanka.pos.dao.custom.impl;



import io.github.madushanka.pos.dao.CrudDAOImpl;
import io.github.madushanka.pos.dao.custom.ItemDAO;
import io.github.madushanka.pos.entity.Item;


public class ItemDAOImpl extends CrudDAOImpl<Item,String> implements ItemDAO {

    @Override
    public String getLastItemCode() throws Exception {
       return (String) entityManager.createNativeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1").getSingleResult();
    }

}
