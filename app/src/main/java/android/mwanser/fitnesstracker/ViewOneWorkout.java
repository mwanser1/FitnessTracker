package android.mwanser.fitnesstracker;

import android.mwanser.fitnessmodel.ExerciseSet;
import android.mwanser.fitnessmodel.Workout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by Wanser on 5/6/17.
 */

public class ViewOneWorkout extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.perform_gps_run);
        setContentView(R.layout.view_one_workout);
        Log.d("**PerformGPSRun","onCreate");
        Bundle extras = getIntent().getExtras();
        String num= extras.getString(EXTRA_MESSAGE);
        int i = Integer.valueOf(num);
        //todo filter based on user id

        WorkoutFileManipulator fileManipulator= new WorkoutFileManipulator("workout",null, 0,0);
        Workout w = fileManipulator.getAtPosition(i);


        ListView mListView = (ListView) findViewById(R.id.view_one_list);
        ExerciseSetAdapter adapter = new ExerciseSetAdapter(this,w.getExercises());
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();





    }
}
