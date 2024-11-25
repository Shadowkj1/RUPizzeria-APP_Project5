package com.example.rupizzeria_app_project4;

import android.os.Bundle;
import android.view.View;
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

        //pass the c
        preventTooManyToppings(findViewById(R.id.chipGroup_toppings), 6);
    }

    public void preventTooManyToppings(View view, int maxToppings) {
        //code to prevent user from adding too many toppings
        ChipGroup toppings = (ChipGroup) view;
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
}