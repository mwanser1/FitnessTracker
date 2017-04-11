package android.mwanser.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.mwanser.fitnessmodel.Person;

/**
 * Created by Wanser on 3/23/17.
 */

public class EditInfo extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);
        Log.d("**EditInfo","onCreate");
        Person theUser= new Person(100,100,100,100,100,true,"blahblah","gobblygook");
        Log.e("EditInfo",theUser.toString());
        Button save = (Button) findViewById(R.id.edit_button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: implement save new information give dialogue to confirm overrides
                Intent intent = new Intent(getApplicationContext(), GridActivity.class);
                startActivity(intent);
            }
        });

        Button cancel = (Button) findViewById(R.id.edit_button_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                Intent intent = new Intent(getApplicationContext(), GridActivity.class);
                startActivity(intent);
            }
        });

        RadioGroup gender = (RadioGroup) findViewById(R.id.edit_radio_gender_group);
        //TODO get gender and fillout radio group
        gender.check(R.id.edit_radio_other);

        EditText email = (EditText) findViewById(R.id.edit_text_email1);
        email.setText(theUser.getEmail());

        EditText oldPassword = (EditText) findViewById(R.id.edit_text_Opassword);
        oldPassword.setText(theUser.getPassword());

        EditText newPassword1 = (EditText) findViewById(R.id.edit_text_password);
        EditText newPassword2 = (EditText) findViewById(R.id.edit_text_password2);

        EditText restingHeart = (EditText) findViewById(R.id.edit_text_resting_hr);
        restingHeart.setText(String.valueOf(theUser.getRestingHr()));

        EditText vo2Max = (EditText) findViewById(R.id.edit_text_votwo);
        vo2Max.setText(String.valueOf(theUser.getVo2()));


        //TODO : confirm new password by validating they are equal and old password correct



        //TODO: confirm all changes with dialogue




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
