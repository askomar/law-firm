package com.solvd.lawfirm;

public class ClientFolder {

    private long id;
    private ClientFolderStatus status;
    private Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
