package entity;

import entity.Customer;
import entity.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-01-27T02:18:04")
@StaticMetamodel(History.class)
public class History_ { 

    public static volatile SingularAttribute<History, Product> product;
    public static volatile SingularAttribute<History, Long> id;
    public static volatile SingularAttribute<History, Customer> customer;

}