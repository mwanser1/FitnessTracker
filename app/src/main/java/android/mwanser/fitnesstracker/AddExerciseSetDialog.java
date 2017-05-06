package android.mwanser.fitnesstracker;


import android.app.Dialog;
import android.app.DialogFragment;

import android.content.Context;
import android.mwanser.PerformanceWorkoutCallback;
import android.mwanser.fitnessmodel.Exercise;
import android.mwanser.fitnessmodel.ExerciseSet;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that creates a dialog pop up for adding E to PerformWorkout
 * Created by Wanser on 4/11/17.
 */

public class AddExerciseSetDialog extends DialogFragment {
    private static final String TAG="AddExerciseSetDialog";
    private ListView mListExercises;
    private Button mSaveButton;
    private Button mCancelButton;
    private Button mSearchButton;
    private EditText mSearchText;
    private EditText mWeightText;
    private EditText mRepsText;
    private EditText mRestText;
    private CheckBox mChestCheckBox;
    private CheckBox mAbsCheckBox;
    private CheckBox mLegsCheckBox;
    private CheckBox mArmsCheckBox;
    private CheckBox mBackCheckBox;
    private CheckBox mCalvesCheckBox;
    private CheckBox mShouldersCheckBox;
    private LinearLayout mLinearLayoutSingle;
    private TextView mTextDesc;
    private TextView mTextName;
    private View rootView;
    private Exercise temp;
    private static boolean listFlag=true;
    private ArrayList<Exercise> E =null;
    private ExerciseAdapter adapter=null;

    private PerformanceWorkoutCallback.PerformWorkoutCallbacks callbacks;

    /**
     * call back fo sending exercise to the Perform workout class
     * @param savedInstanceState-
     */
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

    /**
     * on create for dialog to add exercise to workout
     * @param inflater-
     * @param container-
     * @param savedInstanceState-
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.add_exercise_layout, container, false);
        Log.d(TAG,"onCreateView");
        this.E = ((PerformWorkout) getActivity()).getExercises();
        Log.e(TAG,"Exercise: "+String.valueOf(E.size()));
        setButtons(); //sets buttons nd UI elements

        //Set List


        return rootView;
    }

    /**
     * returns the dialog
     * @param savedInstanceState-
     * @return- dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(TAG,"OnCreatDialog");
        return dialog;
    }

    /**
     * fucntion for clearing menu
     * @param menu-
     * @param inflater-
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        //getActivity().getMenuInflater().inflate(R.menu.menu_ak, menu);
    }

    /**
     * handles selecting a combo box item for filtering results
     * @param view-
     */
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

