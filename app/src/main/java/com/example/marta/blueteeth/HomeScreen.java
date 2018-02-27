package com.example.marta.blueteeth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }


    public void testMethod(View view) {
        Button button = findViewById(R.id.button);
        button.setBackgroundColor(this.getResources().getColor(R.color.southWhite));
        Intent switchPage = new Intent(HomeScreen.this, StudentHomeActivity.class);
        startActivity(switchPage);
    }
}
