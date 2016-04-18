package com.gotb.myapplication.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gotb.myapplication.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPressButtonInputData(View view){
        Intent intentInputData = new Intent(this, InputDataActivity.class);
        startActivity(intentInputData);
    }

    public void onPressButtonOutputData(View view){
        Intent intentOutputData = new Intent(this, OutputDataActivity.class);
        startActivity(intentOutputData);
    }
}
