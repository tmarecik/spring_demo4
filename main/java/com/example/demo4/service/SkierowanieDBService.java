package com.example.demo4.service;

import com.example.demo4.DTO.SkierowanieDoLekarzaDTO;
import com.example.demo4.controller.GeneralControllerAdvice;
import com.example.demo4.controller.TestControler;
import com.example.demo4.dao.SkierowanieDoLekarzaDao;
import com.example.demo4.model.SkierowanieDoLekarza;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Primary          // w przypadku konfliktu beanów adnowtacja Primary mówi króy bin ma być ważniejszy
@Service
public class SkierowanieDBService extends SkierowanieServices {

    SkierowanieDoLekarzaDao dao;
    SkierowanieMapper mapper;

    public SkierowanieDBService(SkierowanieDoLekarzaDao dao, SkierowanieMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }


    @Override
    public SkierowanieDoLekarzaDTO getSkierowanie(int id) {
        return dao.findById(id)
                .map(skierowanieDoLekarza -> mapper.toDTO(skierowanieDoLekarza))
                .orElseThrow(() -> new TestControler.NotFoundException());   //tutaj zastosowano strukture "optional"
    }

    @Override
    public SkierowanieDoLekarzaDTO createSkierowanie(String lekarz, String pacjent, Date termin) {
        SkierowanieDoLekarza skierowanieDoLekarza = new SkierowanieDoLekarza(0, lekarz, pacjent, termin);  // w tym przypatku bibloioteka widzi czy dane encja zostala wyciągnieta z bazy czy nie, jezeli nie to zapisujac od bazy nowej encji nada nowy id
        return mapper.toDTO(dao.save(
                mapper.toDB(skierowanieDoLekarza)));
    }

    @Override
    public void usunSkierowanie(int id) {
//        SkierowanieDoLekarza skierowanieDoLekarza = getSkierowanie(id);   //tym sprawdzamy czy skierowanie ktore chcemy usunąć istnieje!!
        dao.deleteById(getSkierowanie(id).getId());
    }

    @Override
    public Collection<SkierowanieDoLekarzaDTO> listSkierowanie() {
//        return dao.findAll();
        return mapper.toDTO(dao.findAll());
    }


    @Override
    public SkierowanieDoLekarzaDTO updateSkierowanie(SkierowanieDoLekarza skierowanie) {

//        SkierowanieDoLekarza skierowanieDoLekarza = getSkierowanie(skierowanie.getId());
        SkierowanieDoLekarza skierowanieDoLekarza = dao.findById(skierowanie.getId()).get();

        skierowanieDoLekarza.setLekarz(skierowanie.getLekarz());
        skierowanieDoLekarza.setPacjent(skierowanie.getPacjent());
        skierowanieDoLekarza.setTermin(skierowanie.getTermin());
        return dao.save(skierowanieDoLekarza);
    }
}
