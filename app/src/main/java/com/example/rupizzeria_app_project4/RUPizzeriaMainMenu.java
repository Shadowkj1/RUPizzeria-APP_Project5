package com.example.rupizzeria_app_project4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class RUPizzeriaMainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ru_pizzeria_main_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //setup B-role screen
        setupBRoleVideo(findViewById(R.id.pizzaB_Role));
        //What happens when you click the OrderNow button
        showPizzaMenuOptions(findViewById(R.id.ordersButton));
        //What happens when you click the Back button
        resetMainMenu(findViewById(R.id.back_button));
        //What happens when you click the New York Pizza button

        //What happens when you click the Chicago Pizza button
    }

    /**
     * Method will reset the main menu to its original state.
     * Hiding the back button, shifting the title, and hiding the cart button
     * @param view the view containing the back button
     */
    public void resetMainMenu(View view) {
        ImageButton backButton = (ImageButton)view;
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the visibility of the main menu buttons
                findViewById(R.id.orderNewYorkButton).setVisibility(View.INVISIBLE);
                findViewById(R.id.orderChicagoButton).setVisibility(View.INVISIBLE);
                findViewById(R.id.cart_button).setVisibility(View.INVISIBLE);
                findViewById(R.id.back_button).setVisibility(View.INVISIBLE);
                findViewById(R.id.orderHistoryButton).setVisibility(View.VISIBLE);
                findViewById(R.id.ordersButton).setVisibility(View.VISIBLE);
                //move the title back to the left
                Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams)
                        findViewById(R.id.mainMenuToolbar_title).getLayoutParams();
                layoutParams.gravity = Gravity.START;
                findViewById(R.id.mainMenuToolbar_title).setLayoutParams(layoutParams);
            }
                                      });
    }

    /**
     * Method will set the visuals of the main menu to the
     * two options for ordering pizza
     * @param view the view containing the order button
     */
    public void showPizzaMenuOptions(View view) {
        Button OrderNowButton = (Button)view;
        OrderNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Set the visibility of the main menu buttons
                OrderNowButton.setVisibility(View.INVISIBLE);
                findViewById(R.id.orderHistoryButton).setVisibility(View.INVISIBLE);
                findViewById(R.id.orderNewYorkButton).setVisibility(View.VISIBLE);
                findViewById(R.id.orderChicagoButton).setVisibility(View.VISIBLE);
                findViewById(R.id.cart_button).setVisibility(View.VISIBLE);
                findViewById(R.id.back_button).setVisibility(View.VISIBLE);

                //change the layout parameters of the toolbar title
                Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams)
                        findViewById(R.id.mainMenuToolbar_title).getLayoutParams();
                layoutParams.gravity = android.view.Gravity.CENTER;
                findViewById(R.id.mainMenuToolbar_title).setLayoutParams(layoutParams);

            }
        });
    }


    /**
     * Method to grab the video and display it in the background of the
     * main menu
     * @param view the view containing the video to be displayed
     */
    public void setupBRoleVideo(View view) {
        VideoView pizzaBROLE = (VideoView) view;
        //set the video path
        Uri videouri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pizza_b_role_final);
        pizzaBROLE.setVideoURI(videouri);
        //start looping
        pizzaBROLE.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
        //start the video
        pizzaBROLE.start();
    }


    /**
     //     * Simple test method for displaying a Toast window.
     //     * @param view I dunno its just a button man
     //     */
//    public void buttonCLICKED(View view) {
//        Toast.makeText(this, "Button clicked!", Toast.LENGTH_LONG).show();
//    }
}