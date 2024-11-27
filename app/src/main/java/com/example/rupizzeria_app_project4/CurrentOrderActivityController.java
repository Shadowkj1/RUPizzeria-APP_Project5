package com.example.rupizzeria_app_project4;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
        populateAndCreateListView();
    }


    /**
     * Method to create the recycler view for the current order screen
     */
    private void populateAndCreateListView() {
        ListView listView = findViewById(R.id.listview_pizzaList);
        ListViewPizzaAdapter adapter = new ListViewPizzaAdapter(this, currentOrder);
        listView.setAdapter(adapter);

        //on click of the list view item
        listView.setOnItemClickListener((parent, view, position, id) -> {
            System.out.println("Position: " + position);
            AlertDialog dialog = deleteOptionDialog(position);
            dialog.show();
        });
    }

    /**
     * Method to send the user back to the main menu
     */
    private void sendUserBackToMainMenu() {
        findViewById(R.id.back_button).setOnClickListener(v -> {
            finish();
        });
    }

    AlertDialog deleteOptionDialog(int PizzaPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you wish to remove this Pizza from the current order?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //remove the pizza from the current order
                currentOrder.remove(PizzaPosition);
                //refresh the list view
                populateAndCreateListView();
                //show a toast message
                Toast.makeText(CurrentOrderActivityController.this,
                        "Pizza removed from order", Toast.LENGTH_SHORT).show();
                //dismiss the dialog
                dialog.dismiss();
            }

        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        return builder.create();
    }
}