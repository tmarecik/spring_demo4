package com.example.demo4.dao;

import com.example.demo4.model.SkierowanieDoLekarza;
import org.graalvm.compiler.lir.LIR;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository                                       //trzeci stereotyp @REepository i @Konponent są NIE wymienialne!!!  @Repository nad interface'm
public interface SkierowanieDoLekarzaDao extends CrudRepository<SkierowanieDoLekarza, Integer> {

    @Override
    List<SkierowanieDoLekarza> findAll();

    @Override
    List<SkierowanieDoLekarza> findAllById(Iterable<Integer> integers);


}
