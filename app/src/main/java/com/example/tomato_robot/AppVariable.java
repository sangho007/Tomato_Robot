package com.example.tomato_robot;

import android.app.Application;

import java.util.ArrayList;

public class AppVariable extends Application {
    String act_state = "waiting";

    public ArrayList<HarvestHistory.HarvestItem> harvestItems;

    public ArrayList<IntakeHistory.IntakeItem> intakeItems;

    @Override
    public void onCreate() {
        super.onCreate();
        harvestItems = new ArrayList<>();
        harvestItems.add(new HarvestHistory.HarvestItem("2023-06-07","350"));
        harvestItems.add(new HarvestHistory.HarvestItem("2023-06-08","403"));
        harvestItems.add(new HarvestHistory.HarvestItem("2023-06-10","456"));
        harvestItems.add(new HarvestHistory.HarvestItem("2023-06-11","420"));
        harvestItems.add(new HarvestHistory.HarvestItem("2023-06-13","280"));
        harvestItems.add(new HarvestHistory.HarvestItem("2023-06-15","410"));
        harvestItems.add(new HarvestHistory.HarvestItem("2023-06-19","440"));
        harvestItems.add(new HarvestHistory.HarvestItem("2023-06-20","420"));

        intakeItems = new ArrayList<>();
        intakeItems.add(new IntakeHistory.IntakeItem("2023-06-07","350"));
        intakeItems.add(new IntakeHistory.IntakeItem("2023-06-08","403"));
        intakeItems.add(new IntakeHistory.IntakeItem("2023-06-10","456"));
        intakeItems.add(new IntakeHistory.IntakeItem("2023-06-11","420"));
        intakeItems.add(new IntakeHistory.IntakeItem("2023-06-13","280"));
        intakeItems.add(new IntakeHistory.IntakeItem("2023-06-15","410"));
        intakeItems.add(new IntakeHistory.IntakeItem("2023-06-19","440"));
        intakeItems.add(new IntakeHistory.IntakeItem("2023-06-20","420"));
    }

    public ArrayList<HarvestHistory.HarvestItem> getHarvestItemList(){
        return this.harvestItems;
    }
    public ArrayList<IntakeHistory.IntakeItem>getIntakeItems(){return this.intakeItems;}
}
