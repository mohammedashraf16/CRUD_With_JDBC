package dao;

import util.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{
    @Override
    public List<Employee> findAllEmployee() {
        return List.of();
    }

    @Override
    public Employee findBYID(int id) {
        return null;
    }

    @Override
    public void save(Employee employee) {
        Connection con = DBConnection.getConnection();
        if (con==null) {
            return;
        }
        if(employee.getId()>1){
            String query = "UPDATE Employee set name=?, gender=?, birthDate=?, salary=? WHERE id=?;";
            try(PreparedStatement preparedStatement = con.prepareStatement(query);){
                preparedStatement.setString(1,employee.getName());
                preparedStatement.setBoolean(2,employee.isGender());
                preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirthDate()));
                preparedStatement.setDouble(4,employee.getSalary());
                preparedStatement.setInt(5,employee.getId());

                preparedStatement.executeUpdate();
            }catch (SQLException sqlException){
                sqlException.fillInStackTrace();
            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.fillInStackTrace();
                }
            }
        }else {
            String query = "Insert Into Employee(name,gender,birthDate,salary) Values(?,?,?,?);";
            try(PreparedStatement preparedStatement = con.prepareStatement(query);){
                preparedStatement.setString(1,employee.getName());
                preparedStatement.setBoolean(2,employee.isGender());
                preparedStatement.setDate(3, Utils.getSqlDate(employee.getBirthDate()));
                preparedStatement.setDouble(4,employee.getSalary());

                preparedStatement.executeUpdate();
            }catch (SQLException sqlException){
                sqlException.fillInStackTrace();
            }finally {
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

    }
}
