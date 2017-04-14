package android.mwanser.fitnesstracker;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.mwanser.PerformanceWorkoutCallback;
import android.mwanser.fitnessmodel.Exercise;
import android.mwanser.fitnessmodel.ExerciseSet;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;

/**
 * Created by Wanser on 4/11/17.
 */

public class AddExerciseSetDialog extends DialogFragment {
    private static final String TAG="AddExerciseSetDialog";
    private ListView mListExercises;
    private Button mSaveButton;
    private Button mCancelButton;
    private CheckBox mChestCheckBox;
    private CheckBox mAbsCheckBox;
    private CheckBox mLegsCheckBox;
    private CheckBox mArmsCheckBox;
    private CheckBox mBackCheckBox;
    private CheckBox mCalvesCheckBox;
    private CheckBox mShouldersCheckBox;
    private View rootView;
    private ExerciseSet exerciseSet;

    private PerformanceWorkoutCallback.PerformWorkoutCallbacks callbacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            callbacks = (PerformanceWorkoutCallback.PerformWorkoutCallbacks) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.add_exercise_layout, container, false);
        setButtons();

        return rootView;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        //getActivity().getMenuInflater().inflate(R.menu.menu_ak, menu);
    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()){
            case R.id.add_exercise_check_chest:
                if(checked)
                    //do something
                    Log.d(TAG, "Chest checked");
                else
                    //do something
                    Log.d(TAG, "Chest not checked");
                break;
            case R.id.add_exercise_check_abs:
                if(checked)
                    Log.d(TAG, "Abs checked");
                else
                    Log.d(TAG, "abs not checked");
                break;
        }

    }
    private void setButtons(){
        mSaveButton= (Button) rootView.findViewById(R.id.add_exercise_add);
        mCancelButton= (Button) rootView.findViewById(R.id.add_exercise_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbacks.onExerciseSetUpdate(null);
                getDialog().dismiss();
            }
        });
        mChestCheckBox = (CheckBox) rootView.findViewById(R.id.add_exercise_check_chest);
        mChestCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mAbsCheckBox=(CheckBox) rootView.findViewById(R.id.add_exercise_check_abs);
        mAbsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLegsCheckBox=(CheckBox) rootView.findViewById(R.id.add_exercise_check_legs);
        mLegsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mArmsCheckBox=(CheckBox) rootView.findViewById(R.id.add_exercise_check_arms);
        mArmsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mBackCheckBox=(CheckBox) rootView.findViewById(R.id.add_exercise_check_back);
        mBackCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mCalvesCheckBox=(CheckBox) rootView.findViewById(R.id.add_exercise_check_calves);
        mCalvesCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mShouldersCheckBox=(CheckBox) rootView.findViewById(R.id.add_exercise_check_shoulders);
        mShouldersCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseSet= new ExerciseSet();
                callbacks.onExerciseSetUpdate(exerciseSet);
                getDialog().dismiss();

            }
        });
        mListExercises = (ListView) rootView.findViewById(R.id.add_exercise_list_result);
        //TODO add adapter

    }
    private void filterListResults(){
        //TODO take input and filter the list
        ArrayList<Exercise> temp =((PerformWorkout)getActivity()).exercises;
    }


}
