package io.github.madushanka.pos.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="`order`")
public class Order implements SuperEntity {
    @Id
    private int id;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customerID",referencedColumnName = "customerId",nullable = false)
    private Customer customer;

    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Order() {
    }


    public Order(int id, Date date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    //cascading perposes...
    public void addOrderDetails(OrderDetail orderDetails) {

        this.orderDetails.add(orderDetails);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +

                '}';
    }
}
