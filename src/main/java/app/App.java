package app;

import dao.Employee;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

import java.util.Date;

public class App {
    static void main() {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee employee=new Employee(4,"Ali",true,new Date(),8000);
        employeeDao.save(employee);
    }
}
