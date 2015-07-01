package pentasys.de.employeequiz;

import android.app.ListActivity;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;

import pentasys.de.employeequiz.model.Employee;
import pentasys.de.employeequiz.model.EmployeeDAO;
import pentasys.de.employeequiz.model.Office;


public class EmployeeListActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener {

    public static final String EXTRA_KEY_LOCAL_ONLY = "localOnly";
    public static final String TAG = "EmployeeListActivity";
    public static final int THRESHOLD = 500;
    private ListView employeeListView;
    private EmployeeDAO employeeDAO;
    private boolean localOnly;
    private GoogleApiClient locationApiClient;
    public static final String LOCAL_ONLY = "local_only";

    private static Location LOCATION_MUNICH=new Location(LOCAL_ONLY);
    private static Location LOCATION_FRANKFURT =new Location(LOCAL_ONLY);
    private static Location LOCATION_STUTTGART=new Location(LOCAL_ONLY);
    static {
        LOCATION_MUNICH.setLongitude(48.132010);
        LOCATION_MUNICH.setLatitude(11.522531);

        LOCATION_FRANKFURT.setLongitude(50.118502);
        LOCATION_FRANKFURT.setLatitude(8.631459);

        LOCATION_STUTTGART.setLongitude(48.792883);
        LOCATION_STUTTGART.setLatitude(9.182044);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        localOnly = getIntent().getBooleanExtra(EXTRA_KEY_LOCAL_ONLY, false);
        Log.i(TAG,"LocalOnly="+localOnly);
        employeeListView= (ListView) findViewById(R.id.employeeListView);
        initListData();
        if(localOnly) {
            locationApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
            locationApiClient.connect();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(localOnly) {
            locationApiClient.connect();
        }
    }

    private void initListData() {
        employeeDAO= new EmployeeDAO(this);
        List<Employee> employees = employeeDAO.readEmployees();
        EmployeeAdapter adapter=new EmployeeAdapter(this,employees);
        employeeListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(locationApiClient);

        Office office = determineOffice(lastLocation);
        if(office!=null) {
            employeeListView.setAdapter(new EmployeeAdapter(this, employeeDAO.readEmployees(office)));
        }

    }

    private Office determineOffice(Location lastLocation) {
        Office office=null;
        if(lastLocation!=null){
            if(lastLocation.distanceTo(LOCATION_FRANKFURT)< THRESHOLD){
                office=Office.Frankfurt;
            }else if(lastLocation.distanceTo(LOCATION_MUNICH)< THRESHOLD) {
                    office=Office.Munich;
            }else if(lastLocation.distanceTo(LOCATION_STUTTGART)< THRESHOLD) {
                    office=Office.Stuttgart;
                }
            }
        return office;
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.w(TAG,"Connection to LocationService failed :"+connectionResult.toString());
    }
}
