package io.github.madushanka.pos.dao.custom;


import io.github.madushanka.pos.dao.CrudDAO;
import io.github.madushanka.pos.entity.Item;

public interface ItemDAO extends CrudDAO<Item, String> {

    String getLastItemCode() throws Exception;
}
