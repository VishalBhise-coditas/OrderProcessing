package service;

import domain.Order;
import domain.Product;
import java.util.Map;

public interface OrderProcessor {

    void addNewProduct(String id, String name, double price);

    boolean removeProduct(String id);

    boolean placeOrder(Order order);

    boolean cancelOrder(String orderId);

    Map<String, Product> listAllProducts();

    Map<String, Order> listAllOrders();

    Product getProduct(String productId);

}
