package org.pjgg.flightSearch.model;


public class Airport {

    private String code;

    private String city;

    public Airport(String iatta_code, String city){
        this.code = iatta_code;
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
