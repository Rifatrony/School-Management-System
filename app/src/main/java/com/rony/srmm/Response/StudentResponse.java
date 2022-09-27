package com.rony.srmm.Response;

import java.util.ArrayList;
import java.util.Date;

public class StudentResponse {


    public ArrayList<Student> student;

    public StudentResponse(ArrayList<Student> student) {
        this.student = student;
    }

    public ArrayList<Student> getStudent() {
        return student;
    }

    public void setStudent(ArrayList<Student> student) {
        this.student = student;
    }

    public class Student{
        public String _id;
        public String id;
        public int roll;
        public String name;
        public String student_class;
        public String myclass;
        public String department;
        public String gender;
        public int age;
        public String dob;
        public Object birth_certificate;
        public String father_name;
        public String father_profession;
        public int father_number;
        public Object fathers_nid;
        public String mother_name;
        public String mother_profession;
        public String persent_address;
        public String permanent_address;
        public String year;
        public String today;
        public Date createdOn;
        public boolean status;
        public String _ct;
        public String _ac;
        public int __v;
        public String password;
    }

}
