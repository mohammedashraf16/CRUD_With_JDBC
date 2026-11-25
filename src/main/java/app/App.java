package app;

import model.Employee;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

import java.util.Date;

public class App {
    static void main() {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee employee=new Employee(4,"Ali",true,new Date(),8000);
        // employeeDao.save(employee);
        employeeDao.findAllEmployee().forEach(System.out::println);
        Employee emp = employeeDao.findBYID(4);
        employeeDao.deleteById(4);
        System.out.println("Done");
    }
}
