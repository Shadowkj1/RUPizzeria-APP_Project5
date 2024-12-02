package com.example.rupizzeria_app_project4;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
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
import Utils.ListViewCurrentOrder;
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

    /**
     * simple default onCreate method. runs when the active view is seen/created.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_current_order);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
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

        //set the order number
        setOrderNumber();

        //create the list view
        populateAndCreateListView();

        //on click of the Place Order button
        putOrderInHistory();

        //on click of the Clear Order button
        clearOrder();
    }

    /**
     * Method to set the order number
     */
    private void setOrderNumber() {
        int orderNumber = orderHistory.size()+1;
        String orderNumberString = "Order#\n" + orderNumber;
        TextView orderNumberTextView = findViewById(R.id.textview_order_number_text);
        orderNumberTextView.setText(orderNumberString);
    }


    /**
     * Method to place the current orders pizzas in the order history
     */
    private void putOrderInHistory() {
        findViewById(R.id.place_orders_button).setOnClickListener(v -> {
            if (currentOrder.isEmpty()) {
                Toast.makeText(CurrentOrderActivityController.this,
                        "No pizzas in order to add", Toast.LENGTH_LONG).show();
            } else {
                Order order = new Order(orderHistory.size() + 1, new ArrayList<>(currentOrder));
                orderHistory.add(order);
                currentOrder.clear();
                populateAndCreateListView();
                setOrderNumber();
                Toast.makeText(CurrentOrderActivityController.this,
                        "Order placed", Toast.LENGTH_LONG).show();
            }
        });
    }



    /**
     * Method to clear the current order
     */
    private void clearOrder() {
        findViewById(R.id.clear_pizzas_button).setOnClickListener(v -> {
            currentOrder.clear();
            populateAndCreateListView();
            Toast.makeText(CurrentOrderActivityController.this,
                    "Order cleared", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Method to create the recycler view for the current order screen
     */
    private void populateAndCreateListView() {
        ListView listView = findViewById(R.id.listview_pizzaList);
        ListViewCurrentOrder adapter = new ListViewCurrentOrder(this, currentOrder);
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

    /**
     * Alert dialog method that will create a confirmation screen to make sure the user
     * wants to delete the pizza they have selected.
     * @param PizzaPosition the position of the pizza that was selected on screen
     * @return the Alert box that will be displayed on screen.
     */
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