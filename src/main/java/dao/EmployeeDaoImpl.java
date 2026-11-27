package dao;

import model.Employee;
import util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<Employee> findAllEmployee() {
        Connection connection = DBConnection.getConnection();
        if (connection == null) {
            return null;
        }
        List<Employee> employeeList = new LinkedList<>();
        String query = "SELECT * FROM employee;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .gender(resultSet.getBoolean("gender"))
                        .birthDate(resultSet.getDate("birthDate"))
                        .salary(resultSet.getDouble("salary")).build();
                employeeList.add(employee);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return employeeList;
    }

    @Override
    public Employee findBYID(int id) {
        Connection connection = DBConnection.getConnection();
        if (connection == null) {
            return null;
        }

        String query = "SELECT * FROM employee WHERE id =?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return  Employee.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .gender(resultSet.getBoolean("gender"))
                        .birthDate(resultSet.getDate("birthDate"))
                        .salary(resultSet.getDouble("salary")).build();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.fillInStackTrace();
            }
        }
        return null;
    }

    @Override
    public void save(Employee employee) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return;
        }
        if (employee.getId() > 1) {
            String query = "UPDATE Employee set name=?, gender=?, birthDate=?, salary=? WHERE id=?;";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);) {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setBoolean(2, employee.isGender());
                preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirthDate()));
                preparedStatement.setDouble(4, employee.getSalary());
                preparedStatement.setInt(5, employee.getId());

                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                sqlException.fillInStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.fillInStackTrace();
                }
            }
        } else {
            String query = "Insert Into Employee(name,gender,birthDate,salary) Values(?,?,?,?);";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);) {
                preparedStatement.setString(1, employee.getName());
                preparedStatement.setBoolean(2, employee.isGender());
                preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirthDate()));
                preparedStatement.setDouble(4, employee.getSalary());

                preparedStatement.executeUpdate();
            } catch (SQLException sqlException) {
                sqlException.fillInStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.fillInStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteById(int id) {
        Connection connection =DBConnection.getConnection();
        if (connection==null){
            return;
        }
        String query ="DELETE From employee WHERE id=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
