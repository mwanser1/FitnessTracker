package android.mwanser.fitnesstracker;

import android.content.Context;
import android.mwanser.fitnessmodel.Exercise;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class for adapting Exercise list in perform workout to UI in
 * AddExerciseSetDialog
 * Created by Wanser on 4/14/17.
 */

public class ExerciseAdapter extends ArrayAdapter<Exercise> {

    public ExerciseAdapter(Context context, ArrayList<Exercise> exercises) {
        super(context, 0, exercises);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        //TODO ADAPT THE SETS
        Exercise e= getItem(position);

        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.exercise_item,parent,false);


        ImageView mImageview= (ImageView) convertView.findViewById(R.id.exercise_item_image);
        TextView mTextViewName = (TextView) convertView.findViewById(R.id.exercise_item_name);
        TextView mTextViewCategory= (TextView) convertView.findViewById(R.id.exercise_item_category);
        TextView mTextViewDesc= (TextView) convertView.findViewById(R.id.exercise_item_desc);
//
        mImageview.setImageResource(R.mipmap.ic_launcher); //TODO update to actual photos
        mTextViewName.setText(e.getName());
        mTextViewCategory.setText(e.getCategory());
        //mTextViewDesc.setText(e.getDescription()); //TODO parse HTML

        return convertView;
    }

}
