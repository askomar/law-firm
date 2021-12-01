package com.solvd.lawfirm.domain;

public class ClientFolder {

    private Long id;
    private ClientFolderStatus status;
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientFolderStatus getStatus() {
        return status;
    }

    public void setStatus(ClientFolderStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
