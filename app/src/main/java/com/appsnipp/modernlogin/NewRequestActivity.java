package com.appsnipp.modernlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewRequestActivity extends AppCompatActivity {

    @BindView(R.id.spinner)
    Spinner spinner;
    EditText editText;
    @BindView(R.id.timePicker) EditText tp;
    Calendar myCalendar;
    private int mHour, mMinute;
    public int a = 0;
    private String format = "";
    Button setTime;
    FirebaseDatabase rootnode;

    // creating a variable for our Database
    // Reference for Firebase.

    DatabaseReference addressRef,item1ref,item2ref,item3ref,item4ref,item5ref,dateref,timeref,amtref,hashRef;

    /*@BindView(R.id.timePicker) private TimePicker timePicker1;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request);
        ButterKnife.bind(this);

        /*Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }*/
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Crop Items");
        categories.add("Dairy Products");
        categories.add("Farm Utensils");
        categories.add("fertilizers");

        Collections.sort(categories);
        /*for (String country : countries) {
            System.out.println(country);
        }*/

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(NewRequestActivity.this,
                android.R.layout.simple_spinner_item, categories);

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the your spinner
        spinner.setAdapter(countryAdapter);

        myCalendar = Calendar.getInstance();

        editText= findViewById(R.id.datepicker);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH, i1);
                myCalendar.set(Calendar.DAY_OF_MONTH, i2);
                updateLabel();
            }

        };

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(NewRequestActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mHour = myCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = myCalendar.get(Calendar.MINUTE);
        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tp.setText(hourOfDay + ":" + minute);
            }
        };
        tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(NewRequestActivity.this,time,mHour,mMinute,false).show();
            }
        });

    }
    public void back(View view){
        startActivity(new Intent(this,BuyersActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }
    public void rangeClick(View view){
        a = a+1;
        if(a%2 == 1){
            view.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.themeColor)));
        }
        else{
            view.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.lightBlue)));
        }

    }
    /*public void setTime(View view) {
        int hour = timePicker1.getCurrentHour();
        int min = timePicker1.getCurrentMinute();
        showTime(hour, min);
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }*/
    public void mainActivity(View view){
        startActivity(new Intent(this,BuyersActivity.class));
        Toast.makeText(this, "New List added!", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void sendList(View view){
        EditText address=(EditText)findViewById(R.id.address);
//        EditText category=(EditText)findViewById(R.id.spinner);
        EditText item1=(EditText)findViewById(R.id.item1);
        EditText item2=(EditText)findViewById(R.id.item2);
        EditText item3=(EditText)findViewById(R.id.item3);
        EditText item4=(EditText)findViewById(R.id.item4);
        EditText item5=(EditText)findViewById(R.id.item5);
        EditText date=(EditText)findViewById(R.id.datepicker);
        EditText time=(EditText)findViewById(R.id.timePicker);
        EditText amt=(EditText)findViewById(R.id.amt);

        String date1= date .getText().toString();
        String address1= address .getText().toString();
        String item11= item1 .getText().toString();
        String item12= item2 .getText().toString();
        String item13= item3 .getText().toString();
        String item14= item4 .getText().toString();
        String item15= item5 .getText().toString();
        String time1= time .getText().toString();
        String amt1= amt .getText().toString();

        int hash=17263;
        String hash1=Integer.toString(hash);
        rootnode=FirebaseDatabase.getInstance();
        addressRef=rootnode.getReference("newRequest/address");
        addressRef.setValue(address1);
        item1ref=rootnode.getReference("newRequest/item1");
        item1ref.setValue(item11);
        item2ref=rootnode.getReference("newRequest/item2");
        item2ref.setValue(item12);
        item3ref=rootnode.getReference("newRequest/item3");
        item3ref.setValue(item13);
        item4ref=rootnode.getReference("newRequest/item4");
        item4ref.setValue(item14);
        item5ref=rootnode.getReference("newRequest/item5");
        item5ref.setValue(item15);
        timeref=rootnode.getReference("newRequest/time");
        timeref.setValue(time1);
        dateref=rootnode.getReference("newRequest/date");
        dateref.setValue(date1);
        amtref=rootnode.getReference("newRequest/amt");
        amtref.setValue(amt1);
        hashRef=rootnode.getReference("newRequest/hash");
        hashRef.setValue(hash1);
      hash++;
    }
}