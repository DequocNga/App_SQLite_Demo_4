package com.russia.app_sqlite_demo_4.model;

/**
 * Created by VLADIMIR PUTIN on 3/3/2018.
 */

public class Student {
    private int idSV;
    private String nameSV;
    private String phoneSV;
    private String emailSV;
    private String addressSV;

    public Student() {
    }

    public Student(int idSV, String nameSV, String phoneSV, String emailSV, String addressSV) {
        this.idSV = idSV;
        this.nameSV = nameSV;
        this.phoneSV = phoneSV;
        this.emailSV = emailSV;
        this.addressSV = addressSV;
    }

    public Student(String nameSV, String phoneSV, String emailSV, String addressSV) {
        this.nameSV = nameSV;
        this.phoneSV = phoneSV;
        this.emailSV = emailSV;
        this.addressSV = addressSV;
    }

    public int getIdSV() {
        return idSV;
    }

    public void setIdSV(int idSV) {
        this.idSV = idSV;
    }

    public String getNameSV() {
        return nameSV;
    }

    public void setNameSV(String nameSV) {
        this.nameSV = nameSV;
    }

    public String getPhoneSV() {
        return phoneSV;
    }

    public void setPhoneSV(String phoneSV) {
        this.phoneSV = phoneSV;
    }

    public String getEmailSV() {
        return emailSV;
    }

    public void setEmailSV(String emailSV) {
        this.emailSV = emailSV;
    }

    public String getAddressSV() {
        return addressSV;
    }

    public void setAddressSV(String addressSV) {
        this.addressSV = addressSV;
    }
}
