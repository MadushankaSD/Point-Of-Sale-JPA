package io.github.madushanka.pos.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements SuperEntity{
    @Id
    private String customerId;
    private String name;
    private String address;

    @OneToMany(mappedBy ="customer")
    private List<Order> order =new ArrayList<>();

    public Customer() {
    }

    public Customer(String customerId, String name, String address) {
       this.customerId = customerId;
       this.name = name;
       this.address = address;
   }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Order> getOrder() {
        return order;
    }

    //handeler method
    public void addOrder(Order order){
        order.setCustomer(this);
        this.order.add(order);
    }


    //handeler mothod 02
    public void removeOrer(Order order){
        if(order.getCustomer()!=this){
            throw new RuntimeException("Invilid Order");
        }
        order.setCustomer(null);
        this.getOrder().remove(order);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
