package pentasys.de.employeequiz;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import pentasys.de.employeequiz.model.Employee;
import pentasys.de.employeequiz.model.EmployeeDAO;


public class QuizActivity extends AppCompatActivity {

    private List<Employee> employees;
    private Random random=new Random(System.currentTimeMillis());

    private Employee employeeToBeGuessed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        EmployeeDAO employeeDAO=new EmployeeDAO(this);
        employees=employeeDAO.readEmployees();

        employeeToBeGuessed=determinRandomEmployee();

        ImageView employeeImage= (ImageView) findViewById(R.id.employeeImage);
        employeeImage.setImageBitmap(employeeDAO.readImage(employeeToBeGuessed));

        labelButtons();
    }

    public void onHandleGuess(View view){
        Employee employee= (Employee) view.getTag();
        if(employee.equals(employeeToBeGuessed)){
            Toast.makeText(this,getString(R.string.guess_right_text),Toast.LENGTH_LONG).show();
            this.recreate();
        }else{
            Toast.makeText(this,getString(R.string.guess_wrong_text),Toast.LENGTH_LONG).show();
        }

    }

    private void labelButtons() {
        ViewGroup buttonsContainer= (ViewGroup) findViewById(R.id.guessButtonsLayout);
        List<Employee> randomEmployees=createListOfRandomEmployees();
        for(int i=0;i<buttonsContainer.getChildCount();i++){
            Button curButton= (Button) buttonsContainer.getChildAt(i);
            Employee employee = randomEmployees.get(i);
            curButton.setText(employee.getFirstName()+" "+employee.getLastName());
            curButton.setTag(employee);
        }
    }

    private List<Employee> createListOfRandomEmployees(){
        Set<Employee> randomEmployees=new HashSet<>();
        randomEmployees.add(employeeToBeGuessed);
        while(randomEmployees.size()!=4){
            randomEmployees.add(determinRandomEmployee());
        }

        List<Employee> employeesList=new ArrayList<>(randomEmployees);
        Collections.shuffle(employeesList);
        return employeesList;

    }

    private Employee determinRandomEmployee() {
        int randomIndex = random.nextInt(employees.size());
        return employees.get(randomIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
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