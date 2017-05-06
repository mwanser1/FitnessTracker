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
 *
 * Class that opens the Exercise DB from assets folder and stores locally
 *
 * No data manipulation allowed, read only
 *
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
       // DB_PATH=context.getFilesDir().getPath()+"/databases/";
        this.myContext = context;

    }
    public void createDataBase(){
        try {

            String destPath = myContext.getFilesDir().getPath()+"/Exercises.db";

            File f = new File(destPath);
            if(!f.exists()){
                Log.v(TAG,"File Not Exist");

                InputStream in = myContext.getAssets().open("Exercises.db");
                Log.e("_____",String.valueOf(in.available()));
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
           myDataBase=SQLiteDatabase.openDatabase(myContext.getFilesDir().getPath()+"/Exercises.db", null, SQLiteDatabase.OPEN_READONLY);
            Log.e("----","opened");
        } catch (Exception e){
            Log.e(TAG,"error opening database");
            e.printStackTrace();
        }
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
