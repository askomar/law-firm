package com.solvd.lawfirm.domain;

public class Paperwork {

    private Long id;
    private String title;
    private PaperWorkType type;
    private ClientFolder folder;
    private Court court;
    private Judge judge;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PaperWorkType getType() {
        return type;
    }

    public void setType(PaperWorkType type) {
        this.type = type;
    }

    public ClientFolder getFolder() {
        return folder;
    }

    public void setFolder(ClientFolder folder) {
        this.folder = folder;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
