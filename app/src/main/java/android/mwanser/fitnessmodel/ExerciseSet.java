package android.mwanser.fitnessmodel;

import java.io.Serializable;

/**
 *
 * Daa class for holding exercise + reps+weight+rest infromation
 * used in perform workout
 *
 *
 * Created by Wanser on 4/10/17.
 */

public class ExerciseSet implements Serializable {


    private String name;
    private String description;
    private int weight;
    private int reps;
    private int restSeconds;
    private String cadence;

    public ExerciseSet(){
        name="test";
        description="test";
        weight=1;
        reps=1;
        restSeconds=1;
        cadence="test";

    }

    public ExerciseSet(String name, String description, int weight, int reps, int restSeconds,
                       String cadence){
        this.name=name;
        this.description=description;
        this.weight=weight;
        this.reps=reps;
        this.restSeconds=restSeconds;
        this.cadence=cadence;
    }
    public String toString(){
        return "name:"+getName()+"\tDesc:"+getDescription()+"\tweight"+
                String.valueOf(getWeight())+"\treps:"+String.valueOf(getReps())+
                "\tRest:"+String.valueOf(getRestSeconds())+"\tCad:"+getCadence();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getRestSeconds() {
        return restSeconds;
    }

    public void setRestSeconds(int restSeconds) {
        this.restSeconds = restSeconds;
    }

    public String getCadence() {
        return cadence;
    }

    public void setCadence(String cadence) {
        this.cadence = cadence;
    }


}
