package pentasys.de.employeequiz;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import pentasys.de.employeequiz.model.Employee;
import pentasys.de.employeequiz.model.EmployeeDAO;


public class EmployeeListActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_LOCAL_ONLY = "localOnly";
    public static final String TAG = "EmployeeListActivity";
    private ListView employeeListView;
    private boolean localOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        localOnly = getIntent().getBooleanExtra(EXTRA_KEY_LOCAL_ONLY, false);
        Log.i(TAG,"LocalOnly="+localOnly);
        employeeListView= (ListView) findViewById(R.id.employeeListView);

        initListData();

    }

    private void initListData() {
        EmployeeDAO employeeDAO=new EmployeeDAO(this);
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
}
