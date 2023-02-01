/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Customer;
import entity.Product;
import entity.History;
import interfaces.Keeping;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToBase;
import tools.SaverToFiles;

/**
 *
 * @author user
 */
public class App {
    public static boolean toFile = false;
    
    private Scanner scanner = new Scanner(System.in);
    private List<Product> products = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private Keeping keeper;
    
    public App(){
        if(toFile){
            keeper = new SaverToFiles();
        }else{
            keeper = new SaverToBase();
        }
        products = keeper.loadProducts();
        customers = keeper.loadCustomers();
        histories = keeper.loadHistories();
    }
    
    public void run(){
       String repeat = "yes";
       do{
           System.out.println("Список задач: ");
            System.out.println("0. Выход из программы");
            System.out.println("1. Добавить продукт");
            System.out.println("2. Список продаваемых продуктов");
            System.out.println("3. Добавить покупателя");
            System.out.println("4. Список зарегистрированных покупателей");
            System.out.println("5. Покупка покупателем продукта");
            System.out.println("6. Оборот магазина за все время работы");
            System.out.println("7. Добавить денег покупателю");
           int task = getNumber();
           
           switch (task) {
               case 0:
                   repeat = "no";
                   break;
               case 1:
                   addProduct();
                   break;
               case 2:
                   printListSellersProduct();
                   break;
               case 3:
                   addCustomer();
                   break;
               case 4:
                   printListCustomers();
                   break;
               case 5:
                   getTakeOnProduct();
                   break;
               case 6:
                   printListPurchase();
                   break;
               case 7:
                  updateCustomer();
                   break;
               default:
                   System.out.println("Попробуй еще раз: ");
           }
           
       }while("yes".equals(repeat));
       System.out.println("Пока! :)");
    }
    
    private void getTakeOnProduct() {
        
        System.out.println("*ПОКУПКА *"); 
        System.out.println("-----------------------------");
        printListSellersProduct();
        System.out.print("Выберите нужную:");
        int productNum= scanner.nextInt(); scanner.nextLine();
        System.out.println("-----------------------------");
        printListCustomers();
        System.out.print("Выберите нужного покупателя: ");
        int customerNum= scanner.nextInt(); scanner.nextLine();
        History history= new History();
        history.setProduct(products.get(productNum-1));
        history.setCustomer(customers.get(customerNum-1));
        if(history.getCustomer().getMoney()>=history.getProduct().getPrice()){
            System.out.println("-----------------------------");
            System.out.printf("продукты %s купил %s %s за %s евро\n",
            history.getProduct().getProductName(),
            history.getCustomer().getFirstName(),
            history.getCustomer().getLastName(),
            history.getProduct().getPrice());
            history.getCustomer().setMoney(history.getCustomer().getMoney()-history.getProduct().getPrice());
            history.getProduct().setQuantity(history.getProduct().getQuantity() - 1);
            histories.add(history);
            keeper.saveProducts(products);
            keeper.saveCustomers(customers);
            keeper.saveHistories(histories);
        }else{
            System.out.println("Этот пользователь не может совершить покупку, так как у него не хватает денег!");
        }
    }

