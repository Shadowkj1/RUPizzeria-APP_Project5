package Core;

/**
 * The basics for a Build Your Own Pizza.
 * @author Kareem
 */
public class BuildYourOwn extends Pizza{
    //change the return value of price depending on the given size

    /**
     * The price of the pizza.
     * @return the price of the pizza
     */
    @Override
    public double price() {
        //change the return value of price depending on the given size
        //prices: small: 8.99, medium 10.99, large 12.99
        //+1.69 per topping. 7 max toppings
        if (getSize() == Size.Small) {
            return 8.99 + (1.69 * getToppings().size());
        } else if (getSize() == Size.Medium) {
            return 10.99 + (1.69 * getToppings().size());
        } else {
            return 12.99 + (1.69 * getToppings().size());
        }
    }
}
