package com.solvd.lawfirm.domain;

import java.time.LocalDate;

public abstract class Person {

    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dob;

    public Person() {
    }

    public Person(String surname, String name, String patronymic, LocalDate dob) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dob = dob;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
