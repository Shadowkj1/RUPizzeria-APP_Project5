package com.example.rupizzeria_app_project4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PizzaCustomizationScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PizzaCustomizationScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PizzaCustomizationScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PizzaCustomizationScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static PizzaCustomizationScreen newInstance(String param1, String param2) {
        PizzaCustomizationScreen fragment = new PizzaCustomizationScreen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pizza_customization_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //press the add to cart button
        weirdbuttonPressed(view.findViewById(R.id.add_to_cart_button));
    }

    public void weirdbuttonPressed(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do a toast
                Toast.makeText(getContext(), "IT WOOOOOORKS!!!", Toast.LENGTH_SHORT).show();

                // In the current fragment (main_newyork_fragment)
                FragmentManager fragmentManager = getParentFragmentManager();

                // Start a FragmentTransaction
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(PizzaCustomizationScreen.this);

                // Replace the current fragment with the new fragment
                Fragment newFragment = new newyorkPizzaGallery(); // Replace with your fragment class
                transaction.replace(R.id.mainFragmentHolder, newFragment, "CUSTOMIZATION_FRAGMENT_TAG");

                // Commit the transaction
                transaction.commit();
            }
        });
    }
}