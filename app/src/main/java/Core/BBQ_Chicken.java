package Core;

/**
 * The Basics for a BBQ Chicken Pizza.
 * @author Kareem
 */
public class BBQ_Chicken extends Pizza {


    /**
     * The price of the pizza.
     * @return the price of the pizza
     */
    @Override
    public double price() {
        //change the return value of price depending on the given size
        //prices: small: 14.99, medium: 16.99, large: 19.99

        if (getSize() == Size.Small) {
            return 14.99;
        } else if (getSize() == Size.Medium) {
            return 16.99;
        } else {
            return 19.99;
        }
    }
}
