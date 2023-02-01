
 
package tools;

import entity.Customer;
import entity.Product;
import entity.History;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
 
public class SaverToBase implements Keeping{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ButorovSPTV21ShoppPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    public void saveProducts(List<Product> products) {
        tx.begin();
            for (int i = 0; i < products.size(); i++) {
                if(products.get(i).getId() == null){
                    em.persist(products.get(i));
                }else{
                    em.merge(products.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Product> loadProducts() {
        List<Product> products = null;
        try {
            products = em.createQuery("SELECT product FROM Product product")
                .getResultList();
        } catch (Exception e) {
            products = new ArrayList<>();
        }
        return products;
    }

   

    @Override
    public void saveHistories(List<History> histories) {
        tx.begin();
            for (int i = 0; i < histories.size(); i++) {
                if(histories.get(i).getId() == null){
                    em.persist(histories.get(i));
                }else{
                    em.merge(histories.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<History> loadHistories() {
        List<History> histories = null;
        try {
            histories = em.createQuery("SELECT history FROM History history")
                .getResultList();
        } catch (Exception e) {
            histories = new ArrayList<>();
        }
        return histories;
    }

    public void saveCustomers(List<Customer> customers) {
        tx.begin();
            for (int i = 0; i < customers.size(); i++) {
                if(customers.get(i).getId() == null){
                    em.persist(customers.get(i));
                }else{
                    em.merge(customers.get(i));
                }
            }
        tx.commit();
    }

    @Override
    public List<Customer> loadCustomers() {
        try {
            return em.createQuery("SELECT customer FROM Customer customer")
                .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}