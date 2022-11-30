package com.example.api_b811204.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStudentRes {
    private int sid;
    private String name;
    private String email;
    private String degree;
    private int graduation;
}