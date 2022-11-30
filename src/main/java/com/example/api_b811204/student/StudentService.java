package com.example.api_b811204.student;

import com.example.api_b811204.model.PutStudentReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("students")
public class StudentService {
    private final StudentDao studentDao;


    @PostConstruct
    public void crawlAndPut() {
        StudentCrawler C = new StudentCrawler("https://apl.hongik.ac.kr/lecture/dbms", ".n8H08c.UVNKR");
        List<String> result = C.getList().stream().map(student -> this.putStudent(student)).collect(Collectors.toList());
        System.out.println("student initialized");
    };


    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public String getStudentDegree(String name) {
        List<String> degreeList = studentDao.selectStudentsDegreeByName(name);

        if (degreeList.size() > 1) {
            return "There are multiple students with the same name. Please provide an email address instead.";
        } else if (degreeList.size() == 0) {
            return "No such student";
        } else {
            return name + " : " + degreeList.get(0);
        }
    }

    public String getStudentEmail(String name) {
        List<String> emailList = studentDao.selectStudentsEmailByName(name);

        if (emailList.size() > 1) {
            return "There are multiple students with the same name. Please contact the administrator by phone.";
        } else if (emailList.size() == 0) {
            return "No such student";
        } else {
            return name + " : " + emailList.get(0);
        }
    }

    public String getDegreeCount(String degree) {
        List<String> count = studentDao.selectStudentsCountByDegree(degree);
            return  "Number of "+ degree+"'s student : " + count.get(0);

    }

    public String putStudent(PutStudentReq data) {
        Object[] checkStudentsExistParams = {
                data.getName(),
                 data.getEmail()
        };

        Object[] createStudentParams = {
                data.getName(), data.getEmail(), data.getGraduation(), data.getDegree()
        };
        List<String> selected = studentDao.checkStudentsExist(checkStudentsExistParams);
        if (selected.size() > 0) {
            return "Already registered";
        } else {
            int result = studentDao.createStudent(createStudentParams);
            System.out.println(result);
            return "Registration successful";
        }
    }
}
