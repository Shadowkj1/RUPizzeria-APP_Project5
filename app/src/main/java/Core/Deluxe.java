package Core;

/**
 * The basics of what a Deluxe Pizza should have.
 * @author Kareem
 */
public class
Deluxe extends Pizza {

    /**
     * The price of the pizza.
     * @return the price of the pizza
     */
    @Override
    public double price() {
        //change the return value of price depending on the given size
        //prices: small: 16.99, medium: 18.99, large: 20.99
        if (getSize() == Size.Small) {
            return 16.99;
        } else if (getSize() == Size.Medium) {
            return 18.99;
        } else {
            return 20.99;
        }
    }
}
