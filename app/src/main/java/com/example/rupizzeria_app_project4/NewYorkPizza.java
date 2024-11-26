package com.example.rupizzeria_app_project4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import Core.Order;
import Core.Pizza;
import Utils.SingletonDataStorage;

public class NewYorkPizza extends AppCompatActivity {

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
                if (selectedPizza.equals(getString(R.string.new_york_deluxe))) {
                    Toast.makeText(getApplicationContext(), "Deluxe Pizza selected",
                            Toast.LENGTH_SHORT).show();
                    //Deselect everything that is not Deluxe
                    deluxePizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_bbq))) {
                    Toast.makeText(getApplicationContext(), "BBQ Pizza selected",
                            Toast.LENGTH_SHORT).show();
                    bbqChickenPizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_meatzza))) {
                    Toast.makeText(getApplicationContext(), "MEATZZA Pizza selected",
                            Toast.LENGTH_SHORT).show();
                    meatzzaPizzaSelectionsOnly();
                } else if (selectedPizza.equals(getString(R.string.new_york_buildyourown))) {
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


    }

    /**
     * Enables all toppings and allows user to select toppings.
     * but only to a limit of 7!
     */
    public void buildYourOwnPizzaSelectionsOnly() {
        setToppingsState(new int[]{
                R.id.toppingsChip_Sausage, R.id.toppingsChip_Pepperoni,
                R.id.toppingsChip_GreenPeppers, R.id.toppingsChip_Onions,
                R.id.toppingsChip_Mushrooms,R.id.toppingsChip_BBQChicken,
                R.id.toppingsChip_Beef, R.id.toppingsChip_Ham,
                R.id.toppingsChip_Provolone, R.id.toppingsChip_Cheddar
        }, false, true, true);

        ChipGroup toppings = findViewById(R.id.chipGroup_toppings);
        //I set it to 6 here but you'll get why in a second
        int maxToppings = 6;
        toppings.setOnCheckedStateChangeListener((group, checkedId) -> {
            if (checkedId.size() > maxToppings) {
                for (int i=0; i<toppings.getChildCount(); i++) {
                    Chip chip = (Chip) toppings.getChildAt(i);
                    if (!chip.isChecked()) {
                        chip.setEnabled(false);
                    }
                }
                Toast.makeText(this, "You can only select " + (maxToppings+1)
                        + " toppings", Toast.LENGTH_SHORT).show();
            } else {
                for (int i=0; i<toppings.getChildCount(); i++) {
                    Chip chip = (Chip) toppings.getChildAt(i);
                    chip.setEnabled(true);
                }
            }
        });
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