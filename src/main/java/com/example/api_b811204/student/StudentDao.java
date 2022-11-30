package com.example.api_b811204.student;

import com.example.api_b811204.model.GetStudentRes;
import com.example.api_b811204.model.PutStudentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class StudentDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) { this.jdbcTemplate = new JdbcTemplate(dataSource); }

    public List<String> selectStudentsDegreeByName(String name){
        String selectStudentsByNameQuery = "select degree from students where name = ?";
        String selectStudentsByNameParams = name;
        return this.jdbcTemplate.query(selectStudentsByNameQuery,
                (rs,rowNum) -> rs.getString("degree"), selectStudentsByNameParams
        );
    }

    public List<String> selectStudentsEmailByName(String name){
        String selectStudentsByNameQuery = "select email from students where name = ?";
        String selectStudentsByNameParams = name;
        return this.jdbcTemplate.query(selectStudentsByNameQuery,
                (rs,rowNum) -> rs.getString("email"), selectStudentsByNameParams
        );
    }

    public List<String> selectStudentsCountByDegree(String degree){
        String selectStudentsCountByDegreeQuery = "select COUNT(name) as count from students where degree = ?";
        String selectStudentsCountByDegreeParams = degree;
        return this.jdbcTemplate.query(selectStudentsCountByDegreeQuery,
                (rs,rowNum) -> rs.getString("count"), selectStudentsCountByDegreeParams
        );
    }

    public List<String> checkStudentsExist(Object[] checkStudentsExistParams) {
        String checkStudentExistQuery = "select name from students where name = ? or email = ?";
        return this.jdbcTemplate.query(checkStudentExistQuery, (rs, rowNum) -> rs.getString("name"), checkStudentsExistParams);
    }

    public int createStudent(Object[] createStudentParams) {
        String createStudentQuery = "INSERT INTO students (name, email, graduation, degree) VALUES (?,?,?,?)";
        int result = this.jdbcTemplate.update(createStudentQuery, createStudentParams);
        return result;
    }
}
