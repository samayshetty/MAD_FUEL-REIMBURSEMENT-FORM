package com.example.frf;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class frForm extends AppCompatActivity /*implements DataAdapter.OnEditListener*/ {
    RecyclerView recycle;
    EditText namee;
    EditText vtt;
    EditText deptt;
    EditText vnn;
    //DatabaseReference employeedbref;
    Button submit;
    Button add;
    Button confirm;
    //  ArrayList<ModelClass> datalist;
    DataAdapter datadapterlist;
    //   public ArrayList<ModelClass> add_data;
    static public String strdate, strfrom, strto, strpurpose, strkms, stradvance, strbalance;
    // private DataAdapter dataAdapter;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    // static String date1, to1, from1, purpose1, kms1, advance1, balance1;

    AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frform);
        //    datalist = new ArrayList<>();
        firebaseDatabase = getInstance();
        databaseReference = firebaseDatabase.getReference("Details");
        namee = findViewById(R.id.namee);
        vtt = findViewById(R.id.vtt);
        deptt = findViewById(R.id.deptt);
        vnn = findViewById(R.id.vnn);
        submit = findViewById(R.id.submit);
        add = findViewById(R.id.add);
        confirm = findViewById(R.id.confirm);
        //dataAdapter = new DataAdapter(this,datalist,this);

        recycle = findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        FirebaseRecyclerOptions<ModelClass> options =
                new FirebaseRecyclerOptions.Builder<ModelClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FR_DETAILS"), ModelClass.class)
                        .build();
        datadapterlist = new DataAdapter(options);
        recycle.setAdapter(datadapterlist);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AddDetails.class);
                startActivity(intent);
                //  startForResult.launch(intent);
                // fillform(strdate,strfrom,strto,strpurpose,strkms,stradvance,strbalance);


