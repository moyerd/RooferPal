package com.dylanmoyer.rooferpal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsHomes extends AppCompatActivity {
    DbAdapter db;
    String id,name,number,email,address,city,state,zip,roofsize,roofpitch,costest,comments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_homes);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        name = intent.getStringExtra("NAME");
        number = intent.getStringExtra("NUMBER");
        email = intent.getStringExtra("EMAIL");
        address = intent.getStringExtra("ADDRESS");
        city = intent.getStringExtra("CITY");
        state = intent.getStringExtra("STATE");
        zip = intent.getStringExtra("ZIP");
        roofsize = intent.getStringExtra("ROOFSIZE");
        roofpitch = intent.getStringExtra("ROOFPITCH");
        costest = intent.getStringExtra("COSTEST");
        comments = intent.getStringExtra("COMMENTS");
        ((TextView) findViewById(R.id.name)).setText(name);
        ((TextView) findViewById(R.id.number)).setText(number);
        ((TextView) findViewById(R.id.email)).setText(email);
        ((TextView) findViewById(R.id.address)).setText(address);
        ((TextView) findViewById(R.id.city)).setText(city);
        ((TextView) findViewById(R.id.state)).setText(state);
        ((TextView) findViewById(R.id.zip)).setText(zip);
        ((TextView) findViewById(R.id.roofsize)).setText(roofsize);
        ((TextView) findViewById(R.id.roofpitch)).setText(roofpitch);
        ((TextView) findViewById(R.id.costestimate)).setText(costest);
        ((TextView) findViewById(R.id.comments)).setText(comments);
        // Calling DbAdapter
        db = new DbAdapter(this);
        db.open();
    }
    public void Edit(View v){
        // Go to EditContact page
        Intent edit = new Intent(DetailsHomes.this, EditHome.class);
        edit.putExtra("ID", id);
        edit.putExtra("NAME", name);
        edit.putExtra("NUMBER", number);
        edit.putExtra("EMAIL", email);
        edit.putExtra("ADDRESS",address);
        edit.putExtra("CITY",city);
        edit.putExtra("STATE",state);
        edit.putExtra("ZIP",zip);
        edit.putExtra("ROOFSIZE",roofsize);
        edit.putExtra("ROOFPITCH",roofpitch);
        edit.putExtra("COSTEST",costest);
        edit.putExtra("COMMENTS",comments);

        startActivity(edit);
    }
    public void Delete(View v){
        db.delete(Integer.parseInt(id));
        Toast.makeText(getApplicationContext(),"Deleted", Toast.LENGTH_SHORT).show();

        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
