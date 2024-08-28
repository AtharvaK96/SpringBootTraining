package h2JDBCTraining.JdbcTemplate.repository;

import h2JDBCTraining.JdbcTemplate.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAllEmployee(){
        String findAllEmployeeSql = "select * from employee";

        List<Employee> employees = jdbcTemplate.query(findAllEmployeeSql,
                (rs,rowCount)->new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
        return employees;
    }

    public Employee addEmployee(Employee employee){
        String addEmployeeSql = "insert into employee values (?,?,?,?)";
        int update = jdbcTemplate.update(addEmployeeSql, employee.getId(), employee.getName(), employee.getEmail(), employee.getCompany());
        System.out.println(update);
        return employee;
    }
     public Employee updateEmployee(Employee employee, int id){
        String updateEmployeeSql = "update employee set " +
                "name = ? , email = ? , company = ? " +
                "where id = ? ";
         int update = jdbcTemplate.update(updateEmployeeSql, employee.getName(), employee.getEmail(), employee.getCompany(), id);
         System.out.println("rows updated "+ update);
         if( update == 0){
             System.out.println("Employee with the id "+id +" does not exists");
         }
         return employee;


     }

     public String deleteEmployee(int id){
        String deleteEmployeeSql = "delete from employee where id=? ";
         int update = jdbcTemplate.update(deleteEmployeeSql, id);
         if(update > 0 ){
             return "Deleted Successfully";
         }
         return "Failed to delete";
     }

}
