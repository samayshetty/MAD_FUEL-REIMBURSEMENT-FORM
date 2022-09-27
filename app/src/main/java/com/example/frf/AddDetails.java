package com.example.frf;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddDetails extends AppCompatActivity {
    private EditText dateEdt, toEdt, fromEdt, purposeEdt, kmsEdt, advanceEdt, balanceEdt;
    private Button add1;
    private ModelClass modelClass;
    public ArrayList<ModelClass> add_data;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String id;
    int year;
    int month;
    int day;
    final public static String DEET ="details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);


        dateEdt = findViewById(R.id.date1);
        fromEdt = findViewById(R.id.from1);
        toEdt = findViewById(R.id.to1);
        purposeEdt = findViewById(R.id.purpose1);
        kmsEdt = findViewById(R.id.kms1);
        advanceEdt = findViewById(R.id.advance1);
        balanceEdt = findViewById(R.id.balance1);
        add1 = findViewById(R.id.add1);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Details");
        //    frdetails = FirebaseDatabase.getInstance().getReference().child("FuelReimbursement-Details");

        final Calendar calendar = Calendar.getInstance();
        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddDetails.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dateEdt.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();

            }
        });
    }
        private void insertData(){

            String date = "", from = "",to = "", purpose = "", kms = "", advance = "", balance = "";


            if (dateEdt.getText() != null) {
                date = dateEdt.getText().toString();
            }

            if (date.equals("")) {
                Toast.makeText(dateEdt.getContext(), "Please enter Date", Toast.LENGTH_LONG).show();
                return;
            }

            if (fromEdt.getText() != null) {
                from = fromEdt.getText().toString();
            }
            if (from.equals("")) {
                Toast.makeText(fromEdt.getContext(), "Please enter from location", Toast.LENGTH_LONG).show();
                return;
            }
            if (toEdt.getText() != null) {
                to = toEdt.getText().toString();
            }
            if (to.equals("")) {
                Toast.makeText(toEdt.getContext(), "Please enter to location", Toast.LENGTH_LONG).show();
                return;
            }
            if (purposeEdt.getText() != null) {
                purpose = purposeEdt.getText().toString();
            }
            if (purpose.equals("")) {
                Toast.makeText(purposeEdt.getContext(), "Please enter the purpose", Toast.LENGTH_LONG).show();
                return;
            }
            if (kmsEdt.getText() != null) {
                kms = kmsEdt.getText().toString();
            }
            if (kms.equals("")) {
                Toast.makeText(kmsEdt.getContext(), "Please enter kms rate", Toast.LENGTH_LONG).show();
                return;
            }
            if (advanceEdt.getText() != null) {
                advance = advanceEdt.getText().toString();
            }
            if (advance.equals("")) {
                Toast.makeText(advanceEdt.getContext(), "Please enter advance amount", Toast.LENGTH_LONG).show();
                return;
            }
            if (balanceEdt.getText() != null) {
                balance = balanceEdt.getText().toString();
            }
            if (balance.equals("")) {
                Toast.makeText(balanceEdt.getContext(), "Please enter balance amount", Toast.LENGTH_LONG).show();
                return;
            }


            Map<String, Object> map = new HashMap<>();
            map.put("date",date);
            map.put("from", from);
            map.put("to", to);
            map.put("purpose", purpose);
            map.put("kms", kms);
            map.put("advance", advance);
            map.put("balance", balance);
            FirebaseDatabase.getInstance().getReference().child("FR_DETAILS").push()
                    .setValue(map)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(AddDetails.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddDetails.this, "Error while Insertion...", Toast.LENGTH_SHORT).show();

                        }
                    });
            clearAll();


    }
    private void clearAll(){
        dateEdt.setText("");
        toEdt.setText("");
        fromEdt.setText("");
        purposeEdt.setText("");
        advanceEdt.setText("");
        balanceEdt.setText("");
        kmsEdt.setText("");
        finish();
    }

}























         /*   id= date;
            ModelClass modelClass= new ModelClass(date, from, to, purpose, kms, advance, balance,id);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.child(id).setValue(modelClass);
                    Toast.makeText(AddDetails.this, "Details added successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddDetails.this,frForm.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AddDetails.this, "Error is " + error.toString(), Toast.LENGTH_SHORT).show();

                }
            })

          /*  frDetails fr = new frDetails(strdate, strfrom, strto, strpurpose, strkms, stradvance, strbalance);
            frdetails.push().setValue(fr);
            Toast.makeText(add_details.this, "Data inserted successfully!", Toast.LENGTH_SHORT).show();
*/

/*
            Intent intent = new Intent();
            intent.putExtra(DEET, new ModelClass(strdate, strfrom , strto , strpurpose , strkms , stradvance , strbalance));
            setResult(RESULT_OK,intent);
            finish();
/*

            intent.putExtra("date",strdate);
            intent.putExtra("from",strfrom);
            intent.putExtra("to",strto);
            intent.putExtra("purpose",strpurpose);
            intent.putExtra("kms",strkms);
            intent.putExtra("advance",stradvance);
            intent.putExtra("balance",strbalance);
            startActivity(intent);*/
            /*add_data=new ArrayList<>();
            add_data.add(new ModelClass(strdate, strfrom , strto , strpurpose , strkms , stradvance , strbalance));

            Intent intent = new Intent(this,FRform.class);
            intent.putExtra("data",add_data);
            startActivity(intent);   */
