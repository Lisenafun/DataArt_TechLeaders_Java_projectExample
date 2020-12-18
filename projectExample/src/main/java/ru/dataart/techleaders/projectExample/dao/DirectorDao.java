package ru.dataart.techleaders.projectExample.dao;

import ru.dataart.techleaders.projectExample.dto.DirectorDto;

public interface DirectorDao {

    Integer findDirectorByName(String name);

    String findDirectorById(Integer id);

    void addDirector(DirectorDto directorDto);
}
