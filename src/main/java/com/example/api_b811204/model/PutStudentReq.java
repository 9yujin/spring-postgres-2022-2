package com.example.api_b811204.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PutStudentReq {
    private String name;
    private String email;
    private Integer graduation;
    private String degree;
}
