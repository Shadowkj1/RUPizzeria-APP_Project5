package Core;

/**
 * The basics of what a Meatzza Pizza should have.
 * @author Kareem
 */
public class Meatzza extends Pizza{
    /**
     * The price of the pizza.
     * @return the price of the pizza
     */
    @Override
    public double price() {
        //change the return value of price depending on the given size
        //prices: small: 17.99, medium: 19.99, large: 21.99
        if (getSize() == Size.Small) {
            return 17.99;
        } else if (getSize() == Size.Medium) {
            return 19.99;
        } else {
            return 21.99;
        }
    }
}
