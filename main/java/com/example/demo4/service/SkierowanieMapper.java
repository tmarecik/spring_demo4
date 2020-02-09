package com.example.demo4.service;

import com.example.demo4.DTO.SkierowanieDoLekarzaDTO;
import com.example.demo4.model.SkierowanieDoLekarza;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")      //dzieki temu spring sam utworzy z tego bin!!
public interface SkierowanieMapper {
    //mapstrauct pozwala przemapować oiek A na obiekt B
    //mapstruct sam przemapuje ten interface!!!

    SkierowanieDoLekarzaDTO toDTO (SkierowanieDoLekarza skierowanieDoLekarza);

    SkierowanieDoLekarza toDB  (SkierowanieDoLekarza skierowanieDoLekarzaDTO);

    List<SkierowanieDoLekarzaDTO> toDTO(List<SkierowanieDoLekarza> skierowanieDoLekarzaList);

}
