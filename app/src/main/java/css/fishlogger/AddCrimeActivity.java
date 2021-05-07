package css.fishlogger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCrimeActivity extends AppCompatActivity {

    Button buttonEnter;
    EditText editTextLocation, editTextDesc, editTextTime, editTextDate;
    Double latitude, longitude;
    CrimeFirebaseData crimeDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fish);

        // link each editText variable to the xml layout
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextDesc = (EditText) findViewById(R.id.editTextDesc);
        editTextTime = (EditText) findViewById(R.id.editTextTime);
        editTextDate = (EditText) findViewById(R.id.editTextDate);

        crimeDataSource = new CrimeFirebaseData();
        crimeDataSource.open();

        getGPSLocation();
        setupSaveButton();

        }

    /**
     *  getGPSLocation -- This tries to get the GPS locaiton, asking the user turn on permissions if needed and turn on GPS if needed.
     *      -- This is not the best solution, but tries to provide a good solution without too much complicated code.
     *      -- You should run the Map or something else the uses the GPS before running this, since it simply requests the last GPS location
     *             instead of requesting a new reading and using a listener to wait for the response
     *      -- You might also have to run this once to enable permissions and then run it agian once permissions are enabled.
     */
    private void getGPSLocation() {
            // get the current location of the phone
            LocationManager locationManager = (LocationManager) getSystemService(this.getApplicationContext().LOCATION_SERVICE);
            //LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (latitude == null) {
                latitude = 0.0;
                longitude = 0.0;
            }
            Criteria criteria = new Criteria();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("CIS 3334", "Cannot get locations -- permission denied");
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 9999);
            }

            // --- Prompt the user to Enabled GPS if needed
            boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            // check if enabled and if not send user to the GSP settings
            if (!enabled) {
                Log.d("CIS 3334", "GPS not enabled");
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            if (location!=null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            } else {
                Log.d("CIS 3334", "Cannot get location -- getLastKnownLocation() returned null");
            }
        }

        private void setupSaveButton() {
            // set up the button listener
            buttonEnter = (Button) findViewById(R.id.buttonEnter);
            buttonEnter.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    // Add the fish to the database
                    String location = editTextLocation.getText().toString();
                    String description = editTextDesc.getText().toString();
                    String time = editTextTime.getText().toString();
                    String date = editTextDate.getText().toString();
                    if (latitude==null || latitude==0.0) {
                        // add fish without location since location is missing
                        Log.d("CIS 3334", "AddFishActivity -- Saving fish with no location");
                        crimeDataSource.createCrime(date, time, location, description);

                    } /* else {
                        Log.d("CIS 3334", "AddFishActivity -- Saving fish with lat="+latitude.toString()+" and lon="+longitude.toString());
                        crimeDataSource.createCrime(date, time, location, description, latitude.toString(), longitude.toString());
                    }
                    */
                    finish();
                }
            });
        }

    }
