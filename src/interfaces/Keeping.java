/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entity.Customer;
import entity.Product;
import entity.History;
import java.util.List;

/**
 *
 * @author user
 */
public interface Keeping {
    public void saveProducts(List<Product> products);
    public List<Product> loadProducts();
    public void saveCustomers(List<Customer> customers);
    public List<Customer> loadCustomers();
    public void saveHistories(List<History> histories);
    public List<History> loadHistories();
}
