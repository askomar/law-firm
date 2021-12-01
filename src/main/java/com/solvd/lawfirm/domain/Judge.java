package com.solvd.lawfirm.domain;

import java.time.LocalDate;

public class Judge extends Person {

    private Long id;
    private LocalDate experienceSince;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getExperienceSince() {
        return experienceSince;
    }

    public void setExperienceSince(LocalDate experienceSince) {
        this.experienceSince = experienceSince;
    }
}
