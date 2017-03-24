package android.mwanser.fitnesstracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Wanser on 3/23/17.
 */

public class ViewWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_workout);
        Log.d("**ViewWorkout","onCreate");
    }
//    @Override
//    protected void (Bundle savedInstanceState){
//        Log.d("*-ViewWorkout","onDestroy");
//        super.onDestroy();
//    }
}
