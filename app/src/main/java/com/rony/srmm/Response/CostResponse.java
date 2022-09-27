package com.rony.srmm.Response;

import java.util.Date;

public class CostResponse {

    public String id;
    public int amount;
    public Date date;
    public String year;
    public String today;
    public String reason;

    public CostResponse(String id, int amount, Date date, String year, String today, String reason) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.year = year;
        this.today = today;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
