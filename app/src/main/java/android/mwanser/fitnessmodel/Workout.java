package android.mwanser.fitnessmodel;

import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * Will be a list of ExerciseSet objects that is object written to SD card
 * for persistent storage
 *
 * Created by Wanser on 4/10/17.
 */

public class Workout implements Serializable {
    private ArrayList <ExerciseSet> exercises=null;
    private Integer maxHr;
    private Integer userId;
    private Integer avgHr;
    private Integer time;
    private Integer calories;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;
    //private
    private Integer type; // tells if it is fitness test, gym workout or run
    //type =0 is workout
    //type 1= run
    //type2 = fitness test

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }



    public int getSizeWorkout(){
        if (exercises !=null) return exercises.size();
        else return -1;
    }
    public void addExercise( ExerciseSet e){
        this.exercises.add(e);
    }

    public void setExercises (ArrayList<ExerciseSet> e){
        for(ExerciseSet item : e)
            this.exercises.add(item);
    }

    public ArrayList<ExerciseSet> getExercises() {
        return exercises;
    }

    public Integer getMaxHr() {
        return maxHr;
    }

    public void setMaxHr(Integer maxHr) {
        this.maxHr = maxHr;
    }

    public Integer getAvgHr() {
        return avgHr;
    }

    public void setAvgHr(Integer avgHr) {
        this.avgHr = avgHr;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }



    public Workout(){
       exercises = new ArrayList<>();


    };

    public Workout(ArrayList<ExerciseSet> e, Integer maxHr, Integer avgHr, Integer time, Integer calories, Integer type, Integer user){
        exercises= new ArrayList<>();
        setExercises(e);
        this.maxHr=maxHr;
        this.avgHr=avgHr;
        this.time=time;
        this.calories=calories;
        this.type=type;
        this.userId=user;
        date= new Date();

    }
    public int getLengthWorkout(){
        return exercises.size();
    }

}
