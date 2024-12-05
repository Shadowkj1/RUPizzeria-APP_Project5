package com.example.rupizzeria_app_project4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Locale;

import Core.Order;
import Core.Pizza;
import Core.PizzaFactory;
import Core.Size;
import Core.Topping;
import Utils.SingletonDataStorage;

public class NewYorkPizzaActivityController extends AppCompatActivity {

    /**
     * holds the current base price of the current pizza
     */
    private double currentBasePrice = 0.0;
    /**
     * holds the selected topping count
     */
    private int selectedToppingCount = 0;
    /**
     * holds the price of one topping
     */
    private final double toppingPrice = 1.69;

    /**
     * holds the current order
     */
    private ArrayList<Pizza> currentOrder;



    /**
     * This method is called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_york_pizza);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //create an instance of the SingletonDataStorage class
        SingletonDataStorage GlobalPizzaData = SingletonDataStorage.getInstance();
        currentOrder = GlobalPizzaData.getCurrentOrder();


        //when the user selects a pizza type
        pizzaTypeChanged(findViewById(R.id.spinner_newYorkPizzaType));
        //When the user selects a pizza size
        pizzaSizeChanged(findViewById(R.id.spinner_pizzaSize));
        //When the user clicks Add to Cart!!
        addToCartClicked(findViewById(R.id.button_addtoCart));
        //When the user clicks the back button
        sendUserBackToMainMenu();
    }

    /**
     * Sends the user back to the main menu
     */
    private void sendUserBackToMainMenu() {
        findViewById(R.id.back_button).setOnClickListener(v -> {
            finish();
        });
    }

