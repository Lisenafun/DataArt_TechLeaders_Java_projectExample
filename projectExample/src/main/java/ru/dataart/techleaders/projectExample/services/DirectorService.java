package ru.dataart.techleaders.projectExample.services;

import ru.dataart.techleaders.projectExample.dto.DirectorDto;

public interface DirectorService {

    void addDirector(DirectorDto directorDto);

    String findDirectorById(Integer id);

    Integer findDirectorByName(String name);
}
