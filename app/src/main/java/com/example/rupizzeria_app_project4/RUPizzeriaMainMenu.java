package com.example.rupizzeria_app_project4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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


        //Button creation and setOnclickListeners must be declared in the onCreate method.
//        Button JButton = findViewById(R.id.Jovonte);
//        JButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(RUPizzeriaMainMenu.this, "HOWDY!!!", Toast.LENGTH_LONG).show();
//
//                //make a new intent to go to the NewYorkPizza activity
//                Intent intent = new Intent(RUPizzeriaMainMenu.this, NewYorkPizza.class);
//
//                startActivity(intent);
//            }
//
//
//        });
    }

    /**
     * Simple test method for displaying a Toast window.
     * @param view I dunno its just a button man
     */
    public void buttonCLICKED(View view) {
        Toast.makeText(this, "Button clicked!", Toast.LENGTH_LONG).show();
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
}