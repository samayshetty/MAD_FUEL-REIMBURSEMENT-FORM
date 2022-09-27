package com.example.frf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameE = "";
                if (name.getText().toString().length() == 0) {
                    Toast.makeText(name.getContext(), "Please enter your name", Toast.LENGTH_LONG).show();
                    return;
                }else if(!name.getText().toString().matches("^[a-zA-Z\\s]+")){
                    Toast.makeText(name.getContext(), "Your name should consist only of alphabetical characters", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    nameE = name.getText().toString();
                }


                Intent intent = new Intent(MainActivity.this, WelcomePage.class);
                intent.putExtra("message",nameE);

                startActivity(intent);

            }
        });
    }

}
