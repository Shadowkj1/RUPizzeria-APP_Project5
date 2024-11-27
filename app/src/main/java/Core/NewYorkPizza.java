package Core;

import java.util.ArrayList;

/**
 * Class containing the methods to create the different styles
 * of New York Pizzas
 * @author Kareem
 */
public class NewYorkPizza implements PizzaFactory{
//Cannot have any instance variables, static final constants, or other methods
//the PizzaTypes can ONLY be used in this class and ChicagoPizzaActivityController
//Deluxe, BBQChicken, Meatzza, BuildYourOwn
//Pizza must be used everywhere else

    /**
     * Method to create a Deluxe pizza
     * @return Pizza
     */
    @Override
    public Pizza createDeluxe() {
        ArrayList<Topping> DeluxeToppings = new ArrayList<>();
        DeluxeToppings.add(Topping.Sausage);
        DeluxeToppings.add(Topping.Pepperoni);
        DeluxeToppings.add(Topping.GreenPeppers);
        DeluxeToppings.add(Topping.Onions);
        DeluxeToppings.add(Topping.Mushrooms);
        Deluxe DeluxePizza = new Deluxe();
        DeluxePizza.setCrust(Crust.Brooklyn);
        DeluxePizza.setToppings(DeluxeToppings);


        return DeluxePizza;
    }

    /**
     * Method to create a Meatzza pizza
     * @return Pizza
     */
    @Override
    public Pizza createMeatzza() {
        ArrayList<Topping> MeatzzaToppings = new ArrayList<>();
        MeatzzaToppings.add(Topping.Sausage);
        MeatzzaToppings.add(Topping.Pepperoni);
        MeatzzaToppings.add(Topping.Beef);
        MeatzzaToppings.add(Topping.Ham);
        Meatzza MeatzzaPizza = new Meatzza();
        MeatzzaPizza.setCrust(Crust.HandTossed);
        MeatzzaPizza.setToppings(MeatzzaToppings);


        return MeatzzaPizza;

    }

    /**
     * Method to create a BBQ Chicken pizza
     * @return Pizza
     */
    @Override
    public Pizza createBBQChicken() {
        ArrayList<Topping> BBQChickenToppings = new ArrayList<>();
        BBQChickenToppings.add(Topping.BBQChicken);
        BBQChickenToppings.add(Topping.GreenPeppers);
        BBQChickenToppings.add(Topping.Provolone);
        BBQChickenToppings.add(Topping.Cheddar);
        BBQ_Chicken BBQChickenPizza = new BBQ_Chicken();
        BBQChickenPizza.setCrust(Crust.Thin);
        BBQChickenPizza.setToppings(BBQChickenToppings);

        return BBQChickenPizza;
    }

    /**
     * Method to create a Build Your Own pizza
     * @return Pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        BuildYourOwn BuildYourOwnPizza = new BuildYourOwn();
        BuildYourOwnPizza.setCrust(Crust.HandTossed);
        //prices: small: 8.99, medium 10.99, large 12.99
        //+1.69 per topping. 7 max toppings
        return BuildYourOwnPizza;
    }
}
