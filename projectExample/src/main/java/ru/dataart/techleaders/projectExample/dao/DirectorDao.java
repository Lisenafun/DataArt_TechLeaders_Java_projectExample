package ru.dataart.techleaders.projectExample.dao;

import ru.dataart.techleaders.projectExample.dto.DirectorDto;

public interface DirectorDao {

    Integer findByName(String name);
    String findById(Integer id);
    void addDirector(DirectorDto directorDto);
}
