package android.mwanser.fitnesstracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.mwanser.fitnessmodel.Exercise;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Wanser on 4/11/17.
 */

public class ExerciseDataBaseHelper extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private static final String TAG ="ExerciseDatabaseHelper";
    private static String DB_PATH = "/data/data/android.mwanser.fitnesstracker/databases/";

    private static String DB_NAME = "Exercises.db";
    private SQLiteDatabase myDataBase=null;

    private final Context myContext;
    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ExerciseDataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;

    }
    public void createDataBase(){
        try {

            String destPath = "/data/data/" + myContext.getPackageName()
                    + "/databases/Exercises.db";

            File f = new File(destPath);
            if(!f.exists()){
                Log.v(TAG,"File Not Exist");

                InputStream in = myContext.getAssets().open("Exercises.db");
                OutputStream out = new FileOutputStream(destPath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.close();
            }

        } catch (FileNotFoundException e) {
            Log.e(TAG,"FILENOTFOUND ");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("TAG","ioexeption");
            e.printStackTrace();
        }


    }
    public void openDataBase(){
        try{
            String myPath = DB_PATH + DB_NAME;
           myDataBase=SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e){
            Log.e(TAG,"error opening database");
            e.printStackTrace();
        }
    }
    public ArrayList<Exercise> returnArrayListExercise(String sql){
        Cursor cursor=null;
        ArrayList<Exercise> temp=new ArrayList<>();
        String name="";
        String musclesUsed="";
        String additionalMuscles="";
        int _id=0;
        String category="";
        String description="";
        ArrayList<Integer> added=new ArrayList<>();
        boolean alreadyIn=false;

        Log.d(TAG,myDataBase.toString());
        if(myDataBase!=null){
            try{
                cursor = myDataBase.rawQuery(sql, null);
            } catch (Exception e){
                Log.e(TAG, "Error doing the array list");
                e.printStackTrace();
            }
            if(cursor!=null){
                cursor.moveToFirst();

                do{
                    try {
                        //TODO DOESNT WORK
                        alreadyIn=false;
                        Log.e(TAG,String.valueOf(temp.size()));
                        Log.e(TAG,String.valueOf(cursor.getPosition()));
//                        for (String s:cursor.getColumnNames())Log.e(TAG,s);
                        name = cursor.getString(0);
                        musclesUsed = cursor.getString(4);
                        additionalMuscles= cursor.getString(5);
                        _id = cursor.getInt(1);
                        category = cursor.getString(2);
                        description = cursor.getString(3);
                    }catch (Exception e){
                        Log.e(TAG,"Error getting column names");
                        e.printStackTrace();
                    }

                    if(temp.size()==0) {
                        Log.e(TAG,"Adding first item");
                        ArrayList<String> temp2= new ArrayList<>();
                        temp2.add(additionalMuscles);
                        temp.add(new Exercise(name, musclesUsed, temp2,_id,
                            category,description));
                        added.add(_id);
                    }
                    else {
//                        for(Exercise i:temp){
//                            if (i.get_id() == _id){
//                                ArrayList<String> temp2= new ArrayList<>();
//                                temp2=i.getAdditionalMuscles();
//                                temp2.add(additionalMuscles);
//                                i.setAdditionalMuscles(temp2);
//                                alreadyIn=true;
//                                Log.e(TAG,"addeded additional muscle group");
//                                break;
//                            }
//                        }
                        //TODO PROBLEM with how muslce groups are stored
                        //TODO only getting 101 exercises should have 260
                        if(!alreadyIn){
                            Log.e(TAG,"adding new execise");
                            ArrayList<String> temp2= new ArrayList<>();
                            temp2.add(additionalMuscles);
                            temp.add(new Exercise(name, musclesUsed, temp2,_id,
                                    category,description));

                            added.add(_id);
                        }


                    }

                }while(cursor.moveToNext());}

        }
        else return null;
        for(Exercise item : temp)
            Log.d(TAG,item.toString());
        if (cursor!=null) cursor.close();
        return temp;

    }
    public void closeAll(){
        myDataBase.close();
    }

    public ArrayList<Exercise> populateArray(){
        ArrayList<Exercise> temp= new ArrayList<>();
        String sql1="SELECT * FROM exercises";
        Cursor cursor=null;
        String name="";
        String musclesUsed="";
        String additionalMuscles="";
        int _id=0;
        String category="";
        String description="";

        if(myDataBase!=null){
            cursor=performSQL(sql1);
        }
        else return null;

        Log.e("TAG","Size of results from Select all exercises "+String.valueOf(cursor.getCount()));

        if(cursor==null) return null;
        else{
            cursor.moveToFirst();
            do{
                try {
                    _id = cursor.getInt(0);
                    name = cursor.getString(1);
                    description = cursor.getString(2);
                    category = cursor.getString(3);
                } catch (Exception e){ Log.e(TAG,"ERROR handling column name resolution");
                    e.printStackTrace();
                }

                temp.add(new Exercise(name,_id,category, description));

            }while(cursor.moveToNext());
        }


        return temp;
    }
    private Cursor performSQL(String sql){
        Cursor cursor=null;
        try{
            cursor = myDataBase.rawQuery(sql, null);
        } catch (Exception e){
            Log.e(TAG, "Error doing the array list");
            e.printStackTrace();
        }
        return cursor;

    }

}
