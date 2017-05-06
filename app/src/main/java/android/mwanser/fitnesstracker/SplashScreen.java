package android.mwanser.fitnesstracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 *
 * Class for the splash screen
 * Created by Wanser on 4/14/17.
 */

public class SplashScreen extends AppCompatActivity {
    private ImageView mSplashImage; //Applciaiton Image
    private Handler mHandler;       //Handler for waiting 3 secodns before dialog
    private AlertDialog.Builder mDialog; //dialog for user to accept

    /**
     * OnCreate when splash screen loads ( like constructor)
     * @param savedInstanceState -
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Log.d("**Splash Screen","onCreate");
        //set the image
        mSplashImage= (ImageView)findViewById(R.id.splash_screen_image);
        mSplashImage.setImageResource(R.mipmap.ic_launcher);
        //set the handler
        mHandler= new Handler();
        //Set delay and call function for popualting dialog after 3 seconds
        mHandler.postDelayed(new Runnable() {
            public void run() {
                doDialog();
            }
        },3000);
    }

    /**
     * Dialog for user to accept that he is responsible for his own well being.
     * user must click accept in order to use application each time it is loaded
     */
    private void doDialog(){
        mDialog=new AlertDialog.Builder(SplashScreen.this); //bind Dialog to activity
        mDialog.setTitle("Warning"); //set title
        //set message
        mDialog.setMessage("Please consult a physician before performing any " +
                "physical activity.  If at anytime you feel light headed, pain or nauseous" +
                " please discontinue exercise and seek medical attention." +
                "  FitnessTracker is not responsible for injuries incurred while working out and" +
                " the user assumes full responsibility.  ");
        //sets positive button for agreeing
        mDialog.setPositiveButton(
                "Accept",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // user agreed therefore go to login
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
        //User clicked cancle app does nothing
        mDialog.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish(); //exits aplication if not accepted
                    }
                });
        AlertDialog alert=mDialog.create(); //create dialog
        alert.show(); //show dialog.
    }
}
