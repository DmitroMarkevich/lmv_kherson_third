package com.example.lmv_kherson_third.entities;

public class StudentDto {
    private int studentId;
    private String name;
    private String surname;
    private String middleName;
    private String phoneNumber;
    private String email;
    private String faculty;
    private String specialty;
    private int groupId;

    public StudentDto(int studentId, String name, String surname,
                      String middleName, String phoneNumber, String email,
                      String faculty, String specialty, int groupId) {
        this.studentId = studentId;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.faculty = faculty;
        this.specialty = specialty;
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Студент №" + studentId + ", ім'я=" + name +
                ", прізвище=" + surname + ", група=" + groupId;
    }
}
