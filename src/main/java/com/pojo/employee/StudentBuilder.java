package com.pojo.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Setter;



public class StudentBuilder {

    private int id;
    private String fname;
    private String lname;
    private String email;

    public StudentBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public StudentBuilder setFname(String fname) {
        this.fname = fname;
        return this;
    }

    public StudentBuilder setLname(String lname) {
        this.lname = lname;
        return this;
    }

    public StudentBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public static StudentBuilder builder(){

        return new StudentBuilder();
    }

    public Student build(){
        return new Student(this.id,this.fname,this.lname,this.email);
    }
}
