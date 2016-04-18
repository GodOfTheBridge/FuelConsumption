package com.gotb.myapplication;


public class Calculations extends com.activeandroid.app.Application {

    private final int FIX_PATH_100_KM = 100;
    private double sumCostOf1Liter = 0;


    public double fuelConsumptionPer100Km(){
        if (Database.getTwoLastRecords().get(1).tankIsEmpty) {
            return specificFuelConsumption() * FIX_PATH_100_KM;
        }
        return 0;
    }

    public double costPer1Km(){
        if (Database.getTwoLastRecords().get(1).tankIsEmpty) {
            double costOf1Liter = Database.getTwoLastRecords().get(1).price
                    / Database.getTwoLastRecords().get(1).gasoline;
            return specificFuelConsumption() * costOf1Liter;
        }
        return 0;
    }

    public double specificFuelConsumption(){
        double last = Database.getTwoLastRecords().get(0).mileage;
        double preLast = Database.getTwoLastRecords().get(1).mileage;
        double gas = Database.getTwoLastRecords().get(1).gasoline;
        return gas / (last - preLast);
    }

    public double averageFuelConsumptionPer100Km(){
        return averageSpecificFuelConsumption() * FIX_PATH_100_KM;
    }

    public double averageCostPer1Km(){
        double averageSpecificFuelConsumption = averageSpecificFuelConsumption();
        return averageSpecificFuelConsumption * sumCostOf1Liter;
    }

    public double averageSpecificFuelConsumption(){
        double sumCost = 0, sumGasoline = 0, allMileage, minusMileage = 0, mileage;

        allMileage = Database.getAllRecords().get(0).mileage
                - Database.getAllRecords().get(Database.getCountOfRecordsInTheTable()-1).mileage;

        for(int i=0; i<Database.getAllRecords().size(); i++) {

            if (!Database.getAllRecords().get(i).tankIsEmpty
                    && Database.getCountOfRecordsInTheTable()> Database.getAllRecords().get(i).getId()){
                minusMileage = Database.getAllRecords().get(i - 1).mileage
                        - Database.getAllRecords().get(i).mileage;
            }

            if (Database.getAllRecords().get(i).tankIsEmpty
                    && Database.getCountOfRecordsInTheTable()> Database.getAllRecords().get(i).getId()) {
                sumCost = sumCost + Database.getAllRecords().get(i).price;
                sumGasoline = sumGasoline + Database.getAllRecords().get(i).gasoline;
            }
        }
        sumCostOf1Liter = sumCost / sumGasoline;
        mileage = allMileage - minusMileage;
        return sumGasoline / mileage;
    }


    public double lastMileage(){
        return Database.getTwoLastRecords().get(0).mileage;
    }

    public int countOfRecordsInTheTable(){
        return Database.getCountOfRecordsInTheTable();
    }



}
