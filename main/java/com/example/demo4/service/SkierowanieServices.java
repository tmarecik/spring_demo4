package com.example.demo4.service;


import com.example.demo4.DTO.SkierowanieDoLekarzaDTO;
import com.example.demo4.model.SkierowanieDoLekarza;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SkierowanieServices {
    static AtomicInteger atomicInteger = new AtomicInteger();

    private Map<Integer, SkierowanieDoLekarza> sklMap = new HashMap<>();

    public SkierowanieDoLekarzaDTO createSkierowanie(String lekarz, String pacjent, Date termin){
//        int id = new Random().nextInt();
        int id = atomicInteger.getAndIncrement();
        SkierowanieDoLekarza skl = new SkierowanieDoLekarza(id, lekarz, pacjent, termin);
        sklMap.put(id, skl);
        return skl;
    }

    public void usunSkierowanie(int id){
        sklMap.remove(id);
    }

    public Collection<SkierowanieDoLekarzaDTO> listSkierowanie(){
        return sklMap.values();
    }

    public SkierowanieDoLekarzaDTO getSkierowanie(int id){
        return sklMap.get(id);
    }

    public SkierowanieDoLekarzaDTO updateSkierowanie(SkierowanieDoLekarzaDTO skierowanieDoLekarza){
        SkierowanieDoLekarza existing = getSkierowanie(skierowanieDoLekarza.getId());
        existing.setLekarz(skierowanieDoLekarza.getLekarz());
        existing.setPacjent(skierowanieDoLekarza.getPacjent());
        existing.setTermin(skierowanieDoLekarza.getTermin());
        sklMap.put(existing.getId(), existing);
        return existing;
    }
}
