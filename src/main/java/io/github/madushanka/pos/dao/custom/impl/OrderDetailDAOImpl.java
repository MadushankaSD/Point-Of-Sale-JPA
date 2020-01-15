package io.github.madushanka.pos.dao.custom.impl;


import io.github.madushanka.pos.dao.CrudDAOImpl;
import io.github.madushanka.pos.dao.custom.OrderDetailDAO;
import io.github.madushanka.pos.entity.OrderDetail;
import io.github.madushanka.pos.entity.OrderDetailPK;


public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail,OrderDetailPK> implements OrderDetailDAO {

    @Override
    public boolean existsByItemCode(String itemCode) throws Exception {
        return entityManager.createNativeQuery("SELECT * FROM OrderDetail WHERE itemCode=?").setParameter(1, itemCode).getSingleResult() != null;
    }
}
