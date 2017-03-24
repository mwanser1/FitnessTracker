package android.mwanser.fitnesstracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.content.Intent;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;



/**
 * Wanser 3/21/17
 * Main controller for fitness tracker
 * Code Snippets taken from :
 * ----------
 * https://developer.android.com/training/basics/firstapp/starting-activity.html
 * for passing messages between intents
 * ----------
 */

public class GridActivity extends AppCompatActivity {
    public enum ACT { //TODO:convert ints to enums
        EDIT_INFO, CALORIES, BTH_CONNECT, GPS_RUN,
        WORKOUT, FITNESS_TEST, VIEW_PRIOR, EMPTY
    };
    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher//,
//            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
//            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
//            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
//            R.mipmap.ic_launcher
    };

    private void sendToNextActivity(int position){
        //TODO: send to each unique activity with MESSAGES
        Intent intent=null;
        switch(position){
            case 0:  //do something
                intent = new Intent(getApplicationContext(), EditInfo.class);
                break;
            case 1: //do things for next intent
                intent = new Intent(getApplicationContext(), CalorieView.class);
                break;
            case 2: //do things for next intent
                intent = new Intent(getApplicationContext(), BluetoothConnect.class);
                break;
            case 3: //do things for next intent
                intent = new Intent(getApplicationContext(), PerformGPSRun.class);
                break;
            case 4: //do things for next intent
                intent = new Intent(getApplicationContext(), PerformWorkout.class);
                break;
            case 5: //do things for next intent
                intent = new Intent(getApplicationContext(), FitnessTest.class);
                break;
            case 6: //do things for next intent
                intent = new Intent(getApplicationContext(), ViewWorkout.class);
                break;
            case 7: //do things for next intent

                break;
            case 8: //do things for next intent

                break;
            default:
                Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                break;
        }
        if(intent!=null) startActivity(intent);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity);
        Log.d("**GridActivity","onCreate");
        Intent intent = getIntent();
        String message = intent.getStringExtra(LoginActivity.EXTRA_MESSAGE);
        //TODO: should extract user ID and generate previous workouts

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long l){
                Toast.makeText(getApplicationContext(),String.valueOf(position),
                    Toast.LENGTH_SHORT).show();

                sendToNextActivity(position);

                }
            }
        );

        // Instance of GridItemAdapter Class
        gridView.setAdapter(new GridItemAdapter(this,mThumbIds));
    }
}
