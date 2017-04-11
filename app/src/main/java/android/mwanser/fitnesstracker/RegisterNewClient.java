package android.mwanser.fitnesstracker;

import android.content.Intent;
import android.mwanser.PreferenceUtils;
import android.mwanser.fitnessmodel.Person;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;

/**
 * Created by Wanser on 3/23/17.
 * Backend for taking new user information in
 *
 * writes to flat file
 *
 */

public class RegisterNewClient extends AppCompatActivity {

    //private final String[] genders= {"MALE","FEMALE","OTHER"};
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private final String[] heights = {
            "null", "3'0", "3'1", "3'3", "3'4", "3'5", "3'6", "3'7", "3'8", "3'9", "3'10", "3'11",
            "4'0", "4'1", "4'3", "4'4", "4'5", "4'6", "4'7", "4'8", "4'9", "4'10", "4'11",
            "5'0", "5'1", "5'3", "5'4", "5'5", "5'6", "5'7", "5'8", "5'9", "5'10", "5'11",
            "6'0", "6'1", "6'3", "6'4", "6'5", "6'6", "6'7", "6'8", "6'9", "6'10", "6'11"
    };
    //Class viarables for UI
    private Button mSaveButon;
    private Button mCancelButton;
    private Spinner mSpinnerAge;
    private Spinner mSpinnerHeight;
    private EditText mEmailText;
    private EditText mEmailText2;
    private EditText mPasswordText;
    private EditText mPasswordText2;
    private RadioGroup mGenderRadioGroup;
    private RadioGroup mUnitsRadioGroup;
    private EditText   mWeightText;
    private View focusView;
    private String toastMessage;
    private FileManipulator loginFile;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_new_client);
        Log.d("**RegisterNewClient","onCreate");

        loginFile=new FileManipulator("loginCred.txt");
        //save button-- handles checking and writing to file
        mSaveButon = (Button) findViewById(R.id.register_button_save);
        mSaveButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { doCreate();}});

        //Cancel button
        mCancelButton = (Button) findViewById(R.id.register_button_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        //spinner for age 0-100, 0 is default and error
        List<Integer> ages = new ArrayList<>();
        for (int i = 0; i <= 100; i++) ages.add(i);
        mSpinnerAge = (Spinner) findViewById(R.id.register_spinner_age);
        ArrayAdapter<Integer> adapterAge = new ArrayAdapter<>(this,
                simple_spinner_item, ages);
        mSpinnerAge.setAdapter(adapterAge);
        mSpinnerAge.setSelection(0);

        //Spinner for hieght, null is error
        mSpinnerHeight = (Spinner) findViewById(R.id.register_spinner_height);
        ArrayAdapter<String> adapterHeight = new ArrayAdapter<>(this,
                simple_spinner_item, heights);
        mSpinnerHeight.setAdapter(adapterHeight);
        mSpinnerHeight.setSelection(0);

        //email text
        mEmailText = (EditText) findViewById(R.id.register_text_email1);
        mEmailText2 = (EditText) findViewById(R.id.register_text_email2);

        //password text
        mPasswordText = (EditText) findViewById(R.id.register_text_password);
        mPasswordText2 = (EditText) findViewById(R.id.register_text_password2);

        //gender radio
        mGenderRadioGroup = (RadioGroup) findViewById(R.id.register_radio_gender_group);
        //units radio
        mUnitsRadioGroup = (RadioGroup) findViewById(R.id.register_radio_unit_group);
        //weight edit text
        mWeightText = (EditText) findViewById(R.id.register_text_weight);

    }

    private void writeToPersistentStroage(Person p){
        //TODO: store in DB
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.register_radio_male:
                if (checked)
                    Log.e("RegisterNewClient","male");
                    break;
            case R.id.register_radio_female:
                if (checked)
                    Log.e("RegisterNewClient","female");
                    break;
            case R.id.register_radio_other:
                if (checked)
                    Log.e("RegisterNewClient","other");
                    break;
            default:
                Log.e("RegisterNewClient","NO gender selected");
                break;
        }
    }
    private String onSaveGender(int i){
        switch(i){
            case 0:
                return "MALE";
            case 1:
                return "FEMALE";
            case 2:
                return "OTHER";
            default:
                return "ERROR";
        }

    }
    private void resetErrors(){
        //TODO spinners and radio groups need error messages
        mSaveButon.setError(null);
        mCancelButton.setError(null);
        //mSpinnerAge.setError(null);
        //mSpinnerHeight.setError(null);
        mEmailText.setError(null);
        mEmailText2.setError(null);
        mPasswordText.setError(null);
        mPasswordText2.setError(null);
        //mGenderRadioGroup.setError(null);
        //mUnitsRadioGroup.setError(null);
        mWeightText.setError(null);

        return;

    }

    private void doCreate(){
        //reset errors
        resetErrors();

        boolean cancel= false;
        focusView = null;
        boolean succesful= false;

//get values at save time
        String email1= mEmailText.getText().toString();
        String email2 = mEmailText2.getText().toString();
        String pw1 = mPasswordText.getText().toString();
        String pw2 = mPasswordText2.getText().toString();
        Integer weight = Integer.valueOf(mWeightText.getText().toString());
        Integer age = Integer.valueOf(mSpinnerAge.getSelectedItem().toString());
        String height = mSpinnerHeight.getSelectedItem().toString();
        int genderId = mGenderRadioGroup.getCheckedRadioButtonId();
        int unitID = mUnitsRadioGroup.getCheckedRadioButtonId();
        int gender= mGenderRadioGroup.indexOfChild(mGenderRadioGroup.
                findViewById(genderId));
        int unit=mUnitsRadioGroup.indexOfChild(mUnitsRadioGroup.
                findViewById(unitID));

        toastMessage="";
        //check for empty input use toast for spinners and radio groups
        cancel=checkInputEmpty(email1, email2, pw1, pw2, weight, age, height,
                gender);
        //if this statement executes then missing input
        if(cancel==true && !toastMessage.equals("")) {
            Toast.makeText(getApplicationContext(), toastMessage,
                    Toast.LENGTH_SHORT).show();
            focusView.requestFocus();
            return;
        }

        //now check email and pass word are correct
        succesful=isEmailValid(email1,email2);
        if(!succesful){
            Toast.makeText(getApplicationContext(), toastMessage,
                    Toast.LENGTH_SHORT).show();
            focusView.requestFocus();
            return;

        }


        succesful=checkPasswordSame(pw1,pw2);
        if(!succesful){
            Toast.makeText(getApplicationContext(), getString(R.string.credential_failed),
                    Toast.LENGTH_SHORT).show();
            focusView=mPasswordText2;
            focusView.requestFocus();
            return;
        }

        //if i get here then all input is valid
        if(succesful){
            boolean createdSuccess=loginFile.writeToFile(email1,pw1,weight,height,age,gender,unit);
//            String message = new String(email1+","+pw1+","+weight.toString()+","+
//                    age.toString()+","+height+","+gender+","+unit);
            //TODO: write to file---- check if duplicate user
            if(createdSuccess) {
//                intent.putExtra(EXTRA_MESSAGE, message);
                Intent intent = new Intent(this, LoginActivity.class);
                loginFile.closeStreams();
                loginFile=null;
                startActivity(intent);
            }
            else{
                Log.e("RegisterNewClient","Write to file failed ");
                Toast.makeText(getApplicationContext(), "Error in writing to file, check permissions",
                        Toast.LENGTH_SHORT).show();
            }

        }
        return ;
    }

    private boolean checkPasswordSame(String pw1, String pw2){
        return (pw1.equals(pw2));

    }
    private boolean isEmailValid(String email1, String email2) {
        if(email1.contains("@")&&email2.equals(email1))
            return true;
        else{
            toastMessage+="Email not valid.  ";
            focusView=mEmailText;
            return false;
        }

    }

    private boolean checkInputEmpty(String email1, String email2, String pw1,
                                   String pw2, Integer weight, Integer age,
                                   String height, int gender){
        boolean cancel=false;
        if(TextUtils.isEmpty(email1)){
            mEmailText.setError(getString(R.string.error_field_required));
            focusView=mEmailText;
            cancel=true;
        }
        if(TextUtils.isEmpty(email2)){
            mEmailText2.setError(getString(R.string.error_field_required));
            focusView=mEmailText2;
            cancel=true;
        }
        if(TextUtils.isEmpty(pw1)){
            mPasswordText.setError(getString(R.string.error_field_required));
            focusView=mPasswordText;
            cancel=true;
        }
        if(TextUtils.isEmpty(pw2)){
            mPasswordText2.setError(getString(R.string.error_field_required));
            focusView=mPasswordText2;
            cancel=true;
        }
        if(weight.intValue()==0){
            mWeightText.setError(getString(R.string.error_field_required));
            focusView=mWeightText;
            cancel=true;
        }
        if(age.intValue()==0){
            toastMessage+="Must input age.  ";
            focusView=mSpinnerAge;
            cancel=true;
        }
        if(height.equals(heights[0])){
            toastMessage+="Must input height.  ";
            focusView=mSpinnerHeight;
            cancel=true;
        }
        if(gender==-1){
            toastMessage+= "Must select gender.";
            focusView= mGenderRadioGroup;
            cancel=true;
        }


        return cancel;
    }

}
