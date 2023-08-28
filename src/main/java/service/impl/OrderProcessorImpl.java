package service.impl;

import domain.Order;
import domain.Product;
import service.OrderProcessor;

import java.util.HashMap;
import java.util.Map;

public class OrderProcessorImpl implements OrderProcessor {

    private Map<String, Product> products = new HashMap<>();
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public void addNewProduct(String id, String name, double price) {
        Product product = new Product(id, name, price);
        products.put(id, product);
    }

    @Override
    public boolean removeProduct(String id) {
        boolean isRemoved = false;
        if (products.containsKey(id)) {
            products.remove(id);
            isRemoved = true;
        }
        return isRemoved;
    }

    @Override
    public boolean placeOrder(Order order) {
        boolean isPlaced = false;
        if (!order.getProducts().isEmpty()) {
            orders.put(order.getId(), order);
            isPlaced = true;
        }
        return isPlaced;
    }

    @Override
    public boolean cancelOrder(String orderId) {
        boolean isCancel = false;
        if (orders.containsKey(orderId)) {
            orders.remove(orderId);
            isCancel = true;
        }
        return isCancel;
    }

    @Override
    public Map<String, Product> listAllProducts() {
        return products;
    }

    @Override
    public Map<String, Order> listAllOrders() {
        return orders;
    }

    @Override
    public Product getProduct(String productId) {
        return products.get(productId);
    }
}
