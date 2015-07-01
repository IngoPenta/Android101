package pentasys.de.employeequiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pentasys.de.employeequiz.model.Employee;
import pentasys.de.employeequiz.model.EmployeeDAO;

/**
 * Created by freitain on 21.06.2015.
 */
public class EmployeeAdapter extends ArrayAdapter<Employee> {

    public static final String TAG = "EmployeeAdapter";
    private final EmployeeDAO employeeDAO;

    public EmployeeAdapter(Context context,List<Employee> employees) {
        super(context, R.layout.employee_list_row, employees);
        employeeDAO=new EmployeeDAO(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        ViewHolder viewHolder=null;
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.employee_list_row,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imageView= (ImageView) view.findViewById(R.id.listItemImage);
            viewHolder.textView= (TextView) view.findViewById((R.id.listItemText));
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }

        Employee employee=getItem(position);
        viewHolder.textView.setText(employee.getFirstName() + " " + employee.getLastName());
        viewHolder.imageView.setImageBitmap(employeeDAO.readImage(employee));

        return view;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
