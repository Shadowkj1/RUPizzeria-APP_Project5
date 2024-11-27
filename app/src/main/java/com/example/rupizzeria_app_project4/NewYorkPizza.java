package com.example.rupizzeria_app_project4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import Core.Order;
import Core.Pizza;
import Utils.SingletonDataStorage;

public class NewYorkPizza extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_york_pizza);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainFragmentHolder), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //create an instance of the SingletonDataStorage class
        SingletonDataStorage GlobalPizzaData = SingletonDataStorage.getInstance();
        ArrayList<Pizza> currentOrder = GlobalPizzaData.getCurrentOrder();
        ArrayList<Order> orderHistory = GlobalPizzaData.getOrderHistory();

        //when the user selects a pizza type
        pizzaTypeChanged(findViewById(R.id.spinner_newYorkPizzaType));
        //When the user selects a pizza size
        pizzaSizeChanged(findViewById(R.id.spinner_pizzaSize));
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
                if (selectedPizza.equals(getString(R.string.new_york_deluxe))) {
                    //change the crust to Brooklyn
                    crustType.setText(getString(R.string.crust_Brooklyn));
                    deluxePizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_bbq))) {
                    //change the crust to thin
                    crustType.setText(getString(R.string.crust_Thin));
                    bbqChickenPizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_meatzza))) {
                    //change the crust to Hand-tossed
                    crustType.setText(getString(R.string.crust_HandTossed));
                    meatzzaPizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_buildyourown))) {
                    //change the crust to Hand-tossed
                    crustType.setText(getString(R.string.crust_HandTossed));
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

    public void buildYourOwnPizzaSelectionsOnly() {
        setToppingsState(new int[]{
                R.id.toppingsChip_Sausage, R.id.toppingsChip_Pepperoni,
                R.id.toppingsChip_GreenPeppers, R.id.toppingsChip_Onions,
                R.id.toppingsChip_Mushrooms, R.id.toppingsChip_BBQChicken,
                R.id.toppingsChip_Beef, R.id.toppingsChip_Ham,
                R.id.toppingsChip_Provolone, R.id.toppingsChip_Cheddar
        }, false, true, true);

        // Initialize price for build-your-own
        Spinner pizzaSize = findViewById(R.id.spinner_pizzaSize);
        if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_small))) {
            currentBasePrice = 8.99;
        } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_medium))) {
            currentBasePrice = 10.99;
        } else if (pizzaSize.getSelectedItem().toString().equals(getString(R.string.pizzaSize_large))) {
            currentBasePrice = 12.99;
        }
        selectedToppingCount = 0; // Reset topping count
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



    public void pizzaSizeChanged(View view) {
        Spinner pizzaSize = (Spinner) view;
        pizzaSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedSize = pizzaSize.getSelectedItem().toString();
                String selectedPizza = ((Spinner) findViewById(R.id.spinner_newYorkPizzaType))
                        .getSelectedItem().toString();

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

    private void updateTotalPrice() {
        double totalPrice = currentBasePrice + (selectedToppingCount * toppingPrice);
        setPriceFromType(totalPrice);
    }


    /**
     * if the pizza is large, set the price
     * @param largePizza the selected pizza size (large)
     */
    private void priceForLargePizza(String largePizza) {
        if (largePizza.equals(getString(R.string.new_york_deluxe))) {
            setPriceFromType(20.99);
        } else if (largePizza.equals(getString(R.string.new_york_bbq))) {
            setPriceFromType(19.99);
        } else if (largePizza.equals(getString(R.string.new_york_meatzza))) {
            setPriceFromType(21.99);
        } else if (largePizza.equals(getString(R.string.new_york_buildyourown))) {
            setPriceFromType(12.99+grabPriceFromActiveToppings());
        }
    }

    /**
     * if the pizza is small, set the price
     * @param smallPizza the selected pizza size (small)
     */
    private void priceForSmallPizza(String smallPizza) {
        if (smallPizza.equals(getString(R.string.new_york_deluxe))) {
            setPriceFromType(16.99);
        } else if (smallPizza.equals(getString(R.string.new_york_bbq))) {
            setPriceFromType(14.99);
        } else if (smallPizza.equals(getString(R.string.new_york_meatzza))) {
            setPriceFromType(17.99);
        } else if (smallPizza.equals(getString(R.string.new_york_buildyourown))) {
            //grab the amount of chips selected from the chipgroup
            setPriceFromType(8.99+grabPriceFromActiveToppings());
        }
    }

    /**
     * sets the price (text) based on the pizza size changing
     * @param MediumPizza the selected pizza size (medium)
     */
    private void priceForMediumPizza(String MediumPizza) {
        if (MediumPizza.equals(getString(R.string.new_york_deluxe))) {
            setPriceFromType(18.99);
        } else if (MediumPizza.equals(getString(R.string.new_york_bbq))) {
            setPriceFromType(16.99);
        } else if (MediumPizza.equals(getString(R.string.new_york_meatzza))) {
            setPriceFromType(19.99);
        } else if (MediumPizza.equals(getString(R.string.new_york_buildyourown))) {
            setPriceFromType(10.99+grabPriceFromActiveToppings());
        }
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

}