package android.mwanser.fitnesstracker;

import android.content.Intent;
import android.mwanser.fitnessmodel.ExerciseSet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Wanser on 3/23/17.
 */

public class PerformWorkout extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private Button mCancelButton;
    private Button mSaveButton;
    private Button mAddButton;
    private String message;
    private ListView mListViewSets;
    private ArrayList<ExerciseSet> theSets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.perform_workout);
        Log.d("**PerformWorkout","onCreate");
        Intent intent = getIntent();
        message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        theSets= new ArrayList<>();
        mCancelButton = (Button) findViewById(R.id.perform_workout_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GridActivity.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
        mSaveButton = (Button) findViewById(R.id.perform_workout_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: save to persistent data
                //TODO: get the ID of the workout so that i can view it and add to message
                message=message+","+1;
                Intent intent = new Intent(getApplicationContext(), ViewWorkout.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
        mAddButton = (Button) findViewById(R.id.perform_workout_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDO- add dialogue window to add exercise
            }
        });
        ExerciseSetAdapter adapter= new ExerciseSetAdapter(this,theSets);
        mListViewSets = (ListView) findViewById(R.id.perform_workout_list_view);
        mListViewSets.setAdapter(adapter);

        ExerciseSet temp =new ExerciseSet("Chest",  "description", 100, 15, 60,
                "FAst");
        adapter.add(temp);
        adapter.add(temp);
        adapter.add(temp);
        adapter.add(temp);
        adapter.add(temp);adapter.add(temp);adapter.add(temp);
        adapter.add(temp);
        adapter.add(temp);

//        for (ExerciseSet item : theSets){
//            Log.d("PerformWorkout",item.toString());
//        }



    }
}
