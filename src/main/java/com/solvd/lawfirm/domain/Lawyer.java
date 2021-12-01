package com.solvd.lawfirm.domain;

import java.time.LocalDate;
import java.util.List;

public class Lawyer extends Person {

    private Long id;
    private List<LawyerActivitySphere> activitySpheres;
    private LawOffice lawOffice;
    private LocalDate experienceSince;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LawyerActivitySphere> getActivitySpheres() {
        return activitySpheres;
    }

    public void setActivitySpheres(List<LawyerActivitySphere> activitySpheres) {
        this.activitySpheres = activitySpheres;
    }

    public LawOffice getLawOffice() {
        return lawOffice;
    }

    public void setLawOffice(LawOffice office) {
        lawOffice = office;
    }

    public LocalDate getExperienceSince() {
        return experienceSince;
    }

    public void setExperienceSince(LocalDate experienceSince) {
        this.experienceSince = experienceSince;
    }
}
