package Core;

/**
 * The Basics that a New York Style Pizza
 * or Chicago Pizza should have.
 * @author Kareem
 */
public interface PizzaFactory {
    /**
     * Creates a Deluxe pizza.
     */
    Pizza createDeluxe();

    /**
     * Creates a Meatzza pizza.
     */
    Pizza createMeatzza();

    /**
     * Creates a BBQ Chicken pizza.
     */
    Pizza createBBQChicken();

    /**
     * Creates a Build Your Own pizza.
     */
    Pizza createBuildYourOwn();
}
