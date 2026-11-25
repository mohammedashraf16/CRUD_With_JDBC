package app;

import dao.Employee;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

import java.util.Date;

public class App {
    static void main() {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee employee=new Employee(0,"mohammed",true,new Date(),5000d);
        employeeDao.save(employee);
    }
}
