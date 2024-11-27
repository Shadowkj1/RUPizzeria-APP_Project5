package Utils;

import static Utils.PizzaHelperMethods.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rupizzeria_app_project4.R;

import java.util.ArrayList;
import java.util.Locale;

import Core.Pizza;

public class ListViewPizzaAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Pizza> pizzas;

    public ListViewPizzaAdapter(Context context, ArrayList<Pizza> pizzas) {
        this.context = context;
        this.pizzas = pizzas;
    }

    @Override
    public int getCount() {
        return pizzas.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return pizzas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (position == getCount() - 1) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.order_pizza_totals_view_basic, parent, false);
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

        } else {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.pizza_item_view_basic, parent, false);
            }

            Pizza pizza = pizzas.get(position);
            String pizzaType = pizza.getClass().getSimpleName();
            String pizzaStyle = determineIfChicagoStyleOrNewYorkStyleText(pizzaType, pizza.getCrust());

            TextView pizzaName = convertView.findViewById(R.id.textview_pizzaName);
            TextView price = convertView.findViewById(R.id.textview_PizzaToppings);
            TextView crust = convertView.findViewById(R.id.textview_PizzaCrust);
            ImageView pizzaimage = convertView.findViewById(R.id.imageview_pizzaImage);
            String pizzaString = pizzaStyle + " " + pizzaType;

            pizzaName.setText(pizzaString);
            price.setText(String.format("$%.2f", pizza.getPrice()));
            pizzaimage.setImageResource(determineIfChicagoStyleOrNewYorkStyleImage(pizzaType, pizza.getCrust()));
            crust.setText(pizza.getCrust().toString());

        }
        return convertView;
    }


}