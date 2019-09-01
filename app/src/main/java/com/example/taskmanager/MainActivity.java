package com.example.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        DbSqlite db = new DbSqlite(this);
        ArrayList<Data> array = db.getAll();

        AdapterClass adapter = new AdapterClass(this,array);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Data data = (Data) adapterView.getItemAtPosition(position);

                int id = data.getId();
                String nomT = data.getNomtask();
                String tache = data.getTask();
                String date = data.getDate();

                Intent intent = new Intent(MainActivity.this,UpdateTache.class);

                intent.putExtra("id",id);
                intent.putExtra("nomT",nomT);
                intent.putExtra("tache",tache);
                intent.putExtra("date",date);

                startActivity(intent);


            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add)
        {
            Intent intent = new Intent(MainActivity.this,AddTache.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
