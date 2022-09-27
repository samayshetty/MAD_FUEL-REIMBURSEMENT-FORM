package com.example.frf;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class DataAdapter extends FirebaseRecyclerAdapter<ModelClass,DataAdapter.MyViewHolder>{



    public DataAdapter(@NonNull FirebaseRecyclerOptions<ModelClass> options) {
        super(options);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_details,parent,false);
        return  new MyViewHolder(view);

    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView")final int position, @NonNull ModelClass model) {

        holder.date.setText(model.getDate());
        holder.to.setText(model.getTo());
        holder.from.setText(model.getFrom());

        holder.purpose.setText(model.getPurpose());
        holder.kms.setText(model.getKms());
        holder.advance.setText(model.getAdvance());
        holder.balance.setText(model.getBalance());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.edit.getContext()).
                        setContentHolder(new ViewHolder(R.layout.edit_fr)).
                        setExpanded(true, 1750).create();

                View view = dialogPlus.getHolderView();
                EditText dateEdt, toEdt, fromEdt, purposeEdt, kmsEdt, advanceEdt, balanceEdt;


                dateEdt = view.findViewById(R.id.date1);
                fromEdt = view.findViewById(R.id.from1);
                toEdt = view.findViewById(R.id.to1);
                purposeEdt = view.findViewById(R.id.purpose1);
                kmsEdt = view.findViewById(R.id.kms1);
                advanceEdt = view.findViewById(R.id.advance1);
                balanceEdt = view.findViewById(R.id.balance1);

                Button update = view.findViewById(R.id.update);

                dateEdt.setText(model.getDate());
                fromEdt.setText(model.getFrom());
                toEdt.setText(model.getTo());
                purposeEdt.setText(model.getPurpose());
                kmsEdt.setText(model.getKms());
                advanceEdt.setText(model.getBalance());
                balanceEdt.setText(model.getAdvance());

                dialogPlus.show();


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Map<String, Object> map = new HashMap<>();
                        map.put("date",dateEdt.getText().toString());
                        map.put("from", fromEdt.getText().toString());
                        map.put("to", toEdt.getText().toString());
                        map.put("purpose", purposeEdt.getText().toString());
                        map.put("kms", kmsEdt.getText().toString());
                        map.put("advance", advanceEdt.getText().toString());
                        map.put("balance", balanceEdt.getText().toString());
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


                        FirebaseDatabase.getInstance().getReference().child("FR_DETAILS")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.purpose.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.purpose.getContext(), "Error while Deleting...", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });


                    }
                });

            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.date.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data cannot be retrieved.");

                builder.setPositiveButton("Delete anyway", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.purpose.getContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
                        FirebaseDatabase.getInstance().getReference().child("FR_DETAILS")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.purpose.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();


                    }
                });
                builder.show();
              }
        });


           // onEditListener.onEditClick(listdata.get(position));


    }
   /*   @Override
  public int getItemCount() {

        return listdata.size();
    }*/


    public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView date,from,to,purpose,kms,advance,balance;
            ImageView edit,delete;

            public MyViewHolder(@NonNull View itemView) {

                super(itemView);
                date=itemView.findViewById(R.id.dateid1);
                from=itemView.findViewById(R.id.fromid1);
                to=itemView.findViewById(R.id.toid1);
                purpose=itemView.findViewById(R.id.purposeid1);
                kms=itemView.findViewById(R.id.kmsid1);
                advance=itemView.findViewById(R.id.advanceid1);
                balance=itemView.findViewById(R.id.balanceid1);
                edit=itemView.findViewById((R.id.edit));
                delete= itemView.findViewById(R.id.delete);

            }
        }
}

/*public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder>{
    ArrayList<ModelClass> listdata;
    Context context;
    int lastPos=-1;
   private OnEditListener onEditListener;
   DatabaseReference databaseReference;
    private ClickInterface clickInterface;


    public DataAdapter(Context context, ArrayList<ModelClass> datalist, OnEditListener onEditListener) {
        this.listdata=datalist;
        this.context=context;
        this.onEditListener=onEditListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.form_details,parent,false);
        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelClass dataobj=listdata.get(position);

        holder.dateid1.setText(dataobj.getDate());
        holder.fromid1.setText(dataobj.getFrom());
        holder.toid1.setText(dataobj.getTo());
        holder.purposeid1.setText(dataobj.getPurpose());
        holder.kmsid1.setText(dataobj.getKms());
        holder.advanceid1.setText(dataobj.getAdvance());
        holder.balanceid1.setText(dataobj.getBalance());
        holder.delete.setOnClickListener(v->{
            listdata.remove(position);
            databaseReference.removeValue();
            notifyDataSetChanged();
        });
        holder.edit.setOnClickListener(v->{
            onEditListener.onEditClick(listdata.get(position));

        });
    }
    @Override
    public int getItemCount() {

        return listdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView dateid1,fromid1,toid1,purposeid1,kmsid1,advanceid1,balanceid1;
        ImageView edit,delete;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            dateid1=itemView.findViewById(R.id.dateid1);
            fromid1=itemView.findViewById(R.id.fromid1);
            toid1=itemView.findViewById(R.id.toid1);
            purposeid1=itemView.findViewById(R.id.purposeid1);
            kmsid1=itemView.findViewById(R.id.kmsid1);
            advanceid1=itemView.findViewById(R.id.advanceid1);
            balanceid1=itemView.findViewById(R.id.balanceid1);
            edit=itemView.findViewById((R.id.edit));
            delete= itemView.findViewById(R.id.delete);

        }
    }


    public interface ClickInterface{
        void onClick(int position);
    }
    public interface OnEditListener{
        void onEditClick(ModelClass listcurrentdata);



    }
}*/
