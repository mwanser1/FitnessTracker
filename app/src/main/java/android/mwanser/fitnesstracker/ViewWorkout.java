package android.mwanser.fitnesstracker;

import android.content.Intent;
import android.mwanser.PreferenceUtils;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Wanser on 3/23/17.
 */

public class ViewWorkout extends AppCompatActivity {
    private ListView mListView;
    private WorkoutAdapter adapter;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private Button mButtonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_workout);
        //ActionBar toolbar = getSupportActionBar();
        //toolbar.setNavigationMode(0);
        Log.d("**ViewWorkout","onCreate");
        WorkoutFileManipulator fileManipulator = new WorkoutFileManipulator("workout", null,0,0);
        Toast.makeText(getApplicationContext(),fileManipulator.getfirst(),
                    Toast.LENGTH_SHORT).show();
        mButtonHome = (Button) findViewById(R.id.view_workout_home);
        mButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GridActivity.class);
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
        mListView = (ListView) findViewById(R.id.view_workout_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Go view the workout",
                        Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getApplicationContext(),ViewOneWorkout.class);
                //fileManipulator=null;
                intent.putExtra(EXTRA_MESSAGE,String.valueOf(i));
                startActivity(intent);
            }
        });
        adapter= new WorkoutAdapter(this, fileManipulator.getWorkouts());
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fileManipulator.destroy();
        fileManipulator=null;

    }
//    @Override
//    protected void (Bundle savedInstanceState){
//        Log.d("*-ViewWorkout","onDestroy");
//        super.onDestroy();
//    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Toast.makeText(getApplicationContext(),"No back button allowed",
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK)
            Toast.makeText(getApplicationContext(), "back press",
                    Toast.LENGTH_LONG).show();

        return false;
        // Disable back button..............
    }
}
