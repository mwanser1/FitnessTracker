package android.mwanser.fitnessmodel;

import java.util.ArrayList;

/**
 * this is a data class for holding exercises,
 * which populate a exercise set(has more unique data)
 * populated from Exercises.db in perform workout
 *
 * Created by Wanser on 4/11/17.
 */

public class Exercise {
    private String name;
    private String muscleUsed;
    private ArrayList<String> additionalMuscles;
    private int _id;
    private String category;
    private String description;

    public ArrayList<String> getAdditionalMuscles() {
        return additionalMuscles;
    }

    public void setAdditionalMuscles(ArrayList<String> additionalMuscles) {
        this.additionalMuscles = additionalMuscles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscleUsed() {
        return muscleUsed;
    }

    public void setMuscleUsed(String muscleUsed) {
        this.muscleUsed = muscleUsed;
    }



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Exercise(){};

    /**
     * constructor for when reading form exercise table
     * @param name - name
     * @param id - id
     * @param category - category
     * @param description - description
     */
    public Exercise(String name, int id, String category, String description){
        this.name=name;
        this._id=id;
        this.category=category;
        this.description=description;

    }

    /**
     * constructor for all exercise informaiton
     * @param name -
     * @param musclesUsed  main muslce
     * @param additionalMuscles - secondary
     * @param id-
     * @param category-
     * @param description-
     */
    public Exercise(String name, String musclesUsed, ArrayList<String> additionalMuscles,
                    int id, String category, String description){
        this.name=name;
        this.muscleUsed =musclesUsed;
        this.additionalMuscles=additionalMuscles;
        this._id=id;
        this.category=category;
        this.description=description;
    }
    public String toString(){
        return name+":"+ muscleUsed +":"+String.valueOf(additionalMuscles.size())+":"+String.valueOf(_id)+":"+category;
    }


}
