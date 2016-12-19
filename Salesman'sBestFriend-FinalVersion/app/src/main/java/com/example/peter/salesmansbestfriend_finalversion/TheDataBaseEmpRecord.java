package com.example.peter.salesmansbestfriend_finalversion;

/**
 * Created by Peter on 12/11/2015.
 */
public class TheDataBaseEmpRecord {
    private String name;
    private String id;
    private String yearlySalary;
    private String monthlySalary;
    private String quota;
    private String sales;

    public TheDataBaseEmpRecord(String n, String i, String yS, String mS, String q, String s) {
        name = n;
        id = i;
        yearlySalary = yS;
        monthlySalary = mS;
        quota = q;
        sales = s;
    }

    public String getName() {return name;}
    public String getId() {return id; }
    public String getYearlySalary() {return yearlySalary;}
    public String getMonthlySalary() {return monthlySalary;}
    public String getQuota() {return quota;}
    public String getSales() {return sales;}
}