package pentasys.de.employeequiz.model;

/**
 * Created by freitain on 21.06.2015.
 */
public class Employee {
    private String firstName;
    private String lastName;
    private String imageFileName;

    public Employee(String firstName, String lastName, String imageFileName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageFileName = imageFileName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
