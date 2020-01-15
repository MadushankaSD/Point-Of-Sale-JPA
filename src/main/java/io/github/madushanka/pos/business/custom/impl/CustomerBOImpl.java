package io.github.madushanka.pos.business.custom.impl;

import io.github.madushanka.pos.business.custom.CustomerBO;
import io.github.madushanka.pos.business.exception.AlreadyExistsInOrderException;
import io.github.madushanka.pos.dao.DAOFactory;
import io.github.madushanka.pos.dao.DAOTypes;
import io.github.madushanka.pos.dao.custom.CustomerDAO;
import io.github.madushanka.pos.dao.custom.OrderDAO;
import io.github.madushanka.pos.db.JPAUtill;
import io.github.madushanka.pos.dto.CustomerDTO;
import io.github.madushanka.pos.entity.Customer;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);
    private OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);

    @Override
    public void saveCustomer(CustomerDTO customer) throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
        customerDAO.save(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void updateCustomer(CustomerDTO customer) throws Exception {

        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
        customerDAO.update(new Customer(customer.getId(), customer.getName(), customer.getAddress()));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteCustomer(String customerId) throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        customerDAO.setEntityManager(entityManager);

        if (orderDAO.existsByCustomerId(customerId)) {
            throw new AlreadyExistsInOrderException("Customer already exists in an order, hence unable to delete");
        }
        customerDAO.delete(customerId);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public List<CustomerDTO> findAllCustomers() throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
        List<Customer> alCustomers = customerDAO.findAll();
        entityManager.getTransaction().commit();
        entityManager.close();
        List<CustomerDTO> dtos = new ArrayList<>();
        for (Customer customer : alCustomers) {
            dtos.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getAddress()));
        }
        return dtos;

    }

    @Override
    public String getLastCustomerId() throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
        String lastCustomerId = customerDAO.getLastCustomerId();
        entityManager.getTransaction().commit();
        entityManager.close();
        return lastCustomerId;


    }

    @Override
    public CustomerDTO findCustomer(String customerId) throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
        Customer customer = customerDAO.find(customerId);
        entityManager.getTransaction().commit();
        entityManager.close();
        return new CustomerDTO(customer.getCustomerId(),
                customer.getName(), customer.getAddress());

    }

    @Override
    public List<String> getAllCustomerIDs() throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        customerDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
        List<Customer> customers = customerDAO.findAll();

        entityManager.getTransaction().commit();
        entityManager.close();
        List<String> ids = new ArrayList<>();
        for (Customer customer : customers) {
            ids.add(customer.getCustomerId());
        }
        return ids;
    }
}
