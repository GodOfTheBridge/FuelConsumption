package com.gotb.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.gotb.myapplication.Database;
import com.gotb.myapplication.R;

public class InputDataActivity extends AppCompatActivity {

    EditText etCurrentMileageCar, etNumberOfLiters, etRefillPrice;
    CheckBox cbTankIsEmpty;
    private double mileage = 0, gasoline = 0, price = 0;
    private boolean tankIsEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        ActiveAndroid.initialize(this);

        etCurrentMileageCar = (EditText) findViewById(R.id.etCurrentMileageCar);
        etNumberOfLiters = (EditText) findViewById(R.id.etNumberOfLiters);
        etRefillPrice = (EditText) findViewById(R.id.etRefillPrice);
        cbTankIsEmpty = (CheckBox) findViewById(R.id.cbTankIsEmpty);
    }

    public void onClickOk(View view) {
        saveToDatabase();
    }

    private void saveToDatabase() {
        if (checkFieldsIsEmpty()) {
            mileage = Double.parseDouble(etCurrentMileageCar.getText().toString());
            gasoline = Double.parseDouble(etNumberOfLiters.getText().toString());
            price = Double.parseDouble(etRefillPrice.getText().toString());
            tankIsEmpty = cbTankIsEmpty.isChecked();
            if (checkMileage() < mileage) {
                Database saveInDatabase = new Database(mileage, gasoline, price, tankIsEmpty);
                saveInDatabase.save();
                Toast.makeText(InputDataActivity.this, getResources().getString(R.string.toast_save), Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                Toast.makeText(InputDataActivity.this, getResources().getString(R.string.toast_mileage)
                        + Database.getTwoLastRecords().get(0).mileage + ")", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkFieldsIsEmpty() {
        if (etCurrentMileageCar.getText().toString().equals("")
                || etNumberOfLiters.getText().toString().equals("")
                || etRefillPrice.getText().toString().equals("")) {
            Toast.makeText(this, getResources().getString(R.string.toast_fields), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private double checkMileage() {
        double mileageFromTable = 0;
        if (Database.getCountOfRecordsInTheTable() > 0) {
            mileageFromTable = Database.getTwoLastRecords().get(0).mileage;
            return mileageFromTable;
        }
        return mileageFromTable;
    }

}
