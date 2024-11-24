package Utils;

import java.util.ArrayList;

import Core.Order;
import Core.Pizza;

public class SingletonDataStorage {
    private static SingletonDataStorage instance;
    /*
    private ArrayList<Pizza> currentOrder = new ArrayList<Pizza>();
    private ArrayList<Order> orderHistory = new ArrayList<Order>();
     */
    private ArrayList<Pizza> currentOrder;
    private ArrayList<Order> orderHistory;

    /**
     * Constructor for SingletonDataStorage
     */
    private SingletonDataStorage() {
        currentOrder = new ArrayList<>();
        orderHistory = new ArrayList<>();
    }

    /**
     * Method to get the instance of SingletonDataStorage.
     * Makes sure two threads cannot access the instance at the same time.
     * Nor can two singletons be created.
     * @return instance of SingletonDataStorage
     */
    public static synchronized SingletonDataStorage getInstance() {
        if (instance == null) {
            instance = new SingletonDataStorage();
        }
        return instance;
    }

    /**
     * Getter for currentOrder
     * @return currentOrder containing all the pizzas in the current order
     */
    public ArrayList<Pizza> getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Setter for currentOrder
     * @param currentOrder you want to set
     */
    public void setCurrentOrder(ArrayList<Pizza> currentOrder) {
        this.currentOrder = currentOrder;
    }

    /**
     * Getter for orderHistory
     * @return orderHistory The orderHistory containing all the orders made
     */
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    /**
     * Setter for orderHistory
     * @param orderHistory the OrderHistory List you want to set
     */
    public void setOrderHistory(ArrayList<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    /**
     * Method to add a pizza to the current order
     * @param pizza The pizza you want to add to the order
     */
    public void addPizzaToOrder(Pizza pizza) {
        currentOrder.add(pizza);
    }

    /**
     * Method to add an order to the order history
     * @param order The order you want to add to the order history
     */
    public void addOrder(Order order) {
        orderHistory.add(order);
    }
}
