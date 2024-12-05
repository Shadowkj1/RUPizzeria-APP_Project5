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
import java.util.Locale;

import Core.Order;
import Core.Pizza;

public class RecyclerViewAdapterOrderHistory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * an arraylist containing the order card + the pizzas themselves
     */
    private final ArrayList<Object> items; // Combines Orders and their Pizzas
    /**
     * the context of the old view
     */
    private final Context context;

    /**
     * int that determines of a card will be a order info card
     */
    private static final int VIEW_TYPE_ORDER = 0;
    /**
     * int that determines of a card will be a pizza card
     */
    private static final int VIEW_TYPE_PIZZA = 1;

    /**
     * Constructor for the RecyclerView
     * @param orderHistory The arraylist containing all of the orders so far
     * @param context The context of the old view
     */
    public RecyclerViewAdapterOrderHistory(ArrayList<Order> orderHistory, Context context) {
        this.context = context;
        this.items = new ArrayList<>();

        // Flatten orders and pizzas into one list
        for (Order order : orderHistory) {
            items.add(order); // Add the Order object
            items.addAll(order.getPizzas()); // Add all associated Pizza objects
        }
    }

    /**
     * Determine if the resulting card that will be displayed next will be a Pizza card or an Order
     * card
     * @param position position to query
     * @return the int of either the decision of order card (VIEW_TYPE_ORDER) or pizza card
     * (VIEW_TYPE_PIZZA).
     */
    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof Order) {
            return VIEW_TYPE_ORDER; // Indicates this item is an Order
        } else if (items.get(position) instanceof Pizza) {
            return VIEW_TYPE_PIZZA; // Indicates this item is a Pizza
        }
        throw new IllegalStateException("Unknown item type at position " + position);
    }

    /**
     * simple method that grabs the count/size of the items list
     * @return the size as an int
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     *  The onCreate method for ViewHolders. determines which ViewHolder should be displayed when
     *  called.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return The Viewholder of the calculated type. order or pizza
     */
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

    /**
     *  Method that determines the actions that should happen to the view upon its creation on the
     *  screen. so moments after its creation.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @SuppressLint({"DefaultLocale", "NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Handle binding based on item type
        if (holder instanceof OrderViewHolder orderHolder) {
            createTheOrderCard(position, orderHolder);

        } else if (holder instanceof PizzaViewHolder pizzaHolder) {
            createThePizzaCard(position, pizzaHolder);
        }
    }

    /**
     * Helper method for onBindViewHolder that holds the logic for what the order card does
     * after creation
     * @param position The position of the item within the adapter's data set
     * @param orderHolder The view/scene that contains all of the UI we need to access.
     *                    The order card UI
     */
    @SuppressLint({"SetTextI18n", "DefaultLocale", "NotifyDataSetChanged"})
    private void createTheOrderCard(int position, OrderViewHolder orderHolder) {
        Order order = (Order) items.get(position);

        orderHolder.orderNumberText.setText(Integer.toString(order.getNumber()));

        // Calculate the total price for the order
        double totalPrice = 0.0;
        for (Pizza pizza : order.getPizzas()) {
            totalPrice += pizza.getPrice();
        }

        orderHolder.orderTotalText.setText(String.format(Locale.US,"%.2f", totalPrice));

        // an onclick for when you click on the order card (to delete the order from the list)
        orderHolder.itemView.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Cancel Order Confirmation")
                    .setMessage("Do you wish to cancel this order (Order #" + (order.getNumber()) + ")?")
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
    }

    /**
     * Helper method for onBindViewHolder that holds the logic for what the pizza card does after
     * creation
     * @param position position of the active pizza/item in the arraylist.
     * @param pizzaHolder The view/scene that contains all of the UI we need to access. The pizza
     *                    card UI
     */
    private void createThePizzaCard(int position, PizzaViewHolder pizzaHolder) {
        Pizza pizza = (Pizza) items.get(position); // Cast item to Pizza
        String pizzaType = pizza.getClass().getSimpleName();
        String pizzaStyle = determineIfChicagoStyleOrNewYorkStyleText(
                pizzaType, pizza.getCrust());
        String newPizzaName = pizzaType + " " + pizzaStyle;

        pizzaHolder.pizzaName.setText(newPizzaName);
        pizzaHolder.pizzaPrice.setText(String.format(Locale.US, "$%.2f", pizza.getPrice()));
        pizzaHolder.pizzaCrust.setText(pizza.getCrust().toString());
        pizzaHolder.pizzaToppings.setText(pizza.getToppings().toString());
        pizzaHolder.pizzaImage.setImageResource(determineIfChicagoStyleOrNewYorkStyleImage(
                pizza.getClass().getSimpleName(), pizza.getCrust()));
        pizzaHolder.pizzaSize.setText(pizza.getSize().toString());
    }

    /**
     * Grabs the values for the order card
     */
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumberText, orderTotalText;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumberText = itemView.findViewById(R.id.orderhistory_ordernumber_value);
            orderTotalText = itemView.findViewById(R.id.orderhistory_orderTotal_value);
        }
    }

    /**
     * Grabs the values for the pizza card
     */
    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        TextView pizzaName, pizzaPrice, pizzaCrust, pizzaToppings,pizzaSize;
        ImageView pizzaImage;
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