    /**
     * Method that adds all that is currently selected to the cart
     * @param view the view that contains the cart image button.
     */
    private void addToCartClicked(View view) {
        Button addToCart = (Button) view;

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create the pizza from the items on screen and add it to the cart
                PizzaFactory pizzaFactory = new Core.NewYorkPizza();
                //convert the size from a string to a Size enum
                Size pizzaSize = getSizeFromSpinner(((Spinner)
                        findViewById(R.id.spinner_pizzaSize)).getSelectedItem().toString());

                if (getPizzaTypeFromSpinner().equals(getString(R.string.new_york_deluxe))) {
                    Pizza deluxe = pizzaFactory.createDeluxe();
                    deluxe.setSize(pizzaSize);
                    currentOrder.add(deluxe);
                    showPizzaAddedToast();
                } else if (getPizzaTypeFromSpinner().equals(getString(R.string.new_york_bbq))) {
                    Pizza bbq = pizzaFactory.createBBQChicken();
                    bbq.setSize(pizzaSize);
                    currentOrder.add(bbq);
                    showPizzaAddedToast();
                } else if (getPizzaTypeFromSpinner().equals(getString(R.string.new_york_meatzza))) {
                    Pizza meatzza = pizzaFactory.createMeatzza();
                    meatzza.setSize(pizzaSize);
                    currentOrder.add(meatzza);
                    showPizzaAddedToast();
                } else if (getPizzaTypeFromSpinner().equals(getString(R.string.new_york_buildyourown))) {
                    Pizza buildYourOwn = pizzaFactory.createBuildYourOwn();
                    buildYourOwn.setSize(pizzaSize);
                    //grab all of the toppings and add them to the pizza
                    buildYourOwn.setToppings(getTheArrayOfToppings());
                    currentOrder.add(buildYourOwn);
                    showPizzaAddedToast();
                }

            }
        });

    }

    /**
     * show toast that a pizza has been added to the cart
     */
    public void showPizzaAddedToast() {
        Toast.makeText(this, "Pizza has been added to the cart!!", Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method that gets all the toppings selected by the user
     */
    public ArrayList<Topping> getTheArrayOfToppings() {
        ChipGroup chipGroupToppings = findViewById(R.id.chipGroup_toppings);
        ArrayList<Topping> allSelectedToppings = new ArrayList<>();
        for (int i = 0; i < chipGroupToppings.getChildCount(); i++) {
            Chip chip = (Chip) chipGroupToppings.getChildAt(i);
            if (chip.isChecked()) {
                Topping convertedChipTopping = getToppingFromChipId(chip.getId());
                //add the topping to the arraylist
                allSelectedToppings.add(convertedChipTopping);
            }
        }
        return allSelectedToppings;
    }

   /**
    * Helper method that converts the chip id to a topping
    */
    public Topping getToppingFromChipId(int chipId) {
        if (chipId == R.id.toppingsChip_Sausage) {
            return Topping.Sausage;
        } else if (chipId == R.id.toppingsChip_Pepperoni) {
            return Topping.Pepperoni;
        } else if (chipId == R.id.toppingsChip_GreenPeppers) {
            return Topping.GreenPeppers;
        } else if (chipId == R.id.toppingsChip_Onions) {
            return Topping.Onions;
        } else if (chipId == R.id.toppingsChip_Mushrooms) {
            return Topping.Mushrooms;
        } else if (chipId == R.id.toppingsChip_BBQChicken) {
            return Topping.BBQChicken;
        } else if (chipId == R.id.toppingsChip_Beef) {
            return Topping.Beef;
        } else if (chipId == R.id.toppingsChip_Ham) {
            return Topping.Ham;
        } else if (chipId == R.id.toppingsChip_Provolone) {
            return Topping.Provolone;
        } else if (chipId == R.id.toppingsChip_Cheddar) {
            return Topping.Cheddar;
        } else {
            return null;
        }
    }


    /**
     * Simple helper method that converts the selected spinner value to a Size enum
     * @param spinnerValue the value from the spinner spinner_pizzaSize
     * @return the enum equivalent of the spinner value
     */
    private Size getSizeFromSpinner(String spinnerValue) {
        return switch (spinnerValue) {
            case "Small" -> Size.Small;
            case "Medium" -> Size.Medium;
            case "Large" -> Size.Large;
            default -> null;
        };
    }

    /**
     * Simple helper method that grabs the pizza type from the spinner_chicagoPizzaType
     * @return string that contains what was inside the spinner
     */
    private String getPizzaTypeFromSpinner() {
        return ((Spinner) findViewById(R.id.spinner_newYorkPizzaType)).getSelectedItem()
                .toString();
    }



    /**
     * This activates everytime the user selects a pizza type from the spinner
     * @param view This is the view containing the pizzaType spinner
     */
    public void pizzaTypeChanged(View view) {
        Spinner pizzaType = (Spinner) view;

        pizzaType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //string holding the pizzatype from the spinner
                String selectedPizza = pizzaType.getSelectedItem().toString();
                TextView crustType = findViewById(R.id.textview_crustType);
                ImageView pizzaImage = findViewById(R.id.imageview_pizzaImage);
                if (selectedPizza.equals(getString(R.string.new_york_deluxe))) {
                    //change the crust to Brooklyn
                    crustType.setText(getString(R.string.crust_Brooklyn));
                    //change the image
                    pizzaImage.setImageResource(R.drawable.newyorkstyle_deluxe);
                    deluxePizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_bbq))) {
                    //change the crust to thin
                    crustType.setText(getString(R.string.crust_Thin));
                    //change the image
                    pizzaImage.setImageResource(R.drawable.newyorkstyle_bbqchicken);
                    bbqChickenPizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_meatzza))) {
                    //change the crust to Hand-tossed
                    crustType.setText(getString(R.string.crust_HandTossed));
                    //change the image
                    pizzaImage.setImageResource(R.drawable.newyorkstyle_meatzza);
                    meatzzaPizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_buildyourown))) {
                    //change the crust to Hand-tossed
                    crustType.setText(getString(R.string.crust_HandTossed));
                    //change the image
                    pizzaImage.setImageResource(R.drawable.newyorkstyle_buildyourown);
                    buildYourOwnPizzaSelectionsOnly();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    /**
     * Disables all toppings not relevant to Deluxe Pizza.
     * Also disables the ability to click toppings
     */
    public void deluxePizzaSelectionsOnly() {
    setToppingsState(new int[]{
        R.id.toppingsChip_Sausage,
        R.id.toppingsChip_Pepperoni,
        R.id.toppingsChip_GreenPeppers,
        R.id.toppingsChip_Onions,
        R.id.toppingsChip_Mushrooms,
    }, true, true, false);

    setToppingsState(new int[]{
        R.id.toppingsChip_BBQChicken,
        R.id.toppingsChip_Beef,
        R.id.toppingsChip_Ham,
        R.id.toppingsChip_Provolone,
        R.id.toppingsChip_Cheddar
    }, false, false, false);

    Spinner pizzaSize = findViewById(R.id.spinner_pizzaSize);
    if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_small))) {
        setPriceFromType(16.99);
    } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_medium))) {
        setPriceFromType(18.99);
    } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_large))) {
        setPriceFromType(20.99);
    }
}

    /**
     * Disables all toppings not relevant to BBQ Chicken Pizza.
     * Also disables the ability to click toppings.
     */
    public void bbqChickenPizzaSelectionsOnly(){
        setToppingsState(new int[]{
                R.id.toppingsChip_BBQChicken,
                R.id.toppingsChip_GreenPeppers,
                R.id.toppingsChip_Provolone,
                R.id.toppingsChip_Cheddar
        }, true, true, false);

        setToppingsState(new int[]{
                R.id.toppingsChip_Sausage,
                R.id.toppingsChip_Pepperoni,
                R.id.toppingsChip_Beef,
                R.id.toppingsChip_Onions,
                R.id.toppingsChip_Mushrooms,
                R.id.toppingsChip_Ham
        }, false, false, false);
        Spinner pizzaSize = findViewById(R.id.spinner_pizzaSize);
        if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_small))) {
            setPriceFromType(14.99);
        } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_medium))) {
            setPriceFromType(16.99);
        } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_large))) {
            setPriceFromType(19.99);
        }
    }

    /**
     * Disables all toppings not relevant to Meatzza Pizza.
     * Also disables the ability to click toppings.
     */
    public void meatzzaPizzaSelectionsOnly() {
        setToppingsState(new int[]{
                R.id.toppingsChip_Sausage,
                R.id.toppingsChip_Pepperoni,
                R.id.toppingsChip_Beef,
                R.id.toppingsChip_Ham,
        }, true, true, false);

        setToppingsState(new int[]{
                R.id.toppingsChip_GreenPeppers,
                R.id.toppingsChip_Onions,
                R.id.toppingsChip_Mushrooms,
                R.id.toppingsChip_BBQChicken,
                R.id.toppingsChip_Provolone,
                R.id.toppingsChip_Cheddar
        }, false, false, false);

        Spinner pizzaSize = findViewById(R.id.spinner_pizzaSize);
        if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_small))) {
            setPriceFromType(17.99);
        } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_medium))) {
            setPriceFromType(19.99);
        } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_large))) {
            setPriceFromType(21.99);
        }
    }

    /**
     * Allows for the user to select up to 7 toppings for their pizza.
     */
    public void buildYourOwnPizzaSelectionsOnly() {
        setBuildYourOwnToppingsChips();

        Spinner pizzaSize = findViewById(R.id.spinner_pizzaSize);
        if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_small))) {
            currentBasePrice = 8.99;
        } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_medium))) {
            currentBasePrice = 10.99;
        } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_large))) {
            currentBasePrice = 12.99;
        }
        selectedToppingCount = 0;
        updateTotalPrice();
        ChipGroup toppings = findViewById(R.id.chipGroup_toppings);
        toppings.setOnCheckedStateChangeListener((group, checkedId) -> {
            // Update topping count
            selectedToppingCount = checkedId.size();
            if (selectedToppingCount > 6) {
                for (int i = 0; i < toppings.getChildCount(); i++) {
                    Chip chip = (Chip) toppings.getChildAt(i);
                    if (!chip.isChecked()) {
                        chip.setEnabled(false);
                    }
                }
                Toast.makeText(this, "You can only select 7 toppings", Toast.LENGTH_SHORT).show();
            } else {
                for (int i = 0; i < toppings.getChildCount(); i++) {
                    Chip chip = (Chip) toppings.getChildAt(i);
                    chip.setEnabled(true);
                }
            }
            // Recalculate total price
            updateTotalPrice();
        });
    }

    /**
     * Helper method to set the state of the toppings chips for build your own pizza
     */
    private void setBuildYourOwnToppingsChips() {
        int[] allToppingsIds = new int[]{
                R.id.toppingsChip_Sausage, R.id.toppingsChip_Pepperoni,
                R.id.toppingsChip_GreenPeppers, R.id.toppingsChip_Onions,
                R.id.toppingsChip_Mushrooms, R.id.toppingsChip_BBQChicken,
                R.id.toppingsChip_Beef, R.id.toppingsChip_Ham,
                R.id.toppingsChip_Provolone, R.id.toppingsChip_Cheddar
        };

        setToppingsState(allToppingsIds, false, true, true);
        clearAllToppings(allToppingsIds, false);
    }

    /**
     * This activates everytime the user selects a pizza size from the spinner.
     * Meant to update the price of the pizza based on the size selected combined with the type.
     * @param view This is the view containing the pizzaSize spinner
     */
    public void pizzaSizeChanged(View view) {
        Spinner pizzaSize = (Spinner) view;
        pizzaSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = pizzaSize.getSelectedItem().toString();
                String selectedPizza = ((Spinner) findViewById(R.id.spinner_newYorkPizzaType))
                        .getSelectedItem().toString();
                //checks if its build your own first, otherwise get the other pre-defined prices
                if (selectedSize.equals(getString(R.string.pizzaSize_small))) {
                    currentBasePrice = selectedPizza.equals(getString(R.string.new_york_buildyourown))
                            ? 8.99 : getPredefinedPizzaPrice(selectedPizza, "small");
                } else if (selectedSize.equals(getString(R.string.pizzaSize_medium))) {
                    currentBasePrice = selectedPizza.equals(getString(R.string.new_york_buildyourown))
                            ? 10.99 : getPredefinedPizzaPrice(selectedPizza, "medium");
                } else if (selectedSize.equals(getString(R.string.pizzaSize_large))) {
                    currentBasePrice = selectedPizza.equals(getString(R.string.new_york_buildyourown))
                            ? 12.99 : getPredefinedPizzaPrice(selectedPizza, "large");
                }

                // Recalculate the total price
                updateTotalPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Helper method to get predefined pizza price based on size.
     * @param pizzaType the pizzaType that is currently selected.
     * @param size the size of the current pizza.
     */
    private double getPredefinedPizzaPrice(String pizzaType, String size) {
        return switch (pizzaType) {
            case "New York Deluxe Pizza" ->
                    size.equals("small") ? 16.99 : size.equals("medium") ? 18.99 : 20.99;
            case "New York BBQ Chicken Pizza" ->
                    size.equals("small") ? 14.99 : size.equals("medium") ? 16.99 : 19.99;
            case "New York Meatzza Pizza" ->
                    size.equals("small") ? 17.99 : size.equals("medium") ? 19.99 : 21.99;
            default -> 0.0;
        };
    }

    /**
     * Updates the total price of the pizza based on the selected toppings
     */
    private void updateTotalPrice() {
        double totalPrice = currentBasePrice + (selectedToppingCount * toppingPrice);
        setPriceFromType(totalPrice);
    }

    /**
     * sets the price (text) based on the pizza type changing
     * @param price price of the pizza
     */
    public void setPriceFromType(double price) {
        TextView priceView = findViewById(R.id.textview_pizza_price);
       priceView.setText(String.format(Locale.getDefault(), "$%.2f", price));
    }

    /**
     * helper method that grabs the price of the total toppings when switching
     * from size to size on build your own pizza
     */
    private double grabPriceFromActiveToppings(){
        int selectedChipsCount = ((ChipGroup) findViewById
                (R.id.chipGroup_toppings)).getCheckedChipIds().size();
        return selectedChipsCount * 1.69;
    }

    /**
     * Helper method that sets enabled, disabled and clickable states of toppings chips
     * @param chipIds the id of the chip that is being modified
     * @param isSelected boolean determining if the chip will be pre-selected
     * @param isEnabled boolean determining if the chip will be enabled
     * @param isClickable boolean determining if the chip will be clickable
     */
    private void setToppingsState(int[] chipIds, boolean isSelected, boolean isEnabled, boolean isClickable) {
        for (int id : chipIds) {
            Chip chip = findViewById(id);
            chip.setSelected(isSelected);
            chip.setClickable(isClickable);
            chip.setEnabled(isEnabled);
        }
    }

    /**
     * helper method that makes all chips unchecked manually
     * @param chipIds an array containing the chipids (as an int) on the screen.
     * @param isChecked a boolean that can allow for the chips to become unchecked or checked
     */
    private void clearAllToppings(int[] chipIds, boolean isChecked) {
    for (int id : chipIds) {
        Chip chip = findViewById(id);
        chip.setChecked(isChecked);
    }
}

}