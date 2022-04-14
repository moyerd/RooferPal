package com.dylanmoyer.rooferpal;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;
import java.util.Locale;

public class addNewHome extends AppCompatActivity {
    // Calling variables
    DbAdapter db;
    SimpleCursorAdapter adapter;
    EditText etname,etnumber,etemail,etaddress,etcity,etstate,etzip,etroofsize,etroofpitch,etcostest,etcomments;
    String name,number,email,address,city,state,zip,roofsize,roofpitch,costest,comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_home);
        // Calling DbAdapter
        db = new DbAdapter(this);
        db.open();
        // Get data from text field
        etname =(EditText)findViewById(R.id.name);
        etnumber =(EditText)findViewById(R.id.number);
        etemail =(EditText)findViewById(R.id.email);
        etaddress = (EditText)findViewById(R.id.address);
        etcity = (EditText)findViewById(R.id.city);
        etstate = (EditText)findViewById(R.id.state);
        etzip = (EditText)findViewById(R.id.zip);
        etroofsize = (EditText)findViewById(R.id.roofsize);
        etroofpitch = (EditText)findViewById(R.id.roofpitch);
        etcostest = (EditText)findViewById(R.id.costestimate);
        etcomments = (EditText)findViewById(R.id.comments);
    }
    public void Save(View v){
        /*if(etname.length() == 0 || etnumber.length() == 0 || etemail.length() == 0 || etaddress.length() == 0 || etcity.length() == 0 || etstate.length() == 0 || etzip.length() == 0 || etroofsize.length() == 0 || etroofpitch.length() == 0 || etcomments.length() == 0)
        {
            Toast.makeText(addNewHome.this, "All Fields Are Required!",Toast.LENGTH_LONG).show();
        }*/
        if(etname.length() == 0){
            etname.requestFocus();
            etname.setError("Required");
        }
        else if(!etname.getText().toString().matches("[a-zA-Z ]+"))
        {
            etname.requestFocus();
            etname.setError("Enter Only Alphabetical Characters");
        }
        else if(etnumber.length() == 0){
            etnumber.requestFocus();
            etnumber.setError("Required");
        }
        else if(etemail.length() == 0){
            etemail.requestFocus();
            etemail.setError("Required");
        }
        else if(!etemail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            etemail.requestFocus();
            etemail.setError("Invalid Email Address");
        }
        else if(etaddress.length() == 0){
            etaddress.requestFocus();
            etaddress.setError("Required");
        }
        else if(etcity.length() == 0){
            etcity.requestFocus();
            etcity.setError("Required");
        }
        else if(etstate.length() == 0){
            etstate.requestFocus();
            etstate.setError("Required");
        }
        else if(etzip.length() == 0){
            etzip.requestFocus();
            etzip.setError("Required");
        }
        else if(etroofsize.length() == 0){
            etroofsize.requestFocus();
            etroofsize.setError("Required");
        }
        else if(etroofpitch.length() == 0){
            etroofpitch.requestFocus();
            etroofpitch.setError("Required");
        }
        else if(etcostest.length() == 0){
            etcostest.requestFocus();
            etcostest.setError("Required");
        }
        else if(etcomments.length() == 0){
            etcomments.requestFocus();
            etcomments.setError("Required");
        }
        else if(db.isExist(number) == true){
            Toast.makeText(getApplicationContext(),"Phone Number Already Exist", Toast.LENGTH_SHORT).show();
        }else{
            name=etname.getText().toString();
            number=etnumber.getText().toString();
            email=etemail.getText().toString();
            address=etaddress.getText().toString();
            city=etcity.getText().toString();
            state=etstate.getText().toString();
            zip=etzip.getText().toString();
            roofsize=etroofsize.getText().toString();
            roofpitch=etroofpitch.getText().toString();
            costest=etcostest.getText().toString();
            comments=etcomments.getText().toString();
            db.insert(name,number,email,address,city,state,zip,roofsize,roofpitch,costest,comments);
            Toast.makeText(getApplicationContext(),"Home Added", Toast.LENGTH_SHORT).show();

            finish();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    public void calcHome(View v){
        EditText txtFirstNumEdit = (EditText) findViewById(R.id.length);
        EditText txtSecNumEdit = (EditText) findViewById(R.id.width);
        EditText txtThirdNumEdit = (EditText) findViewById(R.id.slope);
        EditText txtFourthNumEdit = (EditText) findViewById(R.id.priceperbundle);
        //TextView tvResult = (TextView) findViewById(R.id.tvSquareFt);
        //TextView tvBundles = (TextView) findViewById(R.id.tvBundles);
        //TextView tvPrice = (TextView) findViewById(R.id.tvPrice);

                /*if (txtFirstNumEdit.length() == 0 || txtSecNumEdit.length() == 0 || txtThirdNumEdit.length() == 0){
                    Toast.makeText(CalculateRoof.this, "All Fields Are Required!",Toast.LENGTH_LONG).show();
                }*/
        if(txtFirstNumEdit.length() == 0){
            txtFirstNumEdit.requestFocus();
            txtFirstNumEdit.setError("Required");
        }
        else if(txtSecNumEdit.length() == 0){
            txtSecNumEdit.requestFocus();
            txtSecNumEdit.setError("Required");
        }
        else if(txtThirdNumEdit.length() == 0){
            txtThirdNumEdit.requestFocus();
            txtThirdNumEdit.setError("Required");
        }
        else if(Integer.valueOf(txtThirdNumEdit.getText().toString()) > 24){
            txtThirdNumEdit.requestFocus();
            txtThirdNumEdit.setError("Must be Between 0-24");
        }
        else if(txtFourthNumEdit.length() == 0){
            txtFourthNumEdit.requestFocus();
            txtFourthNumEdit.setError("Required");
        }
        else{

            int length = Integer.parseInt(txtFirstNumEdit.getText().toString());
            int width = Integer.parseInt(txtSecNumEdit.getText().toString());
            double slope = Double.parseDouble(txtThirdNumEdit.getText().toString());
            double priceperbundle = Double.parseDouble(txtFourthNumEdit.getText().toString());

            etroofpitch.setText(String.format("%.0f", slope));


            if (slope == 0){
                slope = 1;
            }
            else if(slope == 1)
            {
                slope = 1.01;
            }
            else if(slope == 2)
            {
                slope = 1.02;
            }
            else if(slope == 3)
            {
                slope = 1.03;

            }
            else if(slope == 4)
            {
                slope = 1.06;

            }
            else if(slope == 5)
            {
                slope = 1.085;

            }
            else if(slope == 6)
            {
                slope = 1.12;

            }
            else if(slope == 7)
            {
                slope = 1.16;

            }
            else if(slope == 8)
            {
                slope = 1.21;

            }
            else if(slope == 9)
            {
                slope = 1.25;

            }
            else if(slope == 10)
            {
                slope = 1.31;

            }
            else if(slope == 11)
            {
                slope = 1.36;

            }
            else if(slope == 12)
            {
                slope = 1.42;

            }
            else if(slope == 13)
            {
                slope = 1.48;

            }
            else if(slope == 14)
            {
                slope = 1.54;

            }
            else if(slope == 15)
            {
                slope = 1.61;

            }
            else if(slope == 16)
            {
                slope = 1.67;

            }
            else if(slope == 17)
            {
                slope = 1.74;

            }
            else if(slope == 18)
            {
                slope = 1.803;

            }
            else if(slope == 19)
            {
                slope = 1.88;

            }
            else if(slope == 20)
            {
                slope = 1.95;

            }
            else if(slope == 21)
            {
                slope = 2.02;

            }
            else if(slope == 22)
            {
                slope = 2.09;

            }
            else if(slope == 23)
            {
                slope = 2.162;

            }
            else if(slope == 24)
            {
                slope = 2.24;

            }


            double result = (length * width) * slope;

            double bundles = (result / 100) * 3;

            Math.round(bundles);

            double totalbundleprice = priceperbundle * bundles;


            String COUNTRY = "US";
            String LANGUAGE = "en";
            String strPrice = NumberFormat.getCurrencyInstance(new Locale(LANGUAGE, COUNTRY)).format(totalbundleprice);


            etroofsize.setText(Math.round(result) + " sq. ft ");
            etcomments.setText(Math.round(bundles) + " Bundles of Shingles ");
            etcostest.setText(strPrice + " Total for Bundles ");

            Toast.makeText(addNewHome.this, "Home Calculated Results Below!",Toast.LENGTH_LONG).show();

        }


    }



    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
