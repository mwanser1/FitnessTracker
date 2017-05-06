package android.mwanser.fitnesstracker;

import android.content.Context;
import android.mwanser.fitnessmodel.Workout;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Wanser on 5/6/17.
 */

public class WorkoutAdapter extends ArrayAdapter<Workout> {

    public WorkoutAdapter(Context context, ArrayList<Workout> workouts) {
        super(context, 0, workouts);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        //TODO ADAPT THE SETS
        Workout w= getItem(position);

        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.workout_item,parent,false);


        ImageView mImageview= (ImageView) convertView.findViewById(R.id.workout_item_image);
        TextView mTextViewDate = (TextView) convertView.findViewById(R.id.workout_item_date);
        TextView mTextViewNumber= (TextView) convertView.findViewById(R.id.workout_item_number);
       // TextView mTextViewDesc= (TextView) convertView.findViewById(R.id.exercise_item_desc);
//
        mImageview.setImageResource(R.mipmap.ic_launcher); //TODO update to actual photos
        mTextViewDate.setText(w.getDate().toString());
        mTextViewNumber.setText(String.valueOf(w.getLengthWorkout()));
        //mTextViewDesc.setText(e.getDescription()); //TODO parse HTML

        return convertView;
    }
}