/*
                strdate = intent.getStringExtra("date");
                strfrom = intent.getStringExtra("from");
                strto= intent.getStringExtra("to");
                strpurpose= intent.getStringExtra("purpose");
                strkms =intent.getStringExtra("kms");
                stradvance= intent.getStringExtra("advance");
                strbalance = intent.getStringExtra("balance");
                fillform(strdate,strfrom,strto,strpurpose,strkms,stradvance,strbalance);

 */

             /*   date1=strdate;
                from1=strfrom;
                to1=strto;
                purpose1=strpurpose;
                kms1=strkms;
                advance1=stradvance;
                balance1=strbalance;
*/


                /* add_data=(ArrayList<ModelClass>)getIntent().getSerializableExtra("data");
                fillform(add_data.get(0).getDate(),
                        add_data.get(0).getFrom(),
                        add_data.get(0).getTo(),
                        add_data.get(0).getPurpose(),
                        add_data.get(0).getKms(),
                        add_data.get(0).getAdvance(),
                        add_data.get(0).getBalance());
               */
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameFR = "";
                if (namee.getText().toString().length() == 0) {
                    Toast.makeText(namee.getContext(), "Please enter your name", Toast.LENGTH_LONG).show();
                    return;
                }else if(!namee.getText().toString().matches("^[a-zA-Z\\s]+")){
                    Toast.makeText(namee.getContext(), "Your name should consist only of alphabetical characters", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    nameFR = namee.getText().toString();
                }
                String vttFR = "";
                if (vtt.getText().toString().length() == 0) {
                    Toast.makeText(vtt.getContext(), "Please enter the vehicle type", Toast.LENGTH_LONG).show();
                    return;
                }else if(!vtt.getText().toString().matches("^[a-zA-Z\\s]+")){
                    Toast.makeText(vtt.getContext(), "Vehicle type should consist only of alphabetical characters", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    vttFR = vtt.getText().toString();
                }
                String deptFR = "";
                if (deptt.getText().toString().length() == 0) {
                    Toast.makeText(deptt.getContext(), "Please enter the department", Toast.LENGTH_LONG).show();
                    return;
                }else if(!deptt.getText().toString().matches("^[a-zA-Z\\s]+")){
                    Toast.makeText(deptt.getContext(), "Department name should consist only of alphabetical characters", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    deptFR = deptt.getText().toString();
                }
                String vnFR = "";
                if (vnn.getText().toString().length() == 0) {
                    Toast.makeText(vnn.getContext(), "Please enter the vehicle name", Toast.LENGTH_LONG).show();
                    return;
                }else if(!vnn.getText().toString().matches("^[a-zA-Z\\s]+")){
                    Toast.makeText(vnn.getContext(), "Vehicle name should consist only of alphabetical characters", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    vnFR = vnn.getText().toString();
                }
                //insertempdata();
            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(confirm.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("You cannot change the details after you click OK");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(frForm.this, SignaturePage.class);
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
       /* submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertempdata();
            }
        });*/



    }


    @Override
    protected void onStart() {
        super.onStart();
        datadapterlist.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        datadapterlist.stopListening();
    }
}
 /*   private void insertempdata()

    {
        String name = namee.getText().toString();
        String vehicletype = vtt.getText().toString();
        String department = deptt.getText().toString();
        String vehiclenumber = vnn.getText().toString();

        employee e= new employee(name, vehicletype, vehiclenumber, department);
        employeedbref.push().setValue(e);
        Toast.makeText(frForm.this,"Data inserted successfully!",Toast.LENGTH_SHORT).show();

    }*/

    /*ActivityResultLauncher<Intent> startForResult= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result!=null && result.getResultCode() == RESULT_OK)
                {
                    if(result.getData()!=null && getIntent()!=null && getIntent().getExtras()!=null&&getIntent().hasExtra(AddDetails.DEET) ){
                        ModelClass model= (ModelClass) getIntent().getSerializableExtra(AddDetails.DEET);

                        strdate =model.date;
                        strfrom = model.from;
                        strpurpose= model.purpose;
                        strkms =model.kms;
                        stradvance= model.advance;
                        strbalance = model.balance;
                    }
                }

            }
        });*/


        //employeedbref = getInstance().getReference().child("Employees");





/*
    private void insertempdata()

        {
            String name = namee.getText().toString();
            String vehicletype = vtt.getText().toString();
            String department = deptt.getText().toString();
            String vehiclenumber = vnn.getText().toString();

            employee e= new employee(name, vehicletype, vehiclenumber, department);
            employeedbref.push().setValue(e);
            Toast.makeText(frForm.this,"Data inserted successfully!",Toast.LENGTH_SHORT).show();

        }

        public void fillform(/*String strdate,String strfrom,String strto,String strpurpose,String strkms,String stradvance,String strbalance*///)

      /*  {
            datalist.clear();
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    datalist.add(snapshot.getValue(dataAdapter.class));
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            })
        ModelClass obj =new ModelClass(strdate,strfrom,strto,strpurpose,strkms,stradvance,strbalance);
       /* obj.setDate(strdate);
        obj.setFrom(strfrom);
        obj.setTo(strto);
        obj.setPurpose(strpurpose);
        obj.setKms(strkms);
        obj.setAdvance(stradvance);
        obj.setBalance(strbalance);*/
       // datalist.add(obj);
        //datadapterlist =new DataAdapter(this,datalist,this::onEditClick);
      //  recycle.setAdapter(datadapterlist);


       // }

  //  public void onEditClick(ModelClass listcurrentdata)
  /*   public class EditDetails extends AppCompatActivity{
        AlertDialog.Builder builderObj= new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.edit_fr,null);
      private EditText dateEdt, toEdt, fromEdt, purposeEdt, kmsEdt, advanceEdt, balanceEdt;
      private Button update;
        String id;
        ModelClass modelClass;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;

        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.edit_fr);




        firebaseDatabase = getInstance();


            dateEdt = findViewById(R.id.date1);
            fromEdt = findViewById(R.id.from1);
            toEdt = findViewById(R.id.to1);
            purposeEdt = findViewById(R.id.purpose1);
            kmsEdt = findViewById(R.id.kms1);
            advanceEdt = findViewById(R.id.advance1);
            balanceEdt = findViewById(R.id.balance1);
        update=view.findViewById(R.id.update);
        modelClass =getIntent().getParcelableExtra("Details");
        if(modelClass!=null){
            dateEdt.setText(modelClass.getDate());
            fromEdt.setText(modelClass.getFrom());
            toEdt.setText(modelClass.getTo());
            purposeEdt.setText(modelClass.getPurpose());
            kmsEdt.setText(modelClass.getKms());
            advanceEdt.setText(modelClass.getAdvance());
            balanceEdt.setText(modelClass.getBalance());
            id= modelClass.getId();

        }

            databaseReference= firebaseDatabase.getReference("Details").child(id);



       /* date1.setText(listcurrentdata.getDate());
        from1.setText(listcurrentdata.getFrom());
        to1.setText(listcurrentdata.getTo());
        purpose1.setText(listcurrentdata.getPurpose());
        kms1.setText(listcurrentdata.getKms());
        advance1.setText(listcurrentdata.getAdvance());
        balance1.setText(listcurrentdata.getBalance());

        */


     /*   ImageView close=view.findViewById(R.id.close);
        builderObj.setCancelable(false);
        builderObj.setView(view);

        close.setOnClickListener(v-> {
            alertDialog.cancel();
        });

        update.setOnClickListener(v->
            {
                String date = "", from = "",to = "", purpose = "", kms = "", advance = "", balance = "";
                if (dateEdt.getText() != null) {
                    date = dateEdt.getText().toString();
                }

                if (date.equals("")) {
                    Toast.makeText(this, "Please enter Date", Toast.LENGTH_LONG).show();
                    return;
                }

                if (fromEdt.getText() != null) {
                    from = fromEdt.getText().toString();
                }
                if (from.equals("")) {
                    Toast.makeText(this, "Please enter from location", Toast.LENGTH_LONG).show();
                    return;
                }
                if (toEdt.getText() != null) {
                    to = toEdt.getText().toString();
                }
                if (to.equals("")) {
                    Toast.makeText(this, "Please enter to location", Toast.LENGTH_LONG).show();
                    return;
                }
                if (purposeEdt.getText() != null) {
                    purpose = purposeEdt.getText().toString();
                }
                if (purpose.equals("")) {
                    Toast.makeText(this, "Please enter the purpose", Toast.LENGTH_LONG).show();
                    return;
                }
                if (kmsEdt.getText() != null) {
                    kms = kmsEdt.getText().toString();
                }
                if (kms.equals("")) {
                    Toast.makeText(this, "Please enter kms rate", Toast.LENGTH_LONG).show();
                    return;
                }
                if (advanceEdt.getText() != null) {
                    advance = advanceEdt.getText().toString();
                }
                if (advance.equals("")) {
                    Toast.makeText(this, "Please enter advance amount", Toast.LENGTH_LONG).show();
                    return;
                }
                if (balanceEdt.getText() != null) {
                    balance = balanceEdt.getText().toString();
                }
                if (balance.equals("")) {
                    Toast.makeText(this, "Please enter balance amount", Toast.LENGTH_LONG).show();
                    return;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("Date", date);
                map.put("From", from);
                map.put("To", to);
                map.put("Purpose", purpose);
                map.put("Kms", kms);
                map.put("Advance", advance);
                map.put("Balance", balance);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditDetails.this, "Details Updated!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditDetails.this, "Failed to update details" , Toast.LENGTH_SHORT).show();

                    }
                });




                //editDetails(strdate,strfrom,strto,strpurpose,strkms,stradvance,strbalance);

            });
        /*
        update.setOnClickListener(v->
        {
            String strdate = "", strfrom = "", strto = "", strpurpose = "", strkms = "", stradvance = "", strbalance = "";
            if (Edate1.getText() != null) {
                strdate = Edate1.getText().toString();
            }

            if (strdate.equals("")) {
                Toast.makeText(this, "Please enter Date", Toast.LENGTH_LONG).show();
                return;
            }

            if (Efrom1.getText() != null) {
                strfrom = Efrom1.getText().toString();
            }
            if (strfrom.equals("")) {
                Toast.makeText(this, "Please enter from location", Toast.LENGTH_LONG).show();
                return;
            }
            if (Eto1.getText() != null) {
                strto = Eto1.getText().toString();
            }
            if (strto.equals("")) {
                Toast.makeText(this, "Please enter to location", Toast.LENGTH_LONG).show();
                return;
            }
            if (Epurpose1.getText() != null) {
                strpurpose = Epurpose1.getText().toString();
            }
            if (strpurpose.equals("")) {
                Toast.makeText(this, "Please enter the purpose", Toast.LENGTH_LONG).show();
                return;
            }
            if (Ekms1.getText() != null) {
                strkms = Ekms1.getText().toString();
            }
            if (strkms.equals("")) {
                Toast.makeText(this, "Please enter kms rate", Toast.LENGTH_LONG).show();
                return;
            }
            if (Eadvance1.getText() != null) {
                stradvance = Eadvance1.getText().toString();
            }
            if (stradvance.equals("")) {
                Toast.makeText(this, "Please enter advance amount", Toast.LENGTH_LONG).show();
                return;
            }
            if (Ebalance1.getText() != null) {
                strbalance = Ebalance1.getText().toString();
            }
            if (strbalance.equals("")) {
                Toast.makeText(this, "Please enter balance amount", Toast.LENGTH_LONG).show();
                return;
            }


            editDetails(strdate,strfrom,strto,strpurpose,strkms,stradvance,strbalance);

        });*/

      /*  alertDialog=builderObj.create();
        alertDialog.show();

    }



    }
/*
    public void editDetails(String strdate, String strfrom, String strto, String strpurpose, String strkms, String stradvance, String strbalance){

        ModelClass obj =new ModelClass(strdate,strfrom,strto,strpurpose,strkms,stradvance,strbalance);
        obj.setDate(strdate);
        obj.setFrom(strfrom);
        obj.setTo(strto);
        obj.setPurpose(strpurpose);
        obj.setKms(strkms);
        obj.setAdvance(stradvance);
        obj.setBalance(strbalance);
        datalist.add(obj);
    }*/



















