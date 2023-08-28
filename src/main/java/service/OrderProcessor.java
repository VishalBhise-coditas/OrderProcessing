package service;

import domain.Order;

public interface OrderProcessor {

    void addNewProduct();

    void removeProduct();

    void placeOrder();

    void cancelOrder();

    void listAllProducts();

    void listAllOrders();

}
