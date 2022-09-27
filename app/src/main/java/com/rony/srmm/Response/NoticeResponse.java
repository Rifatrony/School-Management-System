package com.rony.srmm.Response;

import java.util.ArrayList;
import java.util.Date;

public class NoticeResponse {

    public boolean success;
    public String message;
    public ArrayList<Notice> notice;

    public NoticeResponse(boolean success, String message, ArrayList<Notice> notice) {
        this.success = success;
        this.message = message;
        this.notice = notice;
    }

    public class Notice{
        public String _id;
        public String noticeTitle;
        public String noticeDate;
        public String noticeDescription;
        public Date createdOn;
        public int __v;
    }

}
