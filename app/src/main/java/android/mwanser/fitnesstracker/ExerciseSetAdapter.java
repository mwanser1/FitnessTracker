package android.mwanser.fitnesstracker;

import android.content.Context;
import android.mwanser.fitnessmodel.ExerciseSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Wanser on 4/10/17.
 */

public class ExerciseSetAdapter extends ArrayAdapter<ExerciseSet> {
    private ImageView mImageview;
    private TextView mTextViewName;
    private TextView mTextViewWeight;
    private TextView mTextViewReps;


    public ExerciseSetAdapter(Context context, ArrayList<ExerciseSet> theSet){
        super(context,0,theSet);

    };
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //TODO ADAPT THE SETS
        ExerciseSet e= getItem(position);

        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.exercise_set_item,parent,false);
        mImageview= (ImageView) convertView.findViewById(R.id.exercise_set_item_image);
        mTextViewName = (TextView) convertView.findViewById(R.id.exercise_set_item_name);
        mTextViewReps= (TextView) convertView.findViewById(R.id.exercise_set_item_reps);
        mTextViewWeight= (TextView) convertView.findViewById(R.id.exercise_set_item_weight);

        mImageview.setImageResource(R.mipmap.ic_launcher); //TODO update to actual photos
        mTextViewName.setText(e.getName());
        mTextViewReps.setText(String.valueOf(e.getReps())+" reps");
        mTextViewWeight.setText(String.valueOf(e.getWeight())+" lbs\t");

        return convertView;
    }

}