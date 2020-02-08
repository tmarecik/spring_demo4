package com.example.demo4.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SkierowanieDoLekarza {

    int id;
    String lekarz;
    String pacjent;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    Date termin;

    public SkierowanieDoLekarza(int id, String lekarz, String pacjent, Date termin) {
        this.id = id;
        this.lekarz = lekarz;
        this.pacjent = pacjent;
        this.termin = termin;
    }

    public SkierowanieDoLekarza() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLekarz() {
        return lekarz;
    }

    public void setLekarz(String lekarz) {
        this.lekarz = lekarz;
    }

    public String getPacjent() {
        return pacjent;
    }

    public void setPacjent(String pacjent) {
        this.pacjent = pacjent;
    }

    public Date getTermin() {
        return termin;
    }

    public void setTermin(Date termin) {
        this.termin = termin;
    }
}
