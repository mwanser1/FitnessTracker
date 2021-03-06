package android.mwanser.fitnesstracker;


import android.content.Intent;
import android.mwanser.PerformanceWorkoutCallback;
import android.mwanser.PreferenceUtils;
import android.mwanser.fitnessmodel.Exercise;
import android.mwanser.fitnessmodel.ExerciseSet;
import android.mwanser.fitnessmodel.Workout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Class for creating a workout and storing it.
 *
 *
 * Created by Wanser on 3/23/17.
 *
 *
 */
//TODO - need serializable function fro writing Workout

public class PerformWorkout extends AppCompatActivity implements PerformanceWorkoutCallback.PerformWorkoutCallbacks {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final String TAG="PerformWorkout";
    private Button mCancelButton;
    private Button mSaveButton;
    private Button mAddButton;
    private String message;
    private ListView mListViewSets;
    private ArrayList<ExerciseSet> theSets;
    private Integer maxHr=0;
    private Integer avgHr=0;
    private Integer time=0;
    private Integer calories=0;
    private Integer type=0;
    private int user;
    private int numberAdded=0;
    private PerformanceWorkoutCallback.PerformWorkoutCallbacks performWorkoutCallbacks;
    private ExerciseSetAdapter adapter;

    public ArrayList<Exercise> getExercises() {
        populateExercise();
        return exercises;
    }

    public ArrayList<Exercise> exercises;

    @Override
    public void onExerciseSetUpdate(ExerciseSet exerciseSet) {

        Log.e("PERFORM WORKOUT", "CALLBACK CALLED!!!");
        if(exerciseSet!=null) {
            numberAdded++;
            updateList(exerciseSet);
        }
        else
            Log.d("PerformWorkout","No Excercise sent back");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.perform_workout);
        Log.d("**PerformWorkout","onCreate");
        Intent intent = getIntent();
        user= PreferenceUtils.getUserLoggedIn(getApplicationContext());
        populateExercise();
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
                if(theSets.get(0).getDescription()=="description"){
                    Toast.makeText(getApplicationContext(),"Add excercises to save",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //create workout from the sets added
                Workout newWorkout= new Workout(theSets,maxHr,avgHr,time,calories,type,user);
                //check that numbers equal
                if(numberAdded==newWorkout.getLengthWorkout())
                    Log.e(TAG,String.valueOf(newWorkout.getLengthWorkout()));
                //get file manipulator
                WorkoutFileManipulator fileManipulator= new WorkoutFileManipulator("workout",
                        newWorkout,0,user);
                //write to file
                fileManipulator.writeWorkouts();
                //new workout is in slot 0

                Intent intent = new Intent(getApplicationContext(), ViewWorkout.class);
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
        mAddButton = (Button) findViewById(R.id.perform_workout_add);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToDO- add dialogue window to add exercise
                //Dialog dialog= new Dialog(getApplicationContext());
                AddExerciseSetDialog newFragment= new AddExerciseSetDialog();
                //newFragment.setArguments();
                newFragment.show(getFragmentManager(),"add");





            }
        });
        adapter= new ExerciseSetAdapter(this,theSets);
        mListViewSets = (ListView) findViewById(R.id.perform_workout_list_view);
        mListViewSets.setAdapter(adapter);

        ExerciseSet temp =new ExerciseSet("Click Add to add Sets to your workout",  "description", 0, 0, 0,
                "Null");
        adapter.add(temp);


    }
    private void populateExercise(){
        String sql="SELECT exercises.name, exercises._id, exercises.category, " +
                "exercises.description, muscles.name as MAINMUSCLE, muscle_second.name" +
                " AS SECONDMUSCLE FROM exercises, muscle_second,muscles WHERE" +
                " ((exercises._id==muscles.exerciseId) AND " +
                "exercises._id == muscle_second.exerciseId)";
        ExerciseDataBaseHelper theDb= new ExerciseDataBaseHelper(this);
        theDb.createDataBase();
        theDb.openDataBase();
        //TODO verify
        exercises=theDb.populateArray();
        Log.e(TAG,String.valueOf(exercises.size()));
        theDb.closeAll();
        theDb.close();
    }

    private void updateList(ExerciseSet exerciseSet){
        //TODO update the list with new exercise
        if(theSets.get(0).getDescription()=="description"){
            //TODO replace the first item with the first set
            theSets.clear();
            theSets.add(exerciseSet);

        }
        else{
            theSets.add(exerciseSet);

            //TODO will adapter automatically update the list one new item detected?
        }
        adapter.notifyDataSetChanged();
    }

}
