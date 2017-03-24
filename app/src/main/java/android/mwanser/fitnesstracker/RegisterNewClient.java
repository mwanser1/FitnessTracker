package android.mwanser.fitnesstracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Wanser on 3/23/17.
 */

public class RegisterNewClient extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_new_client);
        Log.d("**RegisterNewClient","onCreate");

    }
}
