package com.example.frf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomePage extends AppCompatActivity {
    TextView welcome;

    Button frf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome = findViewById(R.id.welcome);

        frf = findViewById(R.id.frf);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");

        welcome.setText("WELCOME " + str.toUpperCase() + " !");


        frf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WelcomePage.this, frForm.class);
                startActivity(intent);



    }
});
    }}

