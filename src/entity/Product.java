/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author user
 */
@Entity
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    @OneToOne
    private List<Customer> customers;
    private int quantity;
    private int price;

    public Product() {
    }

    
    public String getProductName() {
        return productName;
    }
 
     public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }



    @Override
    public String toString() {
        return "Product{" 
                + "productName=" + productName 
                + ", quantity=" + quantity 
                + ", price=" + price 
                + '}';
    }

    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.productName);
        hash = 13 * hash + this.quantity;
        hash = 13 * hash + this.price;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.price != other.price) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
