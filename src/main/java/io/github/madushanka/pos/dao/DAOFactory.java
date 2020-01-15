package io.github.madushanka.pos.dao;


import io.github.madushanka.pos.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }

    /*
    public CustomerDAO getCustomerDAO(){
        return new CustomerDAOImpl();
    }

    public ItemDAO getItemDAO(){
        return new ItemDAOImpl();
    }

    public OrderDAO getOrderDAO(){
        return new OrderDAOImpl();
    }

    public OrderDetailDAO getOrderDetailDAO(){
        return new OrderDetailDAOImpl();
    }
     */

}
