package pentasys.de.employeequiz.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pentasys.de.employeequiz.EmployeeAdapter;

/**
 * Created by freitain on 21.06.2015.
 */
public class EmployeeDAO {

    public static final String TAG = "EmployeeDAO";
    private Context context;

    public EmployeeDAO(Context context) {
        this.context = context;
    }

    public List<Employee> readEmployees(){
        List<Employee> employees = new ArrayList<>(3);

        InputStreamReader reader=null;
        AssetManager assetManager=context.getAssets();
        try {
            InputStream stream = assetManager.open("employees.json");
            reader = new InputStreamReader(stream);
            Gson gson=new Gson();
            Employee[] allEmployees= gson.fromJson(reader, Employee[].class);

            Collections.addAll(employees, allEmployees);

        } catch (IOException e) {
            Log.e(TAG,"Error reading JSON",e);
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG,"Error closing stream",e);
                }
            }
        }

        return employees;
    }

    public Bitmap readImage(Employee employee) {
        Bitmap image = null;
        InputStream stream = null;
        try {
            AssetManager assetManager = context.getAssets();
            stream = assetManager.open(employee.getImageFileName());
            image = BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            Log.e(EmployeeAdapter.TAG, "Error reading image", e);
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
