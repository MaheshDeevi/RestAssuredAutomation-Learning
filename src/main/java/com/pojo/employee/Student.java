package com.pojo.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@Getter
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
@ToString
@Builder
public class Student {

    private int id;
    private String fname;
    private String lname;
    private String email;

    //using static inner class
    public static class StudentBuilder1{
        private int id;
        private String fname;
        private String lname;
        private String email;

        public static StudentBuilder1 builder(){
            return new StudentBuilder1();
        }

        public StudentBuilder1 setId(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder1 setFname(String fname) {
            this.fname = fname;
            return this;
        }

        public StudentBuilder1 setLname(String lname) {
            this.lname = lname;
            return this;
        }

        public StudentBuilder1 setEmail(String email) {
            this.email = email;
            return this;
        }
        public Student build(){
            return new Student(this.id,this.fname,this.lname,this.email);
        }


    }
}
