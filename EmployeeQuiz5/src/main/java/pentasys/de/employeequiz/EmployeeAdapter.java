package pentasys.de.employeequiz;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import pentasys.de.employeequiz.model.Employee;

/**
 * Created by freitain on 21.06.2015.
 */
public class EmployeeAdapter extends ArrayAdapter<Employee> {

    public static final String TAG = "EmployeeAdapter";

    public EmployeeAdapter(Context context,List<Employee> employees) {
        super(context, R.layout.employee_list_row, employees);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.employee_list_row,parent,false);
        ImageView imageView= (ImageView) view.findViewById(R.id.listItemImage);
        TextView textView= (TextView) view.findViewById((R.id.listItemText));

        Employee employee=getItem(position);

        textView.setText(employee.getFirstName() + " " + employee.getLastName());

        imageView.setImageBitmap(readImage(employee));

        return view;
    }

    private Bitmap readImage(Employee employee) {
        Bitmap image = null;
        InputStream stream = null;
        try {
            AssetManager assetManager = getContext().getAssets();
            stream = assetManager.open(employee.getImageFileName());
            image = BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            Log.e(TAG, "Error reading image", e);
        }finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
        return image;
    }
}
