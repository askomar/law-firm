package com.solvd.lawfirm.domain;

public class Orientation {

    private Long id;
    private Lawyer lawyer;
    private LawyerActivitySphere lawyerActivitySphere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Lawyer getLawyer() {
        return lawyer;
    }

    public void setLawyer(Lawyer lawyer) {
        this.lawyer = lawyer;
    }

    public LawyerActivitySphere getLawyerActivitySphere() {
        return lawyerActivitySphere;
    }

    public void setLawyerActivitySphere(LawyerActivitySphere lawyerActivitySphere) {
        this.lawyerActivitySphere = lawyerActivitySphere;
    }
}
