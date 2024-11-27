package Core;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Class that holds the basis of... WHAT IS A PIZZA!?
 * @author Kareem
 */

public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * The price of the pizza.
     * @return the price of the pizza
     */
    public double price() {
        return 0;
    }


    /**
     * Getter for the size of the pizza.
     * @return the size of the pizza
     */
    public Size getSize() {
        return this.size;
    }

    /**
     * Getter for the price of the pizza.
     * @return the price of the pizza
     */
    public double getPrice() {
        return price();
    }

    /**
     * Getter for the toppings of the pizza.
     * @return the crust of the pizza
     */
    public ArrayList<Topping> getToppings() {
        return this.toppings;
    }

    /**
     * Setter for the crust of the pizza.
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Setter for the size of the pizza.
     * @param size the size of the pizza
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Setter for toppings of the pizza.
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * ToString method for any pizza.
     * @return toString for any pizza
     */
    @NonNull
    @Override
    public String toString() {
return "Pizza with " + crust + " crust, " + size + " size, and " + toppings + " toppings. Price: $" + String.format(Locale.getDefault(), "%.2f", price());
    }
}
