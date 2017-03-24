package android.mwanser.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Wanser on 3/22/17.
 * Some code Fragments taken from Android Developer Website and
 * https://github.com/googlesamples
 *
 */

public class LoginActivity extends AppCompatActivity {
    //private UserLoginTask mAuthTask = null;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private static final ArrayList<String[]> FAKE_CRED= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Log.d("**LoginActivity","onCreate");

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
        Button mRegisterButton= (Button) findViewById(R.id.email_register_button);
        //TODO: REGISTER PAGE
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Register Clicked and Not Implemented.",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), RegisterNewClient.class);
                startActivity(intent);
            }
        });

        FAKE_CRED.add(new String[]{"jake@unh.com","jakejake"});
        FAKE_CRED.add(new String[]{"bob@unh.com","bobbob"});

    }

    private void doLogin(){

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
        boolean successful=false;
        if(TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel=true;
        }
        if(TextUtils.isEmpty(email)){
            mEmailView.setError(getString(R.string.error_field_required));
            focusView=mEmailView;
            cancel=true;
        }
        if(cancel){
            Toast.makeText(getApplicationContext(), getString(R.string.error_login_failed),
                    Toast.LENGTH_SHORT).show();
            focusView.requestFocus();
            return;
        }else {
            for(String[] e : FAKE_CRED){
                if(e[0].equals(email)&&e[1].equals(password)){
                    successful=true;
                    break;
                }
            }
        }
        if(successful){
            //TODO: Should pass userID not EMAIL
            Intent intent = new Intent(this, GridActivity.class);
            EditText editText = (EditText) findViewById(R.id.email);
            String message = editText.getText().toString();
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }else{
            mPasswordView.setError(getString(R.string.credential_failed));
            mEmailView.setError(getString(R.string.credential_failed));
            mEmailView.requestFocus();
        }


    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 4;
    }



}
