package Week2Day5;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private String name;
    private double price;
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        list.add(new Product("Lays",180));
        list.add(new Product("Milk",100));
        list.add(new Product("Chocolate",160));
        list.add(new Product("Juice",130));
        list.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        System.out.println(list);

    }

}
