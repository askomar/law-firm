package com.solvd.lawfirm.domain;

import java.math.BigDecimal;

public class Service {

    private Long id;
    private Lawyer lawyer;
    private ServiceType type;
    private Paperwork paperWork;
    private BigDecimal cost;

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

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public Paperwork getPaperWork() {
        return paperWork;
    }

    public void setPaperWork(Paperwork paperWork) {
        this.paperWork = paperWork;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
