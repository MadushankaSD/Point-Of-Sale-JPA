package io.github.madushanka.pos.dao.custom;


import io.github.madushanka.pos.dao.CrudDAO;
import io.github.madushanka.pos.entity.OrderDetail;
import io.github.madushanka.pos.entity.OrderDetailPK;

public interface OrderDetailDAO extends CrudDAO<OrderDetail, OrderDetailPK> {

    boolean existsByItemCode(String itemCode) throws Exception;

}
