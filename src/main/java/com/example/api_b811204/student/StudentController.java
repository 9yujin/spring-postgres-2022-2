package com.example.api_b811204.student;

import com.example.api_b811204.model.GetStudentRes;
import com.example.api_b811204.model.PutStudentReq;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * 1. 특정 이름을 가진 학생의 학위 유형 질의
     * [GET] /students/degree?name=
     * @return <name> : <degree>
     */
    //Query String
    @ResponseBody
    @GetMapping("/degree") // (GET) 127.0.0.1:8080/students/degree
    public String getStudentDegree(@RequestParam(required = true) String name) {

            // Get StudentDegree By Name
            String getStudentDegreeRes = studentService.getStudentDegree(name);
            return getStudentDegreeRes;

    }

    /**
     * 2. 특정 이름을 가진 학생의 이메일 질의
     * [GET] /students/email?name=
     * @return <name> : <degree>
     */
    //Query String
    @ResponseBody
    @GetMapping("/email") // (GET) 127.0.0.1:8080/students/email
    public String getStudentEmail(@RequestParam(required = true) String name) {

        // Get StudentDegree By Name
        String getStudentEmailRes = studentService.getStudentEmail(name);
        return getStudentEmailRes;

    }

    /**
     * 3. 학위별 학생의 수 반환
     * [GET] /students/stat?degree=
     * @return <degree> : <count>
     */
    //Query String
    @ResponseBody
    @GetMapping("/stat") // (GET) 127.0.0.1:8080/students/stat
    public String getDegreeCount(@RequestParam(required = true) String degree) {

        // Get StudentDegree By Name
        String getDegreeCountRes = studentService.getDegreeCount(degree);
        return getDegreeCountRes;
    }

    /**
     * 4. 신규 학생 등록
     * [PUT] /students/register?name=&email=&degree=&graduation=
     * @return "Registration successful"
     */
    //Query String
    @ResponseBody
    @PutMapping("/register") // (GET) 127.0.0.1:8080/students/
    public String putStudent(@RequestParam(required = true) String name, @RequestParam(required = true) String email, @RequestParam(required = true) String degree, @RequestParam(required = true) int graduation) {
        PutStudentReq data = new PutStudentReq(name, email, graduation, degree);
        String putStudentRes = studentService.putStudent(data);
        return putStudentRes;

    }
}
