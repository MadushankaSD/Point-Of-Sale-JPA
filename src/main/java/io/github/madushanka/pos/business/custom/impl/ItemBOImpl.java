package io.github.madushanka.pos.business.custom.impl;

import io.github.madushanka.pos.business.custom.ItemBO;
import io.github.madushanka.pos.business.exception.AlreadyExistsInOrderException;
import io.github.madushanka.pos.dao.DAOFactory;
import io.github.madushanka.pos.dao.DAOTypes;
import io.github.madushanka.pos.dao.custom.ItemDAO;
import io.github.madushanka.pos.dao.custom.OrderDetailDAO;
import io.github.madushanka.pos.db.JPAUtill;
import io.github.madushanka.pos.dto.ItemDTO;
import io.github.madushanka.pos.entity.Item;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    private OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    @Override
    public void saveItem(ItemDTO item) throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            itemDAO.save(new Item(item.getCode(),
                    item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
          entityManager.getTransaction().commit();
          entityManager.close();
        }


    @Override
    public void updateItem(ItemDTO item) throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            itemDAO.update(new Item(item.getCode(),
                    item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
            entityManager.getTransaction().commit();
            entityManager.close();

    }

    @Override
    public void deleteItem(String itemCode) throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            orderDetailDAO.setEntityManager(entityManager);

            if (orderDetailDAO.existsByItemCode(itemCode)) {
                throw new AlreadyExistsInOrderException("Item already exists in an order, hence unable to delete");
            }
             itemDAO.delete(itemCode);
            entityManager.getTransaction().commit();
            entityManager.close();

    }

    @Override
    public List<ItemDTO> findAllItems() throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            List<Item> allItems = itemDAO.findAll();
            List<ItemDTO> dtos = new ArrayList<>();
            for (Item item : allItems) {
                dtos.add(new ItemDTO(item.getCode(),
                        item.getDescription(),
                        item.getQtyOnHand(),
                        item.getUnitPrice()));
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return dtos;

    }

    @Override
    public String getLastItemCode() throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            String lastItemCode = itemDAO.getLastItemCode();
            entityManager.getTransaction().commit();
            entityManager.close();
            return lastItemCode;
        }



    @Override
    public ItemDTO findItem(String itemCode) throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            Item item = itemDAO.find(itemCode);
            entityManager.getTransaction().commit();
            entityManager.close();
            return new ItemDTO(item.getCode(),
                    item.getDescription(),
                    item.getQtyOnHand(),
                    item.getUnitPrice());
        }

    @Override
    public List<String> getAllItemCodes() throws Exception {
        EntityManager entityManager = JPAUtill.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            List<Item> allItems = itemDAO.findAll();
            entityManager.getTransaction().commit();
            entityManager.close();
            List<String> codes = new ArrayList<>();
            for (Item item : allItems) {
                codes.add(item.getCode());
            }
            return codes;
        }

}


