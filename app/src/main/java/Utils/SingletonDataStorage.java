package Utils;

import java.util.ArrayList;

import Core.Order;
import Core.Pizza;

public class SingletonDataStorage {
    private static SingletonDataStorage instance;

    /**
     * Arraylist that will hold the pizzas in the current order
     */
    private ArrayList<Pizza> currentOrder;
    /**
     * Arraylist that will hold all orders of pizzas for the apps runtime.
     */
    private ArrayList<Order> orderHistory;

    /**
     * Constructor for SingletonDataStorage. Initializes the arraylists for the first time.
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
     * Getter for orderHistory
     * @return orderHistory The orderHistory containing all the orders made
     */
    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }
}
