package com.example.demo4.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity                                   //tym informyjemy baze danych że to jest nasza encja
@Table(name = "lista_skierowan")
public class SkierowanieDoLekarza {

    @Id          //nasz primary key w bazie
    @GeneratedValue
    @Column
    int id;

    @Column(nullable = false)
    String lekarz;

    @Column(nullable = false)
    String pacjent;

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
