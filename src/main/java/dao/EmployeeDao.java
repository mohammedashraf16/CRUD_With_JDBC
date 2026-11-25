package dao;

import model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findAllEmployee();

    Employee findBYID(int id);

    void save(Employee employee);

    void deleteById(int id);
}
