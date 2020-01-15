package io.github.madushanka.pos.dao.custom;


import io.github.madushanka.pos.dao.SuperDAO;
import io.github.madushanka.pos.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    List<CustomEntity> getOrderInfo() throws Exception;

}
