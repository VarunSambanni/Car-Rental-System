package com.example.javafxproj;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Car {
    public String id, model, brand, available, name, phoneNo, address, startTime ;
    public int cost ;
    public Car(String id, String model, String brand, String available, String name, String phoneNo, String address, int cost, String startTime){
        this.id = id ;
        this.model = model ;
        this.brand = brand ;
        this.available = available;
        this.name = name ;
        this.phoneNo = phoneNo ;
        this.address = address ;
        this.cost = cost ;
        this.startTime = startTime ;
    }

    public static long[] calcDiffTimes(String startDate, String stopDate){
        long timeDiff[] = new long[3] ;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startDate);
            d2 = format.parse(stopDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = d2.getTime() - d1.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000);
        System.out.println("Time in seconds: " + diffSeconds + " seconds.");
        System.out.println("Time in minutes: " + diffMinutes + " minutes.");
        System.out.println("Time in hours: " + diffHours + " hours.");
        timeDiff[0] = diffHours;
        timeDiff[1] = diffMinutes;
        timeDiff[2] = diffSeconds;
        return timeDiff;
    }

    public String getId(){
        return id;
    }

    public String getModel(){
        return model;
    }

    public String getBrand(){
        return brand;
    }

    public String getAvailable(){
        return available;
    }

    public String getName(){
        return name;
    }

    public String getPhoneNo(){
        return phoneNo;
    }

    public String getAddress(){
        return address;
    }

    public int getCost(){
        return cost;
    }

    public String getStartTime(){
        return startTime;
    }

}
