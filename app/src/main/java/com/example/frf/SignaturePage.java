package com.example.frf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SignaturePage extends AppCompatActivity {
    Button confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature_page);
        confirm = findViewById(R.id.confirmF);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(confirm.getContext());
                    builder.setTitle("Are you Sure?");
                    builder.setMessage("You will be logged out on clicking LOGOUT");

                    builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(confirm.getContext(), "You have been logged out!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignaturePage.this, MainActivity.class);
                            startActivity(intent);

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(confirm.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
            }
        });

    }
}