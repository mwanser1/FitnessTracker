package android.mwanser.fitnesstracker;

import android.content.Intent;
import android.mwanser.PreferenceUtils;
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
import android.mwanser.fitnessmodel.Person;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_spinner_item;

/**
 * Created by Wanser on 3/23/17.
 */

public class EditInfo extends AppCompatActivity{
    private Button cancel;
    private RadioGroup gender;
    private EditText email;
    private EditText oldPassword;
    private EditText newPassword1;
    private EditText newPassword2;
    private EditText restingHeart;
    private EditText vo2Max;
    private Spinner mSpinnerAge;
    private Button save;
    private EditText weight;
    private int userID=0;
    private LoginFileManipulator fileM;
    private Person theUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);
        Log.d("**EditInfo","onCreate");
        userID= PreferenceUtils.getUserLoggedIn(getApplicationContext());
        fileM = new LoginFileManipulator("loginCred.txt");
        //TODO finish getting ser information from the file
        email = (EditText) findViewById(R.id.edit_text_email1);
        email.setText(PreferenceUtils.getEmail(this));

        oldPassword = (EditText) findViewById(R.id.edit_text_Opassword);
        oldPassword.setText("");

        newPassword1 = (EditText) findViewById(R.id.edit_text_password);
        newPassword2 = (EditText) findViewById(R.id.edit_text_password2);

        restingHeart = (EditText) findViewById(R.id.edit_text_resting_hr);
        restingHeart.setText(String.valueOf(PreferenceUtils.getRestingHr(this)));

        vo2Max = (EditText) findViewById(R.id.edit_text_votwo);
        vo2Max.setText(String.valueOf(PreferenceUtils.getVo2(this)));

        List<Integer> ages = new ArrayList<>();
        for (int i = 0; i <= 100; i++) ages.add(i);
        mSpinnerAge = (Spinner) findViewById(R.id.edit_spinner_age);
        ArrayAdapter<Integer> adapterAge = new ArrayAdapter<>(this,
                simple_spinner_item, ages);
        mSpinnerAge.setAdapter(adapterAge);
        mSpinnerAge.setSelection(PreferenceUtils.getAgeUser(this));
        weight = (EditText)findViewById(R.id.edit_text_weight);
        weight.setText(String.valueOf(PreferenceUtils.getWeight(this)));

        theUser= new Person();

        Log.e("EditInfo","idk");
        save = (Button) findViewById(R.id.edit_button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldPassword.setError(null);
                newPassword1.setError(null);
                Integer w = Integer.valueOf(weight.getText().toString());
                Integer a = Integer.valueOf(mSpinnerAge.getSelectedItem().toString());
                //String h = mSpinnerHeight.getSelectedItem().toString();
                int genderId = gender.getCheckedRadioButtonId();
                //int unitID = mUnitsRadioGroup.getCheckedRadioButtonId();
                int g= gender.indexOfChild(gender.
                        findViewById(genderId));
                //int u=mUnitsRadioGroup.indexOfChild(mUnitsRadioGroup.
                       // findViewById(unitID));
                theUser.setAge(Integer.parseInt(mSpinnerAge.getSelectedItem().toString()));
                theUser.setEmail(email.getText().toString());
                theUser.setGender(g);
                theUser.setHeight(PreferenceUtils.getHeight(getApplicationContext()));
                theUser.setVo2(Integer.parseInt(vo2Max.getText().toString()));
                theUser.setRestingHr(Integer.parseInt(restingHeart.getText().toString()));
                theUser.setHeight(PreferenceUtils.getHeight(getApplicationContext()));
                theUser.setUnit(PreferenceUtils.getUnit(getApplicationContext()));
                theUser.setWeight(Integer.parseInt(weight.getText().toString()));


                if(oldPassword.getText().toString().equals(
                        PreferenceUtils.getPw(getApplicationContext()))){

                    if(TextUtils.isEmpty(newPassword1.getText().toString())&&
                            TextUtils.isEmpty(newPassword2.getText().toString())){
                        PreferenceUtils.setWeight(getApplicationContext(),w);
                        PreferenceUtils.setVo2(getApplicationContext(),Integer.parseInt(vo2Max.getText().toString()));
                        PreferenceUtils.setAgeUser(getApplicationContext(),a);
                        PreferenceUtils.setGender(getApplicationContext(),g);
                        PreferenceUtils.setRestingHr(getApplicationContext(),Integer.parseInt(restingHeart.getText().toString()));
                        PreferenceUtils.setEmail(getApplicationContext(),email.getText().toString());
                        theUser.setPassword(PreferenceUtils.getPw(getApplicationContext()));
                        fileM.rewriteFile(theUser,userID);
                        //TODO: implement save new information give dialogue to confirm overrides
                        Intent intent = new Intent(getApplicationContext(), GridActivity.class);
                        startActivity(intent);

                    }
                    else{
                        if(newPassword1.getText().toString().equals(newPassword2.getText().toString())){
                            theUser.setPassword(newPassword1.getText().toString());
                            PreferenceUtils.setWeight(getApplicationContext(),w);
                            PreferenceUtils.setPW(getApplicationContext(),newPassword1.getText().toString());
                            PreferenceUtils.setVo2(getApplicationContext(),Integer.parseInt(vo2Max.getText().toString()));
                            PreferenceUtils.setAgeUser(getApplicationContext(),a);
                            PreferenceUtils.setGender(getApplicationContext(),g);
                            PreferenceUtils.setRestingHr(getApplicationContext(),Integer.parseInt(restingHeart.getText().toString()));
                            PreferenceUtils.setEmail(getApplicationContext(),email.getText().toString());

                            fileM.rewriteFile(theUser,userID);
                            Intent intent = new Intent(getApplicationContext(), GridActivity.class);
                            startActivity(intent);

                        }
                        else{
                            newPassword1.setError("Passwords not equal");
                            newPassword1.requestFocus();
                        }
                    }

                }
                else {
                    oldPassword.setError("Not correct");
                    oldPassword.requestFocus();
                }

            }
        });

        cancel = (Button) findViewById(R.id.edit_button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Intent intent = new Intent(getApplicationContext(), GridActivity.class);
                startActivity(intent);
            }
        });

        gender = (RadioGroup) findViewById(R.id.edit_radio_gender_group);
        //TODO get gender and fillout radio group
        switch(PreferenceUtils.getGender(this)){
            case 0:
                gender.check(R.id.edit_radio_male);
                break;
            case 1:
                gender.check(R.id.edit_radio_female);
                break;
            default:
                gender.check(R.id.edit_radio_other);
                break;
        }




    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.edit_radio_male:
                if (checked)
                    Log.e("RegisterNewClient","male");
                break;
            case R.id.edit_radio_female:
                if (checked)
                    Log.e("RegisterNewClient","female");
                break;
            case R.id.edit_radio_other:
                if (checked)
                    Log.e("RegisterNewClient","other");
                break;
            default:
                Log.e("RegisterNewClient","NO gender selected");
                break;
        }
    }
}