    /**
     * sets the UI elements
     */
    private void setButtons(){
        mSaveButton= (Button) rootView.findViewById(R.id.add_exercise_add);
        mCancelButton= (Button) rootView.findViewById(R.id.add_exercise_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbacks.onExerciseSetUpdate(null);
                listFlag=true;
                getDialog().dismiss();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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
        mSearchText=(EditText) rootView.findViewById(R.id.add_exercise_text_search);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exerciseSet= new ExerciseSet();
                if(checkInput()){
                    ExerciseSet itemAdd= new ExerciseSet(temp.getName(),temp.getDescription(),
                            Integer.valueOf(mWeightText.getText().toString()),
                            Integer.valueOf(mRepsText.getText().toString()),
                            Integer.valueOf(mRestText.getText().toString()),"Normal");
                    callbacks.onExerciseSetUpdate(itemAdd);
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                    getDialog().dismiss();
                }

            }
        });
        mSearchButton= (Button) rootView.findViewById(R.id.add_exercise_search);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mSearchText.getWindowToken(), 0);
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                E = ((PerformWorkout) getActivity()).getExercises();
                filterListResults();

            }
        });


        //items for completing the exercise
        mTextName= (TextView) rootView.findViewById(R.id.add_exercise_text_single);
        mTextDesc =(TextView) rootView.findViewById(R.id.add_exercise_text_desc);
        mRepsText = (EditText) rootView.findViewById(R.id.add_exercise_reps);
        mRestText = (EditText) rootView.findViewById(R.id.add_exercise_rest);
        mWeightText = (EditText) rootView.findViewById(R.id.add_exercise_weight);
        //mTextDesc.setMovementMethod(new ScrollingMovementMethod());
        mLinearLayoutSingle=(LinearLayout) rootView.findViewById(R.id.add_exercise_linear_single);
        //list of E to pick from
        mListExercises = (ListView) rootView.findViewById(R.id.add_exercise_list_result);
        mListExercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                temp= E.get(i);
                Toast.makeText(getActivity().getApplicationContext(), "i: "+String.valueOf(i)+
                        "l: "+String.valueOf(l),Toast.LENGTH_SHORT).show();

                //change view since i have clicked on the item to pop exercise set data
                swapViews();

                mTextName.setText(temp.getName());
                Log.e("TAG",temp.getDescription());
                mTextDesc.setText(Html.fromHtml(temp.getDescription()));


            }
        });

        this.E = ((PerformWorkout) getActivity()).exercises;
        adapter = new ExerciseAdapter(getActivity().getApplicationContext(), this.E);
        mListExercises.setAdapter(adapter);

    }
    private boolean checkInput(){
        mRestText.setError(null);
        mRepsText.setError(null);
        mWeightText.setError(null);

        boolean temp=true;

        if(TextUtils.isEmpty(mWeightText.getText().toString()))
            {temp=false; mWeightText.setError("Need value");}
        if(TextUtils.isEmpty(mRepsText.getText().toString())){
            temp=false; mRepsText.setError("Need value");
        }
        if(TextUtils.isEmpty(mRestText.getText().toString())){
            temp=false; mRestText.setError("Need Value");
        }

        return temp;
    }
    private void swapViews(){
        mListExercises.setVisibility(View.GONE);
        mChestCheckBox.setVisibility(View.GONE);
        mAbsCheckBox.setVisibility(View.GONE);
        mArmsCheckBox.setVisibility(View.GONE);
        mBackCheckBox.setVisibility(View.GONE);
        mSearchButton.setVisibility(View.GONE);
        mSearchText.setVisibility(View.GONE);
        mLegsCheckBox.setVisibility(View.GONE);
        mCalvesCheckBox.setVisibility(View.GONE);
        mShouldersCheckBox.setVisibility(View.GONE);
        mLinearLayoutSingle.setVisibility(View.VISIBLE);

    }

    /**
     * function to filter the results of the list of E
     */
    private void filterListResults() {
        ArrayList<String> keywords = new ArrayList<>();
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<Exercise> temp = new ArrayList<>();
        if (mCalvesCheckBox.isChecked()) {
            categories.add("Calves");
        }
        if (mShouldersCheckBox.isChecked())
            categories.add("Shoulders");
        if (mLegsCheckBox.isChecked())
            categories.add("Legs");
        if (mBackCheckBox.isChecked())
            categories.add("Back");
        if (mArmsCheckBox.isChecked())
            categories.add("Arms");
        if (mAbsCheckBox.isChecked())
            categories.add("Abs");
        if (mChestCheckBox.isChecked())
            categories.add("Chest");
        if (!TextUtils.isEmpty(mSearchText.getText().toString())) {
            Collections.addAll(keywords, mSearchText.getText().toString().split(" "));
        }

        Log.e(TAG, "ELSE FILTER: " + String.valueOf(keywords.size()) + " SIze: " + String.valueOf(categories.size()));
        if(keywords.size()==0 && categories.size()==0){
            Log.e("THIS","reset to E");

            adapter.clear();
            adapter.addAll(this.E);
            adapter.notifyDataSetChanged();
            return;
        }

        for (Exercise e : this.E) {
            //Log.d(TAG,e.getCategory());
            for (String s : categories)
                if (e.getCategory().trim().equals(s.trim())) {
                    //Log.e(TAG,e.getName());
                    temp.add(e);
                    break;
                }
            for (String s : keywords)
                if (e.getName().toLowerCase().contains(s.trim().toLowerCase()) && !temp.contains(e)) {
                    //Log.e(TAG,e.getName());
                    temp.add(e);
                    break;
                }
        }
        Log.e(TAG, "TEMP: " + String.valueOf(temp.size()));
        //TODO UPDATE EXERCISE WITH TEMP
        adapter.clear();
        adapter.addAll(temp);
        adapter.notifyDataSetChanged();
    }


}
