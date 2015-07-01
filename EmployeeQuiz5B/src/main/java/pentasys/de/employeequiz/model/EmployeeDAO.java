package pentasys.de.employeequiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by freitain on 21.06.2015.
 */
public class EmployeeDAO {

    public List<Employee> readEmployees(){
        List<Employee> employees = new ArrayList<>(3);

        employees.add(new Employee("Joshua","Bloch","joshua_bloch.jpg"));
        employees.add(new Employee("Robert","Martin","robert_martin.jpg"));
        employees.add(new Employee("Martin","Fowler","martin_fowler.jpg"));

        return employees;
    }
}
