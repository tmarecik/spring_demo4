package com.example.demo4.service;


import com.example.demo4.model.SkierowanieDoLekarza;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Collection;

@Service
public class SkierowanieServices {

    private Map<Integer, SkierowanieDoLekarza> sklMap = new HashMap<>();

    public SkierowanieDoLekarza createSkierowanie(String lekarz, String pacjent, Date termin){
        int id = new Random().nextInt();
        SkierowanieDoLekarza skl = new SkierowanieDoLekarza(id, lekarz, pacjent, termin);
        sklMap.put(id, skl);
        return skl;
    }

    public void usunSkierowanie(int id){
        sklMap.remove(id);
    }

    public Collection<SkierowanieDoLekarza> listSkierowanie(){
        return sklMap.values();
    }

    public SkierowanieDoLekarza getSkierowanie(int id){
        return sklMap.get(id);
    }

}
