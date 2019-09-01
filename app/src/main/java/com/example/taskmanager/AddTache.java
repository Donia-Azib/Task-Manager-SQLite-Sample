package com.example.taskmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTache extends AppCompatActivity {
    Button btn;
    EditText nomT,tache,date;
    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tache);


        btn = findViewById(R.id.btnadd);
        nomT= findViewById(R.id.nametask);
        tache= findViewById(R.id.task);
        date= findViewById(R.id.date);

        date.setFocusable(false);

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
                new DatePickerDialog(AddTache.this, sdate, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String S_nomT = nomT.getText().toString();
                String S_tache = tache.getText().toString();
                String S_date = date.getText().toString();

                if(S_date.length() != 0 && S_tache.length() != 0 && S_nomT.length() != 0) {
                    Data data = new Data(S_nomT, S_tache, S_date);

                    DbSqlite db = new DbSqlite(AddTache.this);
                    db.Ajout(data);
                    Toast.makeText(AddTache.this, "done .. !", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddTache.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(AddTache.this, "Complete the form", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date.setText(sdf.format(c.getTime()));
    }
}
