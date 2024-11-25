package com.example.rupizzeria_app_project4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link newyorkPizzaGallery#newInstance} factory method to
 * create an instance of this fragment.
 */
public class newyorkPizzaGallery extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public newyorkPizzaGallery() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newyorkPizzaGallery.
     */
    // TODO: Rename and change types and number of parameters
    public static newyorkPizzaGallery newInstance(String param1, String param2) {
        newyorkPizzaGallery fragment = new newyorkPizzaGallery();
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
        return inflater.inflate(R.layout.fragment_newyork_pizza_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView myText = view.findViewById(R.id.textView2);
        myText.setText("@string/shopping_cart");

        //on the press of the button
        weirdbuttonPressed(view.findViewById(R.id.button5));
    }

    public void weirdbuttonPressed(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do a toast
                Toast.makeText(getContext(), "IT WOOOOOORKS!!!", Toast.LENGTH_SHORT).show();
                //set the current fragment to invisible
                getView().setVisibility(View.INVISIBLE);
            }
        });
    }

}