package com.gotb.myapplication.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.gotb.myapplication.R;
import com.gotb.myapplication.Calculations;

import java.text.DecimalFormat;

public class OutputDataActivity extends AppCompatActivity {

    TextView tvMileageCar, tvFuelConsumptionPer100Km, tvCostPer1Km, tvAverageFuelConsumptionPer100Km, tvAverageCostPer1Km;
    Calculations calculations;
    private String patternFormat = "#0.00", patternFormatKm = "#0.0";
    private String fuelConsumptionPer100Km, costPer1Km, averageFuelConsumptionPer100Km, averageCostPer1Km, mileageCar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_data);

        tvMileageCar = (TextView) findViewById(R.id.tvMileageCar);
        tvFuelConsumptionPer100Km = (TextView) findViewById(R.id.tvFuelConsumptionPer100Km);
        tvCostPer1Km = (TextView) findViewById(R.id.tvCostOf1);
        tvAverageFuelConsumptionPer100Km = (TextView) findViewById(R.id.tvAverageFuelConsumption);
        tvAverageCostPer1Km = (TextView) findViewById(R.id.tvAverageCostOf1);

        calculations = new Calculations();

        dataFormatting();
    }

    private void dataFormatting() {
        if (calculations.countOfRecordsInTheTable() > 1) {
            mileageCar = new DecimalFormat(patternFormatKm).format(calculations.lastMileage());
            fuelConsumptionPer100Km = new DecimalFormat(patternFormat).format(calculations.fuelConsumptionPer100Km());
            costPer1Km = new DecimalFormat(patternFormat).format(calculations.costPer1Km());
            averageFuelConsumptionPer100Km = new DecimalFormat(patternFormat).format(calculations.averageFuelConsumptionPer100Km());
            averageCostPer1Km = new DecimalFormat(patternFormat).format(calculations.averageCostPer1Km());
            setData();
        } else {
            Toast.makeText(OutputDataActivity.this, getResources().getString(R.string.toast_two_records_minimum), Toast.LENGTH_SHORT).show();
        }
    }

    private void setData() {
        tvMileageCar.setText(getResources().getString(R.string.tv_current_mileage_car)
                + "  " + mileageCar
                + getResources().getString(R.string.value_distance));
        tvFuelConsumptionPer100Km.setText(getResources().getString(R.string.tv_fuel_consumption_100)
                + "  " + fuelConsumptionPer100Km
                + getResources().getString(R.string.value_capacity));
        tvCostPer1Km.setText(getResources().getString(R.string.tv_cost_of_1)
                + "  " + costPer1Km
                + getResources().getString(R.string.value_currency));
        tvAverageFuelConsumptionPer100Km.setText(getResources().getString(R.string.tv_average_fuel_consumption)
                + "  " + averageFuelConsumptionPer100Km
                + getResources().getString(R.string.value_capacity));
        tvAverageCostPer1Km.setText(getResources().getString(R.string.tv_average_cost_of_1)
                + "  " + averageCostPer1Km
                + getResources().getString(R.string.value_currency));
    }

}
