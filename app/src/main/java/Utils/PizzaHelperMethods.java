package Utils;

import com.example.rupizzeria_app_project4.R;

import Core.Crust;

/**
 * Class created to hold those methods that I use more than once in other classes.
 */
public class PizzaHelperMethods {

    /**
     * determines the image that the pizza should have based on the pizza type and crust
     * @param pizzaType the type of pizza (Chicago Style or New York Style)
     * @param crust the crust of the pizza (Thin, Brooklyn, Stuffed, Hand-tossed, Pan, Deep Dish)
     * @return the id of the image that the pizza should have
     */
    public static int determineIfChicagoStyleOrNewYorkStyleImage(String pizzaType, Crust crust){
        //convert crust to a string
        String crustString = crust.toString();

        //must make image returns for 8 different pizzas
        //Deluxe, BBQ_Chicken, etc.
        return switch (pizzaType) {
            case "Deluxe" ->
                    crustString.equals("DeepDish") ? R.drawable.chicagostyle_deluxe : R.drawable.newyorkstyle_deluxe;
            case "BBQ_Chicken" ->
                    crustString.equals("Pan") ? R.drawable.chicagostyle_bbqchicken : R.drawable.newyorkstyle_bbqchicken;
            case "Meatzza" ->
                crustString.equals("Stuffed") ? R.drawable.chicagostyle_meatzza : R.drawable.newyorkstyle_meatzza;
            case "BuildYourOwn" ->
                crustString.equals("Pan") ? R.drawable.chicagostyle_buildyourown : R.drawable.newyorkstyle_buildyourown;
            default -> throw new IllegalStateException("Unexpected value: " + pizzaType);
        };
    }

    /**
     * Determines if the pizza is a chicago or new york depending purely on the crust and pizza type
     * @param pizzaType pizza type of the pizza we are trying to determine
     * @param crust the crust type of the pizza we are trying to determine
     * @return the pizza type we have deduced through the crust and pizza type
     */
    public static String determineIfChicagoStyleOrNewYorkStyleText(String pizzaType, Crust crust){
        //convert crust to a string
        String crustString = crust.toString();

        //must make image returns for 8 different pizzas
        //Deluxe, BBQ_Chicken, etc.
        return switch (pizzaType) {
            case "Deluxe" ->
                    crustString.equals("DeepDish") ? "Chicago Style" : "New York Style";
            case "BBQ_Chicken", "BuildYourOwn" ->
                    crustString.equals("Pan") ? "Chicago Style" : "New York Style";
            case "Meatzza" ->
                    crustString.equals("Stuffed") ? "Chicago Style" : "New York Style";
            default -> throw new IllegalStateException("Unexpected value: " + pizzaType);
        };
    }
}
