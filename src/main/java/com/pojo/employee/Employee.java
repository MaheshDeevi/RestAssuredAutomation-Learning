package com.pojo.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(alphabetic = true)
@NoArgsConstructor
public class Employee {


    private int id;
    private String fname;
    private String lname;
    @JsonIgnore
    private String email;
    private List<String> jobs;
    private FavFood favFood;
}
