package Utils;

import static Utils.PizzaHelperMethods.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.rupizzeria_app_project4.R;

import java.util.ArrayList;
import java.util.Locale;

import Core.Pizza;

public class ListViewCurrentOrder extends BaseAdapter {

    /**
     * the current environment we are in (in this case the only one that it could be is the
     * Pizza Cart/CurrentOrderActivity)
     */
    private Context context;
    /**
     * An arraylist that contains all of the pizzas from the active order
     */
    private ArrayList<Pizza> pizzas;

    /**
     * The constructor for the ListView adapter
     * @param context the environment in which we are calling the constructor from
     * @param pizzas the arraylist of current pizzas in the cart that want to be displayed
     */
    public ListViewCurrentOrder(Context context, ArrayList<Pizza> pizzas) {
        this.context = context;
        this.pizzas = pizzas;
    }

    /**
     * method that grabs the amount of pizzas in the pizza list
     * @return
     */
    @Override
    public int getCount() {
        return pizzas.size()+1;
    }

    /**
     * method to grab a specific pizza from the pizza list.
     * @param position Position of the item whose data we want within the adapter's
     * data set.
     * @return The object at said position.
     */
    @Override
    public Object getItem(int position) {
        return pizzas.get(position);
    }

    /**
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return the id of the item that was found
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Method that will eventually create and return one of two views. One that will be that of a
     * billing card. Or one that will be a basic Pizza card. The method will run for the size of the
     * pizzas arraylist+1 (for the billing card).
     * @param position The position of the item within the adapter's data set of the item whose view
     *        we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param parent The parent that this view will eventually be attached to
     * @return the view of the listview.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == getCount() - 1) {
            convertView = displayTheBillingCard(convertView, parent);
        } else {
            convertView = displayThePizzaCard(position, convertView, parent);
        }
        return convertView;
    }

    /**
     * construction of the basic pizza card to display
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param parent The parent that this view will eventually be attached to
     * @return the fully constructed pizza card
     */
    @NonNull
    private View displayThePizzaCard(int position, View convertView, ViewGroup parent) {
        //oh boy this is gonna be a long comparison
        //done simply so that the program doesnt crash from attempting to mess with values that
        //do not exist yet.
        if (convertView == null || convertView.findViewById(R.id.textview_pizzaName) == null ||
                convertView.findViewById(R.id.pizzaSize_value) == null ||
                convertView.findViewById(R.id.pizza_card_price_value) == null ||
                convertView.findViewById(R.id.textview_PizzaCrust) == null ||
                convertView.findViewById(R.id.textview_PizzaToppings) == null ||
                convertView.findViewById(R.id.imageview_pizzaImage) == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pizza_item_view_basic, parent, false);
        }

        Pizza pizza = pizzas.get(position);
        String pizzaType = pizza.getClass().getSimpleName();
        String pizzaStyle = determineIfChicagoStyleOrNewYorkStyleText(pizzaType, pizza.getCrust());

        TextView pizzaName = convertView.findViewById(R.id.textview_pizzaName);
        TextView pizzaSize = convertView.findViewById(R.id.pizzaSize_value);
        TextView price = convertView.findViewById(R.id.pizza_card_price_value);
        TextView crust = convertView.findViewById(R.id.textview_PizzaCrust);
        TextView toppings = convertView.findViewById(R.id.textview_PizzaToppings);
        ImageView pizzaimage = convertView.findViewById(R.id.imageview_pizzaImage);
        String pizzaString = pizzaStyle + " " + pizzaType;

        pizzaName.setText(pizzaString);
        price.setText(String.format(Locale.US,"$%.2f", pizza.getPrice()));
        pizzaimage.setImageResource(determineIfChicagoStyleOrNewYorkStyleImage(pizzaType, pizza.getCrust()));
        crust.setText(pizza.getCrust().toString());
        toppings.setText(pizza.getToppings().toString());
        pizzaSize.setText(pizza.getSize().toString());
        return convertView;
    }

    /**
     * construction of the billing card at the end of the pizzas list
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *        is non-null and of an appropriate type before using. If it is not possible to convert
     *        this view to display the correct data, this method can create a new view.
     *        Heterogeneous lists can specify their number of view types, so that this View is
     *        always of the right type (see {@link #getViewTypeCount()} and
     *        {@link #getItemViewType(int)}).
     * @param parent The parent that this view will eventually be attached to
     * @return the fully constructed billing card
     */
    @NonNull
    private View displayTheBillingCard(View convertView, ViewGroup parent) {
        if (convertView == null || convertView.findViewById(R.id.textview_subtotal_value) == null
                || convertView.findViewById(R.id.textview_salestax_value) == null ||
                convertView.findViewById(R.id.textview_ordertotal_value) == null) {
            convertView = LayoutInflater.from(context).inflate
                    (R.layout.order_pizza_totals_view_basic, parent, false);
        }

        double subTotal = 0;
        for (Pizza pizza : pizzas) {
            subTotal+= pizza.getPrice();
        }

        double salesTax = subTotal * 0.06625;
        double orderTotal = subTotal + salesTax;

        TextView subTotalValue = convertView.findViewById(R.id.textview_subtotal_value);
        TextView salesTaxValue = convertView.findViewById(R.id.textview_salestax_value);
        TextView orderTotalValue = convertView.findViewById(R.id.textview_ordertotal_value);

        subTotalValue.setText(String.format(Locale.getDefault(),
                "%.2f", subTotal));
        salesTaxValue.setText(String.format(Locale.getDefault(),
                "%.2f", salesTax));
        orderTotalValue.setText(String.format(Locale.getDefault(),
                "%.2f", orderTotal));
        return convertView;
    }
}