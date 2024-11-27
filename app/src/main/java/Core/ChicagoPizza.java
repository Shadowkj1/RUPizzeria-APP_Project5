package Core;



import java.util.ArrayList;
/**
 * Class containing the different methods to create different types of
 * Chicago Pizzas
 * @author Kareem
 * @author Paul
 */
public class ChicagoPizza implements PizzaFactory{
//Cannot have any instance variables, static final constants, or other methods
//the PizzaTypes can ONLY be used in this class and NewYorkPizzaActivityController
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
        Pizza DeluxePizza = new Deluxe();
        DeluxePizza.setCrust(Crust.DeepDish);
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
        Pizza MeatzzaPizza = new Meatzza();
        MeatzzaPizza.setCrust(Crust.Stuffed);
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
        Pizza BBQChickenPizza = new BBQ_Chicken();
        BBQChickenPizza.setCrust(Crust.Pan);
        BBQChickenPizza.setToppings(BBQChickenToppings);

        return BBQChickenPizza;
    }

    /**
     * Method to create a Build Your Own pizza
     * @return Pizza
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza BuildYourOwnPizza = new BuildYourOwn();
        BuildYourOwnPizza.setCrust(Crust.Pan);

        return BuildYourOwnPizza;
    }
}
