package com.appsnipp.modernlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cropRecommendation extends AppCompatActivity {
    FirebaseDatabase rootnode;

    // creating a variable for our Database
    // Reference for Firebase.

    DatabaseReference fieldRef,nref,psref,pmref,maxtempref,humref,phref,rainfref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_recommendation);
    }
    public void predictCrop(View view){

        EditText fieldName=(EditText)findViewById(R.id.fieldName);
        EditText nitrogen=(EditText)findViewById(R.id.nitrogenPercent);
        EditText phosphurus=(EditText)findViewById(R.id.phosporusPercent);
        EditText potassium=(EditText)findViewById(R.id.potassiumPercent);
        EditText maxtemp=(EditText)findViewById(R.id.temperature);
        EditText humidity=(EditText)findViewById(R.id.humidity1);
        EditText phvalue=(EditText)findViewById(R.id.phvalue1);
        EditText rainfall=(EditText)findViewById(R.id.rainfall);




        String fieldname1= fieldName .getText().toString();
        String nitrogen1= nitrogen .getText().toString();
        String phosphurus1= phosphurus .getText().toString();
        String potassium1= potassium .getText().toString();
        String maxtemp1= maxtemp .getText().toString();
        String humidity1= humidity .getText().toString();
        String phvalue1= phvalue .getText().toString();
        String rainfall1= rainfall .getText().toString();


        rootnode= FirebaseDatabase.getInstance();
        fieldRef=rootnode.getReference("cropPrediction/fieldName");
        fieldRef.setValue(fieldname1);
        nref=rootnode.getReference("cropPrediction/nitrogen");
        nref.setValue(nitrogen1);
        psref=rootnode.getReference("cropPrediction/phosphurus");
        psref.setValue(phosphurus1);
        pmref=rootnode.getReference("cropPrediction/potassium");
        pmref.setValue(potassium1);
        maxtempref=rootnode.getReference("cropPrediction/maxtemp");
        maxtempref.setValue(maxtemp1);
        humref=rootnode.getReference("cropPrediction/humidity");
        humref.setValue(humidity1);
        phref=rootnode.getReference("cropPrediction/ph");
        phref.setValue(phvalue1);
        rainfref=rootnode.getReference("cropPrediction/rainfall");
        rainfref.setValue(rainfall1);
        float nitrogen_percent= Float.parseFloat(nitrogen1);
        float phosphurus_percent= Float.parseFloat(phosphurus1);
        float potassium_percent= Float.parseFloat(potassium1);
        float maxtemperature= Float.parseFloat(maxtemp1);
        float humidityValue= Float.parseFloat(humidity1);
        float phvaluee= Float.parseFloat(phvalue1);
        float rainvalue= Float.parseFloat(rainfall1);
        if(nitrogen_percent<5&&maxtemperature<5&&phosphurus_percent<5&&potassium_percent<5&&humidityValue<5&&phvaluee<5&&rainvalue<5){
            resultActivity1();
        }
        else if(nitrogen_percent>5&&nitrogen_percent<10&&maxtemperature>5&&maxtemperature<10&&phosphurus_percent>5&&phosphurus_percent<10&&potassium_percent>5&&potassium_percent<10&&humidityValue>5&&humidityValue<10&&phvaluee>5&&phvaluee<10&&rainvalue>5&&rainvalue<10){
            resultActivity2();
        }
        else if(nitrogen_percent>10&&nitrogen_percent<15&&maxtemperature>10&&maxtemperature<15&&phosphurus_percent>10&&phosphurus_percent<15&&potassium_percent>10&&potassium_percent<15&&humidityValue>10&&humidityValue<15&&phvaluee>10&&phvaluee<15&&rainvalue>10&&rainvalue<15){
            resultActivity3();
        }
        else if(nitrogen_percent>15&&nitrogen_percent<20&&maxtemperature>15&&maxtemperature<20&&phosphurus_percent>15&&phosphurus_percent<20&&potassium_percent>15&&potassium_percent<20&&humidityValue>15&&humidityValue<20&&phvaluee>15&&phvaluee<20&&rainvalue>15&&rainvalue<20){
            resultActivity4();
        }
        else
        {
            resultActivity1();
        }


    }

    public void resultActivity3() {
        Intent intent = new Intent(this, cropResult3.class);
        startActivity(intent);
    }

    public void resultActivity2() {
        Intent intent = new Intent(this, cropResult2.class);
        startActivity(intent);
    }
    public void resultActivity4() {
        Intent intent = new Intent(this, cropResult4.class);
        startActivity(intent);
    }

    public void resultActivity1() {
        Intent intent = new Intent(this, cropResult1.class);
        startActivity(intent);
    }



}

