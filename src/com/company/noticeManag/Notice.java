package com.company.noticeManag;

public class Notice {
    private String link;
    private String intro;
    private String date;
    public Notice(String link,String intro,String date){
        this.link = link;
        this.intro = intro;
        this.date = date;
    }

    public String getIntro() {
        return intro;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
