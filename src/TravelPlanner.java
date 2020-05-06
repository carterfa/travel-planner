/*
 Created by Farrah.
 */

import java.util.Scanner;
import java.text.DecimalFormat;

public class TravelPlanner {
    public static final Scanner input = new Scanner(System.in);
    public static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args){
        //greeting();
        budgeting();
        //timeDiff();
        //findarea();
        //findDistance();
    }

    public static void greeting(){
        System.out.print("Welcome to Vacation Planner!\nWhat is your name?");
        String name = input.nextLine().trim();
        System.out.print("Nice to meet you " + name + ", where are you travelling to?");
        String dest = input.nextLine().trim();
        System.out.println("Great! " + dest + " sounds like a great trip!");
        System.out.println("********** \n");
    }

    public static void budgeting(){
        System.out.print("How many days are you going to spend travelling?");
        int days = intConv(input.nextLine().trim());
        System.out.print("How much money, in USD, are you planning to spend on your trip?");
        double money = doubleConv(input.nextLine().trim());
        System.out.print("What is the three letter currency symbol for your travel destination?");
        String sym = input.nextLine().trim();
        System.out.print("How many " + sym+ " are there in 1 USD?");
        double rate = doubleConv(input.nextLine().trim());
        System.out.println();
        System.out.println(daysCalc(days));
        System.out.println(moneyCalc(days,money));
        System.out.println(convCalc(days,money,rate,sym));
        System.out.println("********** \n");
    }

    public static void findarea(){
        System.out.print("What is the square area of your destination country in kilometers squared?");
        double kmArea = doubleConv(input.nextLine().trim());
        if (kmArea < 0){
            System.out.println("Invalid area!");
            findarea();}else{
            double milesArea = kmArea * 0.386102;
            System.out.println("That is " + df.format(milesArea) + " miles squared.");
            System.out.println("********** \n");}
    }

    public static void findDistance() {
        System.out.print("What is the latitude coordinate of your home in decimal degrees?");
        double homeLat = doubleConv(input.nextLine().trim());
        System.out.print("What is the longitude coordinate of your home in decimal degrees?");
        double homeLong = doubleConv(input.nextLine().trim());
        System.out.print("What is the latitude coordinate of your destination in decimal degrees?");
        double destLat = doubleConv(input.nextLine().trim());
        System.out.print("What is the longitude coordinate of your destination in decimal degrees?");
        double destLong = doubleConv(input.nextLine().trim());

        System.out.println("Your destination is " + df.format(distanceCalc(homeLat, homeLong, destLat, destLong)) + " kilometers from home.");
        System.out.println("********** \n");
    }

    public static double distanceCalc(double homeLat, double homeLong, double destLat, double destLong){
        double lat1 = homeLat * Math.PI / 180;
        double lat2 = destLat * Math.PI / 180;
        double long1 = homeLong * Math.PI / 180;
        double long2 = destLong * Math.PI / 180;
        double diffLat2 = (lat2 - lat1)/2;
        double diffLong2 = (long2 - long1)/2;

        double a = Math.sin(diffLat2) * Math.sin(diffLat2);
        double b = Math.cos(long1) * Math.cos(long2) * Math.sin(diffLong2) * Math.sin(diffLong2);
        double c = 2 * 6378.1 * Math.asin(Math.sqrt(a + b));

        return c;
    }

    public static void timeDiff(){
        System.out.print("What is the time difference, in hours, between your home and your destination?");
        int hoursDiff = intConv(input.nextLine().trim());
        System.out.println("That means that when it is midnight at home it will be "+ timeCalc(hoursDiff,0)+ " in your travel destination and when it is noon it will be " + timeCalc(hoursDiff, 12) +".");
        System.out.println("********** \n");
    }

    public static String timeCalc(int hoursDiff, int timeHere){
        if (hoursDiff > 12 || hoursDiff < -12){
            System.out.println("That time difference is invalid!");
             timeDiff();
        }

        int newTime = timeHere + hoursDiff;

        if (newTime >= 24){
            newTime -= 24;
        }else if (newTime < 0){
            newTime += 24;
        }

        return newTime+":00";
    }

    public static String convCalc(int days, double money, double rate, String sym){

        if (money <= 0 || days <= 0 || rate <= 0){
            return "You need to input numbers greater than zero!";
        }else {
            money *= rate;
            double dailyBudget = money / days;
            return "If you are going to spend " + money + " " + sym + ", that means per day you can spend up to " + dailyBudget + " " + sym+ ".";

        }
    }

    public static String moneyCalc(int days, double money){

        if (money <= 0 || days <= 0){
            return "You need to input numbers greater than zero!";
        }else {
            double dailyBudget = money / days;
            return "If you are going to spend $" + money + " USD, that means per day you can spend up to $" + df.format(dailyBudget) + " USD.";
        }

    }

    public static String daysCalc(int days){

        if (days == -1){
            return "You need to input numbers greater than zero!";
        }else {

            int hours = days * 24;
            int mins = hours * 60;

            return ("If you are travelling for 14 days, that is the same as " + hours + " hours or " + mins + " minutes.");
        }
    }

    public static int intConv(String str){
        try {
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            return -999;
        }
    }

    public static double doubleConv(String str){
        try {
            return Double.parseDouble(str);
        }catch (NumberFormatException e){
            return -999.0;
        }
    }
}