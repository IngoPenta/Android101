package pentasys.de.employeequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;


public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity" ;
    public static final int PLAY_DIAG_REQUEST_CODE = 1;
    private CheckBox switchLocalCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button listButton= (Button) findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEmployeeList(v);
            }
        });

        switchLocalCheckBox = (CheckBox) findViewById(R.id.switchLocalCheckBox);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int available = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(available== ConnectionResult.SUCCESS){
            switchLocalCheckBox.setVisibility(View.VISIBLE);
        }else{
            switchLocalCheckBox.setVisibility(View.INVISIBLE);
            switchLocalCheckBox.setChecked(false);
            GooglePlayServicesUtil.getErrorDialog(available,this, PLAY_DIAG_REQUEST_CODE).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onEmployeeQuiz(View view){
        Log.i(TAG,"onEmployeeQuiz");
        Intent intent = new Intent(this,QuizActivity.class);
        startActivity(intent);
    }

    public void onEmployeeList(View view){
        Log.i(TAG,"onEmployeeList");
        Intent intent = new Intent(this,EmployeeListActivity.class);
        intent.putExtra(EmployeeListActivity.EXTRA_KEY_LOCAL_ONLY,switchLocalCheckBox.isChecked());
        startActivity(intent);
    }

}
