package android.mwanser;

import android.mwanser.fitnessmodel.ExerciseSet;

/**
 * Created by Wanser on 4/11/17.
 */

public class PerformanceWorkoutCallback {

    public interface PerformWorkoutCallbacks {
        void onExerciseSetUpdate(ExerciseSet exerciseSet);
    }
}
