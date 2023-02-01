package entity;

import entity.Customer;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-01-27T02:18:04")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> quantity;
    public static volatile SingularAttribute<Product, Integer> price;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile ListAttribute<Product, Customer> customers;
    public static volatile SingularAttribute<Product, String> productName;

}