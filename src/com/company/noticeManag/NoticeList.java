package com.company.noticeManag;

import java.util.ArrayList;
import java.util.List;

public class NoticeList {
    private List<Notice> noticeList;
    public NoticeList(){
        noticeList = new ArrayList<>();
    }

    public List<Notice> getNoticeList() {
        return noticeList;
    }

    public void addNotice(Notice notice){
        this.noticeList.add(notice);
    }

    public void popNotice(){
        this.noticeList.remove(noticeList.size()-1);
    }

    public void clearNotice(){
        this.noticeList.clear();
    }

}
