package com.example.rupizzeria_app_project4;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import Core.Order;
import Core.Pizza;
import Utils.ListViewPizzaAdapter;
import Utils.SingletonDataStorage;

public class CurrentOrderActivityController extends AppCompatActivity {

    /**
     * holds the current order
     */
    private ArrayList<Pizza> currentOrder;

    /**
     * holds the order history
     */
    private ArrayList<Order> orderHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_current_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainFragmentHolder), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //create an instance of the SingletonDataStorage class
        SingletonDataStorage GlobalPizzaData = SingletonDataStorage.getInstance();
        currentOrder = GlobalPizzaData.getCurrentOrder();
        orderHistory = GlobalPizzaData.getOrderHistory();

        //on click of back button
        sendUserBackToMainMenu();

        //create the list view
        populateListView();
    }


    /**
     * Method to create the recycler view for the current order screen
     */
    private void populateListView() {
        ListView listView = findViewById(R.id.listview_pizzaList);
        ListViewPizzaAdapter adapter = new ListViewPizzaAdapter(this, currentOrder);
        listView.setAdapter(adapter);
    }

    /**
     * Method to send the user back to the main menu
     */
    private void sendUserBackToMainMenu() {
        findViewById(R.id.back_button).setOnClickListener(v -> {
            finish();
        });
    }
}