package com.solvd.lawfirm;

public class Service {

    private long id;
    private Client client;
    private Lawyer lawyer;
    private ServiceType type;
    private Paperwork paperWork;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
}
