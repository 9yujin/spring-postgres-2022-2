package com.example.api_b811204;

import com.example.api_b811204.student.StudentCrawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiB811204Application {

    public static void main(String[] args) {
        // StudentCrawler C = new StudentCrawler("https://apl.hongik.ac.kr/lecture/dbms", ".n8H08c.UVNKR");
        // C.getList();
        SpringApplication.run(ApiB811204Application.class, args);
    }


}
