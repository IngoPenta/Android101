package pentasys.de.employeequiz;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import pentasys.de.employeequiz.model.Employee;

/**
 * Created by freitain on 21.06.2015.
 */
public class EmployeeAdapter extends ArrayAdapter<Employee> {

    public EmployeeAdapter(Context context,List<Employee> employees) {
        super(context, android.R.layout.simple_list_item_1,employees);
    }
}
