package com.solvd.lawfirm;

import java.time.LocalDate;

public class Judge extends Person {

    private long id;
    private LocalDate experienceSince;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getExperienceSince() {
        return experienceSince;
    }

    public void setExperienceSince(LocalDate experienceSince) {
        this.experienceSince = experienceSince;
    }
}
