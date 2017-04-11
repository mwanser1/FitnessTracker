package android.mwanser.fitnessmodel;

import java.io.Serializable;

/**
 * Created by Wanser on 3/27/17.
 */

public class Person implements Serializable {


    private int age;
    private int restingHr;
    private int weight;
    private int height;
    private boolean metricUnit; //true is metric, false imperial
    private String email;
    private String password;
    private int vo2;

    //TODO: save to database

    public Person(){
        age=0;
        restingHr=0;
        weight=0;
        email= null;
        password= null;
        height=0;
        metricUnit=false;
        vo2=0;


    }



    public Person(int age, int rhr, int weight, int height,
                  int vo2, boolean metric, String email, String password ){
        this.age=age;
        this.restingHr=rhr;
        this.weight=weight;
        this.height=height;
        this.metricUnit=metric;
        this.email=email;
        this.password=password;
        this.vo2=vo2;
    }


    public String toString(){
        return new String("Age: "+String.valueOf(age)+"\nRHR: "+String.valueOf(restingHr)
                +"\nWeight: "+ String.valueOf(weight)+"\nHeight: "
                +String.valueOf(height)+ "\nEmail "+email+"\nPassword: "
                +password+"\nMetric: "+String.valueOf(metricUnit)+"\nVO2: "+String.valueOf(vo2));
    }


    public int getVo2() {
        return vo2;
    }

    public void setVo2(int vo2) {
        this.vo2 = vo2;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRestingHr() {
        return restingHr;
    }

    public void setRestingHr(int restingHr) {
        this.restingHr = restingHr;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isMetricUnit() {
        return metricUnit;
    }

    public void setMetricUnit(boolean metricUnit) {
        this.metricUnit = metricUnit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }






}
