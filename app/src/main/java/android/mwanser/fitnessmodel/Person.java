package android.mwanser.fitnessmodel;

import java.io.Serializable;

/**
 * Created by Wanser on 3/27/17.
 */

public class Person implements Serializable {


    private int age;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    private int restingHr;
    private int weight;
    private String height;
    private int gender;
    private int unit; //true is metric, false imperial
    private String email;
    private String password;
    private int vo2;
    private int id=0;

    //TODO: save to database

    public Person(){
        age=0;
        restingHr=0;
        weight=0;
        email= null;
        password= null;
        height="";
        unit =0;
        gender=0;
        vo2=0;


    }
    public Person(int age,
                  int rhr,
                  int weight,
                  String height,
                  int vo2,
                  int metric,
                  String email,
                  String password,
                  int gender ){
        this.age=age;
        this.restingHr=rhr;
        this.weight=weight;
        this.height=height;
        this.unit =metric;
        this.email=email;
        this.password=password;
        this.vo2=vo2;
        this.gender=gender;
    }



    public Person(int age, int rhr, int weight, String height,
                  int vo2, int metric, String email, String password ){
        this.age=age;
        this.restingHr=rhr;
        this.weight=weight;
        this.height=height;
        this.unit =metric;
        this.email=email;
        this.password=password;
        this.vo2=vo2;
    }
    public String write(){
        return email+","+password+","+String.valueOf(weight)+","+height+","
                +String.valueOf(age)+","+String.valueOf(gender)+","+String.valueOf(unit)+","+
                String.valueOf(vo2)+","+String.valueOf(restingHr)+","+
                String.valueOf(id)+"\n";
    }


    public String toString(){
        return new String("Age: "+String.valueOf(age)+"\nRHR: "+String.valueOf(restingHr)
                +"\nWeight: "+ String.valueOf(weight)+"\nHeight: "
                +String.valueOf(height)+ "\nEmail "+email+"\nPassword: "
                +password+"\nMetric: "+String.valueOf(unit)+"\nVO2: "+String.valueOf(vo2));
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
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

    public int getId(){return this.id;}

    public void setId(int id){this.id=id;}






}
