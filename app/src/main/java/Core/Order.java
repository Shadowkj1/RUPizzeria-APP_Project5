package Core;


import androidx.annotation.NonNull;

import java.util.ArrayList;
/**
 * The Order class that keeps the list of instances of the Pizza class.
 * @author Kareem
 */
public class Order {
    //Has a unique order number and keeps the list of instances of the Pizza class
    private int number; //order number
    private ArrayList<Pizza> pizzas; //can use List<E> instead of ArrayList<E>

    /**
     * Constructor for the Order class.
     * @param number the order number
     */
    public Order(int number, ArrayList<Pizza> pizzas) {
        this.number = number;
        this.pizzas = new ArrayList<Pizza>();
    }

    /**
     * Adds pizza to the order.
     * @param pizza that is being added to the order
     */

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * returns the order number.
     * @return number of order
     */
    public int getNumber() {
        return number;
    }

    /**
     * returns the list of pizzas in the order.
     * @return list of pizzas
     */

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }



    /**
     * toString method for the Order class.
     * @return the string representation of all the pizzas of the order
     */
    @NonNull
    @Override
    public String toString() {
        for (Pizza pizza : pizzas) {
            System.out.println(pizza.toString());
        }
        return "";
    }
}
