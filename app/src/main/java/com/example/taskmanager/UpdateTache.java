package com.example.taskmanager;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateTache extends AppCompatActivity {

    Button btn;
    EditText nomT,tache,date;
    int id;
    DbSqlite db;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tache);

        btn = findViewById(R.id.btnUp);
        nomT= findViewById(R.id.nametask);
        tache= findViewById(R.id.task);
        date= findViewById(R.id.date);
        date.setFocusable(false);

        Intent reception = getIntent();
        id = reception.getIntExtra("id",0);
        String S_nomtache = reception.getStringExtra("nomT");
        String S_task = reception.getStringExtra("tache");
        String S_date = reception.getStringExtra("date");


        nomT.setText(S_nomtache);
        tache.setText(S_task);
        date.setText(S_date);


        c = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener sdate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, monthOfYear);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(UpdateTache.this, sdate, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        db = new DbSqlite(UpdateTache.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String S_nomT = nomT.getText().toString();
                String S_tache = tache.getText().toString();
                String S_date = date.getText().toString();

                if(S_date.length() != 0 && S_tache.length() != 0 && S_nomT.length() != 0) {

                Data data = new Data(id,S_nomT,S_tache,S_date);

                db.Update(data);
                Toast.makeText(UpdateTache.this, "done .. !", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UpdateTache.this,MainActivity.class);
                startActivity(intent);
                }
                else
                {
                    Toast.makeText(UpdateTache.this, "Complete the form", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.btn_delete)
        {
            ShowAlert(id);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ShowAlert(final int id) {
        AlertDialog.Builder builer = new AlertDialog.Builder(this)
                .setMessage("Are you sure !")
                .setTitle("Delete Task")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        db.Delete(id);
                        Toast.makeText(UpdateTache.this, "done .. !", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UpdateTache.this,MainActivity.class);
                        startActivity(intent);


                    }
                });
        AlertDialog alertDialog = builer.create();
        alertDialog.show();
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(c.getTime()));
    }
}
