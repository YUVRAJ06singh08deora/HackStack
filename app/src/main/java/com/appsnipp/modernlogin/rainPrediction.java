package com.appsnipp.modernlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import butterknife.BindView;

public class rainPrediction extends AppCompatActivity {

    FirebaseDatabase rootnode;

    // creating a variable for our Database
    // Reference for Firebase.

    DatabaseReference fieldRef,precipref,maxtemref,mintempref,sowref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain_prediction);

    }
    public void rainPrediction1(View view){

        EditText fieldName=(EditText)findViewById(R.id.fieldName);
        EditText precipV=(EditText)findViewById(R.id.precepitatonValue);
        EditText maxtemp=(EditText)findViewById(R.id.maxtmp);
        EditText mintemp=(EditText)findViewById(R.id.mintmp);
        EditText spedofwind=(EditText)findViewById(R.id.wind);




        String fieldname1= fieldName .getText().toString();
        String precipv1= precipV .getText().toString();
        String maxtemp1= maxtemp .getText().toString();
        String mintemp1= mintemp .getText().toString();
        String spedofw1= spedofwind .getText().toString();


        rootnode= FirebaseDatabase.getInstance();
        fieldRef=rootnode.getReference("rainPrediction/FieldName");
        fieldRef.setValue(fieldname1);
        precipref=rootnode.getReference("rainPrediction/PrecipitationValue");
        precipref.setValue(precipv1);
        maxtemref=rootnode.getReference("rainPrediction/maxTemp");
        maxtemref.setValue(maxtemp1);
        mintempref=rootnode.getReference("rainPrediction/minTemp");
        mintempref.setValue(mintemp1);
        sowref=rootnode.getReference("rainPrediction/spedofw1");
        sowref.setValue(spedofw1);
        float precipititation= Float.parseFloat(precipv1);
        float maxtemperature= Float.parseFloat(maxtemp1);
        float mintemperature= Float.parseFloat(mintemp1);
        float speedofwind= Float.parseFloat(spedofw1);
        if(precipititation<5&&maxtemperature<5&&mintemperature<5&&speedofwind<5){
            resultActivity1();
        }
        else if(precipititation>5&&precipititation<10&&maxtemperature>5&&maxtemperature<10&&mintemperature>5&&mintemperature<10&&speedofwind>5&&speedofwind<10){
            resultActivity2();
        }
        else if(precipititation>10&&precipititation<15&&maxtemperature>10&&maxtemperature<15&&mintemperature>10&&mintemperature<15&&speedofwind>10&&speedofwind<15){
            resultActivity3();
        }
        else if(precipititation>15&&precipititation<20&&maxtemperature>15&&maxtemperature<20&&mintemperature>15&&mintemperature<20&&speedofwind>15&&speedofwind<20){
            resultActivity4();
        }
        else
        {
            resultActivity1();
        }


    }

    public void resultActivity3() {
        Intent intent = new Intent(this, rainfallResult3.class);
        startActivity(intent);
    }

    public void resultActivity2() {
        Intent intent = new Intent(this, rainfallResult2.class);
        startActivity(intent);
    }
    public void resultActivity4() {
        Intent intent = new Intent(this, rainfallResult4.class);
        startActivity(intent);
    }

    public void resultActivity1() {
        Intent intent = new Intent(this, rainfallResult1.class);
        startActivity(intent);
    }


}