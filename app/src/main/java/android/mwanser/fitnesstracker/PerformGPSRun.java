package android.mwanser.fitnesstracker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.util.List;

import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


/**
 *
 * Obtaining key
 * http://stackoverflow.com/questions/30070264/get-sha1-fingerprint-certificate-in-android-studio-for-google-maps
 *
 * Created by Wanser on 3/23/17.
 */

public class PerformGPSRun extends AppCompatActivity {

    private LocationManager mLocation;
    private TextView distanceTextView;
        private double pastLatLon[]= null;
    private double currentLatLon[] = null;
    private int distance=0;
        private float[] r= new float[1];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.perform_gps_run);
        setContentView(R.layout.distance_tracker);
        Log.d("**PerformGPSRun","onCreate");

        distanceTextView = (TextView) findViewById(R.id.distance_distance_text);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(PerformGPSRun.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

            recreate();
            return;
        }
        final LocationManager locationManager = (LocationManager)
                this.getSystemService(Context.LOCATION_SERVICE);
        final LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {


                // Ensure we have permission to access GPS
                if (ActivityCompat.checkSelfPermission(PerformGPSRun.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(PerformGPSRun.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(PerformGPSRun.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
                    return;
                }
                if (pastLatLon==null){
                    pastLatLon= new double[2];
                    currentLatLon=new double[2];
                    pastLatLon[0]=location.getLatitude();
                    pastLatLon[1]=location.getLongitude();

                }
                else{
                    currentLatLon[0]=location.getLatitude();
                    currentLatLon[1]=location.getLongitude();
                    Log.e("*****",String.valueOf(pastLatLon[0])+"  "+String.valueOf(pastLatLon[1]));
                    Log.e("----",String.valueOf(currentLatLon[0])+"   "+String.valueOf(currentLatLon[1]));
                    Location.distanceBetween(pastLatLon[0],pastLatLon[1],currentLatLon[0],
                            currentLatLon[1],r);

                    distance+=r[0];
                    r[0]=0;
                    distanceTextView.setText("Distance: "+String.valueOf(distance));
                    pastLatLon[0]=currentLatLon[0];
                    pastLatLon[1]=currentLatLon[1];
                }
                Log.e("----",String.valueOf(distance));


            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}

        };

        // Request updates from both GPS and Network providers
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);




    }
}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        distanceTextView=(TextView) findViewById(R.id.distance);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
//
//            recreate();
//            return;
//        }
//        final LocationManager locationManager = (LocationManager)
//                this.getSystemService(Context.LOCATION_SERVICE);
//        final LocationListener locationListener = new LocationListener() {
//
//            public void onLocationChanged(Location location) {
//
//
//                // Ensure we have permission to access GPS
//                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
//                    return;
//                }
//                if (pastLatLon==null){
//                    pastLatLon= new double[2];
//                    currentLatLon=new double[2];
//                    pastLatLon[0]=location.getLatitude();
//                    pastLatLon[1]=location.getLongitude();
//
//                }
//                else{
//                    currentLatLon[0]=location.getLatitude();
//                    currentLatLon[1]=location.getLongitude();
//                    Log.e("*****",String.valueOf(pastLatLon[0])+"  "+String.valueOf(pastLatLon[1]));
//                    Log.e("----",String.valueOf(currentLatLon[0])+"   "+String.valueOf(currentLatLon[1]));
//                    Location.distanceBetween(pastLatLon[0],pastLatLon[1],currentLatLon[0],
//                            currentLatLon[1],r);
//
//                    distance+=r[0];
//                    r[0]=0;
//                    distanceTextView.setText(String.valueOf(distance));
//                    pastLatLon[0]=currentLatLon[0];
//                    pastLatLon[1]=currentLatLon[1];
//                }
//                Log.e("----",String.valueOf(distance));
//
//
//            }
//
//            public void onStatusChanged(String provider, int status, Bundle extras) {}
//
//            public void onProviderEnabled(String provider) {}
//
//            public void onProviderDisabled(String provider) {}
//
//        };
//
//        // Request updates from both GPS and Network providers
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//
//
//
//
//
//    }
//}
