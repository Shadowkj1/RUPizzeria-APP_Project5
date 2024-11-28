package com.example.rupizzeria_app_project4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Core.Order;
import Core.Pizza;
import Utils.RecyclerViewAdapterOrderHistory;
import Utils.SingletonDataStorage;

public class OrderHistoryActivityController extends AppCompatActivity {

    /**
     * Arraylist that holds the orderHistory
     */
    ArrayList<Order> orderHistory;


    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //create an instance of the SingletonDataStorage class
        SingletonDataStorage GlobalPizzaData = SingletonDataStorage.getInstance();
        orderHistory = GlobalPizzaData.getOrderHistory();


        //create the recycler view
        populateAndCreateRecyclerView();

        //on click of back button
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
     * Populates the recycler view with the order history
     */
    private void populateAndCreateRecyclerView() {
        RecyclerViewAdapterOrderHistory adapter = new RecyclerViewAdapterOrderHistory(orderHistory, this);

        RecyclerView recyclerView = findViewById(R.id.orderHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}