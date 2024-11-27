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
        return pizzas.size();
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
        return convertView;
    }


}