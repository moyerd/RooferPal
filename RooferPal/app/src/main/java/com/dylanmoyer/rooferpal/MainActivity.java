package com.dylanmoyer.rooferpal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Calling variables
    DbAdapter db;
    SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        // Initially insert some data
        // db.insert("Color Picker", "+840885654", "info@icyarena.com", "US");
        // db.insert("Mike Helen", "+808853437", "halen@icyarena.com", "UK");
        // db.insert("Robart Pink", "+808851234", "robart@icyarena.com", "AUS");
        // Display data
        ListView lv = (ListView) findViewById(R.id.listView1);
        int layoutstyle=R.layout.liststyle;
        int[] xml_id = new int[] {
                R.id.txtname,
                R.id.txtnumber
        };
        String[] column = new String[] {
                "name",
                "number"
        };
        Cursor row = db.fetchAllData();
        adapter = new SimpleCursorAdapter(this, layoutstyle,row,column, xml_id, 0);
        lv.setAdapter(adapter);
        // onClick function
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
                Cursor row = (Cursor) adapterview.getItemAtPosition(position);
                String _id = row.getString(row.getColumnIndexOrThrow("_id"));
                String name = row.getString(row.getColumnIndexOrThrow("name"));
                String number = row.getString(row.getColumnIndexOrThrow("number"));
                String email = row.getString(row.getColumnIndexOrThrow("email"));
                String address = row.getString(row.getColumnIndexOrThrow("address"));
                String city = row.getString(row.getColumnIndexOrThrow("city"));
                String state = row.getString(row.getColumnIndexOrThrow("state"));
                String zip = row.getString(row.getColumnIndexOrThrow("zip"));
                String roofsize = row.getString(row.getColumnIndexOrThrow("roofsize"));
                String roofpitch = row.getString(row.getColumnIndexOrThrow("roofpitch"));
                String costest = row.getString(row.getColumnIndexOrThrow("costest"));
                String comments = row.getString(row.getColumnIndexOrThrow("comments"));
                //go to detailsContact page
                Intent todetais = new Intent(MainActivity.this, DetailsHomes.class);
                todetais.putExtra("ID",_id);
                todetais.putExtra("NAME", name);
                todetais.putExtra("NUMBER",number);
                todetais.putExtra("EMAIL",email);
                todetais.putExtra("ADDRESS",address);
                todetais.putExtra("CITY",city);
                todetais.putExtra("STATE",state);
                todetais.putExtra("ZIP",zip);
                todetais.putExtra("ROOFSIZE",roofsize);
                todetais.putExtra("ROOFPITCH",roofpitch);
                todetais.putExtra("COSTEST",costest);
                todetais.putExtra("COMMENTS",comments);

                startActivity(todetais);
            }
        });
        // Display data by filter
        EditText et = (EditText) findViewById(R.id.myFilter);
        et.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });
        adapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return db.fetchdatabyfilter(constraint.toString(),"name");
            }
        });
    }
    public void addHome(View v){
        Intent addNewHome = new Intent(MainActivity.this, addNewHome.class);
        startActivity(addNewHome);


    }

}
