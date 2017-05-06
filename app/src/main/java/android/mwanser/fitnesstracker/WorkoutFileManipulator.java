package android.mwanser.fitnesstracker;

import android.mwanser.fitnessmodel.ExerciseSet;
import android.mwanser.fitnessmodel.Person;
import android.mwanser.fitnessmodel.Workout;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Class for opening and writing the files we need for workout storage
 * Created by Wanser on 5/5/17.
 */
//TODO this and Login file should extend a superclass
public class WorkoutFileManipulator {
    private boolean failedWrite=false;
    private boolean failedRead=false;
    private boolean failedOpen=false;
    private boolean fileNew=false;
    private boolean readOnly=false;

    private File myFile=null;
    private FileOutputStream fos=null;
    private OutputStreamWriter outWriter=null;
    private FileInputStream fis=null;
    private BufferedReader reader=null;
    private ArrayList<String[]> theUsers= null;
    private int currentWO;
    private int userID;

    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    //private Workout workout;
    private ArrayList<Workout> workouts=null;
    private static final String TAG ="WorkoutFileManipulator";

    public WorkoutFileManipulator(){
        Log.d(TAG,"defualt constructor");


    }
    public WorkoutFileManipulator(String fileName, Workout w, int currentWO, int userID){
        boolean success = true;
        this.currentWO=currentWO;
        workouts= new ArrayList<>();

        if(w==null){
            Log.d(TAG,"we are just reading");
            readOnly=true;

        }
        else{

            workouts.add(w);
        }
        this.userID=userID;
        //-------------------------------------------- open file
        try {
            myFile = new File(Environment.getExternalStorageDirectory(), fileName);
        }catch(Exception e){
            Log.e(TAG,"Error opening file");
            e.printStackTrace();
            failedOpen=true;
        }
        if(!myFile.exists())
            try{ myFile.createNewFile(); fileNew=true; } //what does this fucking return
            catch (IOException e){
                e.printStackTrace();
                failedOpen=true;
            }

        readWorkouts();

    }

    //TODO four function
    //-read new file ( should return indicator)
    private void readWorkouts(){
        Log.e(TAG,String.valueOf(workouts.size()));
        ArrayList<Workout> w = new ArrayList<>();
        ObjectInputStream input= null;
        FileInputStream fin =null;
        try {
            fin = new FileInputStream(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fileNew) return;
        else{
            //if(workouts.size()!=0)return;
            try {
                input = new ObjectInputStream(fin);
                w = (ArrayList<Workout>) input.readObject();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            for(Workout i: w)
                workouts.add(i);
        }
        Log.e(TAG,String.valueOf(workouts.size()));

    }

    public void writeWorkouts(){
        ObjectOutputStream output=null;

        FileOutputStream fout= null;
        try {
            fout = new FileOutputStream(myFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            output = new ObjectOutputStream(fout);
            output.writeObject(workouts);
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fout.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
    public String getfirst(){
        return workouts.get(0).getExercises().get(0).getName();
    }

    /**
     * returns the workout at position
     * @param i - position
     * @return - the workout
     */
    public Workout getAtPosition(int i){
        return workouts.get(i);
    }
    public void destroy(){
        Log.e(TAG,"destroy me");
        if (workouts!= null)writeWorkouts();


    }

    //write ( new file)

    //write ( file exists)

    //function close files





}
