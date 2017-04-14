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
 * Created by Wanser on 4/14/17.
 */

public class SplashScreen extends AppCompatActivity {
    private ImageView mSplashImage;
    private Handler mHandler;
    private AlertDialog.Builder mDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Log.d("**Splash Screen","onCreate");

        mSplashImage= (ImageView)findViewById(R.id.splash_screen_image);
        mSplashImage.setImageResource(R.mipmap.ic_launcher);
        mHandler= new Handler();
        mHandler.postDelayed(new Runnable() {
            public void run() {
                doDialog();
            }
        },5000);


    }
    private void doDialog(){
        mDialog=new AlertDialog.Builder(SplashScreen.this);
        mDialog.setTitle("Warning");
        mDialog.setMessage("Please consult a physician before performing any " +
                "physical activity.  If at anytime you feel light headed, pain or nauseous" +
                " please discontinue exercise and seek medical attention." +
                "  FitnessTracker is not responsible for injuries incurred while working out and" +
                " the user assumes full responsibility.  ");
        mDialog.setPositiveButton(
                "Accept",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
        mDialog.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert=mDialog.create();
        alert.show();
    }
}
