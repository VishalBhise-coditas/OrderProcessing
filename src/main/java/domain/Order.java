package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private String id;
    private List<Product> products;

    public Order(String id) {
        this.id = id;
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }
}
