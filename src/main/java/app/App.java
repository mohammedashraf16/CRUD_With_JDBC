package app;

import model.Employee;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;

import java.util.Date;

public class App {
    static void main() {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Employee employee = Employee.builder()
                .name("Mohammed")
                .gender(true)
                .birthDate(new Date())
                .salary(6000d)
                .build();
        employeeDao.save(employee);
        employeeDao.findAllEmployee().forEach(System.out::println);
        Employee emp = employeeDao.findBYID(4);
        employeeDao.deleteById(4);
        System.out.println("Done");
    }
}
