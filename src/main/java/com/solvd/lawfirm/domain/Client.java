package com.solvd.lawfirm.domain;

import java.time.LocalDate;

public class Client extends Person {

    private Long id;
    private String telephone;

    public Client() {
        super();
    }

    public Client(String surname, String name, String patronymic, LocalDate dob, String telephone) {
        super(surname, name, patronymic, dob);
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
