package com.Weekly.Test.weekly.test.repository;

import com.Weekly.Test.weekly.test.model.Employ;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class EmployeeRowMapper implements RowMapper<Employ> {
        @Override
        public Employ mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employ employee = new Employ();

            employee.setEmp_id(rs.getLong("emp_id"));
            employee.setName(rs.getString("name"));
            employee.setEmail(rs.getString("email"));
            employee.setPassword(rs.getString("password"));
            return employee;
        }
    }

    public void addEmployee(Employ employee) {
        String sql = "INSERT INTO employ (name, email, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getPassword());
    }

    public void updateEmployee(Employ employee) {
        String sql = "UPDATE employ SET name = ?, email = ?, password = ? WHERE emp_id = ?";
        jdbcTemplate.update(sql, employee.getName(), employee.getEmail(), employee.getPassword(), employee.getEmp_id());
    }


public Employ getEmployeeById(Long emp_id) {
    String sql = "SELECT * FROM employ WHERE emp_id = ?";
    List<Employ> employees = jdbcTemplate.query(sql, new EmployeeRowMapper(), emp_id);
    if (employees.isEmpty()) {
        return null;
    }
    return employees.get(0);
}


    public List<Employ> getAllEmployees() {
        String sql = "SELECT * FROM employ";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    public void deleteEmployee(Long emp_id) {
        String sql = "DELETE FROM employ WHERE emp_id = ?";
        jdbcTemplate.update(sql, emp_id);
    }



}