    private void addProduct() {
       Product product = new Product();
       System.out.print("Введите название продукта: ");
       product.setProductName(scanner.nextLine());
       System.out.print("Введите цену продукта: ");
       product.setPrice(getNumber());
       System.out.print("Введите количество продуктов: ");
       product.setQuantity(getNumber());
       System.out.println("продукт инициирован: "+product.toString());    
       products.add(product);
       keeper.saveProducts(products);
    }
    
    
    private Set<Integer> printListSellersProduct() {
        System.out.println("----- Список продуктов -----");
        Set<Integer> setNumbersProducts = new HashSet<>();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i) != null){
                System.out.printf("%d. %s %s. цена: %s%n"
                       ,i+1
                       ,products.get(i).getProductName()
                       ,products.get(i).getQuantity()
                       ,products.get(i).getPrice()
                );
                setNumbersProducts.add(i+1);
            }
        }
        if(setNumbersProducts.isEmpty()){
            System.out.println("Список продуктов пуст.");
        }
        return setNumbersProducts;
    }
    
    
   



    private Set<Integer> printListCustomers() {
        System.out.println("----- Список покупателей -----");
        Set<Integer> setNumbersCustomers = new HashSet<>();
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i) != null){
                System.out.printf("%d. %s %s. деньги: %s%n"
                       ,i+1
                       ,customers.get(i).getFirstName()
                       ,customers.get(i).getLastName()
                       ,customers.get(i).getMoney()
                );
                setNumbersCustomers.add(i+1);
            }
        }
        if(setNumbersCustomers.isEmpty()){
            System.out.println("Список Покупателей пуст.");
        }
        return setNumbersCustomers;
    }

    private int getNumber() {
        int number;
        do{
           String strNumber = scanner.nextLine();
           try {
               number = Integer.parseInt(strNumber);
               return number;
           } catch (NumberFormatException e) {
               System.out.println("Введено \""+strNumber+"\". Выбирайте номер ");
           }
       }while(true);
    }

    private int insertNumber(Set<Integer> setNumbers) {
        int number=0;
        do{
           number = getNumber();
           if(setNumbers.contains(number)){
               break;
           }
           System.out.println("Попробуй еще раз: ");
       }while(true);
       return number; 
    }


    private void addCustomer() {
        Customer customer = new Customer();
        System.out.print("Введите имя покупателя: ");
        customer.setFirstName(scanner.nextLine());
        System.out.print("Введите фамилию покупателя: ");
        customer.setLastName(scanner.nextLine());
        System.out.print("Введите кол-во денег: ");
        customer.setMoney(getNumber());
        System.out.println("Покупатель инициирован: "+customer.toString());
        customers.add(customer);
        keeper.saveCustomers(customers);
    }
    
    private void updateCustomer() {
        Set<Integer> changeNumber = new HashSet<>();
        changeNumber.add(1);
        changeNumber.add(2);
        Set<Integer> setNumbersCustomers = printListCustomers();
        if(setNumbersCustomers.isEmpty()){
            return;
        }
        System.out.println("Выберите номер покупателя: ");
        int numberCustomer = insertNumber(setNumbersCustomers);
        System.out.println("Имя покупателя: "+customers.get(numberCustomer - 1).getFirstName());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        int change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новое имя покупателя: ");
            customers.get(numberCustomer - 1).setFirstName(scanner.nextLine());
            
        }
        System.out.println("Фамилия покупателя: "+customers.get(numberCustomer - 1).getLastName());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите новую фамилию покупателя: ");
            customers.get(numberCustomer - 1).setLastName(scanner.nextLine());
            keeper.saveCustomers(customers);
        }
        
        System.out.println("Деньги покупателя: "+customers.get(numberCustomer - 1).getMoney());
        System.out.println("Для изменения введите 1, чтобы пропустить нажмите 2");
        change = insertNumber(changeNumber);
        if(1 == change){
            System.out.println("Введите деньги покупателя: ");
            customers.get(numberCustomer - 1).setMoney(scanner.nextInt());
            keeper.saveCustomers(customers);
            }
        keeper.saveCustomers(customers);
    }
    private Set<Integer> printListPurchase(){
        int sum2=0;
        Set<Integer> setNumbersHistories = new HashSet<>();
        for (int i = 0; i < histories.size(); i++) {
            System.out.printf("_____________________________\n"
                       ,i+1
                       ,histories.get(i).getProduct().getPrice()
                       ,sum2+=histories.get(i).getProduct().getPrice(),"\n"
            );
                setNumbersHistories.add(i+1);
            }
        

        if(setNumbersHistories.isEmpty()){
            System.out.println("Список покупок"
                    + " пуст.");
        }
        System.out.print("доход за все время:"+sum2);
        return setNumbersHistories;
    }


    
}
  
