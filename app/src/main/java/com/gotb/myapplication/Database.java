package com.gotb.myapplication;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "FuelConsumption", id = "_id")
public class Database extends Model {
    @Column(name = "mileage")
    public double mileage;

    @Column(name = "gasoline")
    public double gasoline;

    @Column(name = "price")
    public double price;

    @Column(name = "tank_is_empty")
    public boolean tankIsEmpty;


    public Database() {
        super();
    }

    public Database(double mileage, double gasoline, double price, boolean tankIsEmpty) {
        super();
        this.mileage = mileage;
        this.gasoline = gasoline;
        this.price = price;
        this.tankIsEmpty = tankIsEmpty;
    }

    public static List<Database> getTwoLastRecords() {
        return new Select()
                .from(Database.class)
                .orderBy("_id DESC")
                .limit(2)
                .execute();
    }

    public static List<Database> getAllRecords() {
        return new Select()
                .from(Database.class)
                .orderBy("_id DESC")
                .execute();
    }

    public static int getCountOfRecordsInTheTable() {
        return new Select().from(Database.class).count();
    }
}
