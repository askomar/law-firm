package com.solvd.lawfirm;

import java.time.LocalDate;
import java.util.List;

public class Lawyer extends Person {

    private long id;
    private List<LawyerActivitySphere> activitySpheres;
    private LawOffice lawOffice;
    private LocalDate experienceSince;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
