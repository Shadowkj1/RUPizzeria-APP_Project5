package Utils;

import static Utils.PizzaHelperMethods.determineIfChicagoStyleOrNewYorkStyleImage;
import static Utils.PizzaHelperMethods.determineIfChicagoStyleOrNewYorkStyleText;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rupizzeria_app_project4.R;

import java.util.ArrayList;

import Core.Order;
import Core.Pizza;

public class RecyclerViewAdapterOrderHistory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<Object> items; // Combines Orders and their Pizzas
    private final Context context;

    // View Types for Order and Pizza
    private static final int VIEW_TYPE_ORDER = 0;
    private static final int VIEW_TYPE_PIZZA = 1;

    // Constructor that prepares a combined list of Orders and their Pizzas
    public RecyclerViewAdapterOrderHistory(ArrayList<Order> orderHistory, Context context) {
        this.context = context;
        this.items = new ArrayList<>();
        SingletonDataStorage GlobalPizzaData = SingletonDataStorage.getInstance();
        orderHistory = GlobalPizzaData.getOrderHistory();

        // Flatten orders and pizzas into one list
        for (Order order : orderHistory) {
            items.add(order); // Add the Order object
            items.addAll(order.getPizzas()); // Add all associated Pizza objects
        }
        for (Object item : items) {
            System.out.println("This is an item!!! :" +item);
        }
        System.out.println("This is the size of the items array: " + items.size());
        System.out.println("This is the size of the orderHistory array: " + orderHistory.size());
        System.out.println("This is the size of the pizzas array: " + orderHistory.get(0).getPizzas().size());
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Order) {
            return VIEW_TYPE_ORDER; // Indicates this item is an Order
        } else if (items.get(position) instanceof Pizza) {
            return VIEW_TYPE_PIZZA; // Indicates this item is a Pizza
        }
        throw new IllegalStateException("Unknown item type at position " + position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout depending on item type
        if (viewType == VIEW_TYPE_ORDER) {
            View view = LayoutInflater.from(context).inflate(R.layout.order_number_card, parent, false);
            return new OrderViewHolder(view);
        } else { // VIEW_TYPE_PIZZA
            View view = LayoutInflater.from(context).inflate(R.layout.pizza_item_view_basic, parent, false);
            return new PizzaViewHolder(view);
        }
    }

    @SuppressLint({"DefaultLocale", "NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Handle binding based on item type
        if (holder instanceof OrderViewHolder orderHolder) {
            Order order = (Order) items.get(position);

            orderHolder.orderNumberText.setText(Integer.toString(order.getNumber()));

            // Calculate the total price for the order
            double totalPrice = 0.0;
            for (Pizza pizza : order.getPizzas()) {
                totalPrice += pizza.getPrice();
            }

            orderHolder.orderTotalText.setText(String.format("%.2f", totalPrice));

            orderHolder.itemView.setOnClickListener(v -> {
                new AlertDialog.Builder(context)
                        .setTitle("Cancel Order")
                        .setMessage("Do you wish to cancel this order?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Remove order and all its pizzas
                            int start = items.indexOf(order);
                            int end = start + order.getPizzas().size() + 1; // +1 for the order itself
                            items.subList(start, end).clear();
                            notifyDataSetChanged(); // Refresh RecyclerView
                            Toast.makeText(context, "Order #" + order.getNumber()
                                    + " canceled", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("No", null)
                        .show();
            });

        } else if (holder instanceof PizzaViewHolder pizzaHolder) {
            Pizza pizza = (Pizza) items.get(position); // Cast item to Pizza
            String pizzaType = pizza.getClass().getSimpleName();
            String pizzaStyle = determineIfChicagoStyleOrNewYorkStyleText(
                    pizzaType, pizza.getCrust());
            String newPizzaName = pizzaType + " " + pizzaStyle;

            pizzaHolder.pizzaName.setText(newPizzaName);
            pizzaHolder.pizzaPrice.setText(String.format("$%.2f", pizza.getPrice()));
            pizzaHolder.pizzaCrust.setText(pizza.getCrust().toString());
            pizzaHolder.pizzaToppings.setText(pizza.getToppings().toString());
            pizzaHolder.pizzaImage.setImageResource(determineIfChicagoStyleOrNewYorkStyleImage(
                    pizza.getClass().getSimpleName(), pizza.getCrust()));
            pizzaHolder.pizzaSize.setText(pizza.getSize().toString());
        }
    }

    // ViewHolder for Order Card
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumberText, orderTotalText;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumberText = itemView.findViewById(R.id.orderhistory_ordernumber_value);
            orderTotalText = itemView.findViewById(R.id.orderhistory_orderTotal_value);
        }
    }

    // ViewHolder for Pizza Item
    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pizzaName, pizzaPrice, pizzaCrust, pizzaToppings,pizzaSize;
        ImageView pizzaImage;
        String pizzaStyle,pizzaType;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaName = itemView.findViewById(R.id.textview_pizzaName);
            pizzaPrice = itemView.findViewById(R.id.pizza_card_price_value);
            pizzaCrust = itemView.findViewById(R.id.textview_PizzaCrust);
            pizzaToppings = itemView.findViewById(R.id.textview_PizzaToppings);
            pizzaImage = itemView.findViewById(R.id.imageview_pizzaImage);
            pizzaSize = itemView.findViewById(R.id.pizzaSize_value);
        }
    }
}
