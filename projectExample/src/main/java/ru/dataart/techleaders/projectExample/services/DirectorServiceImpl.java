package ru.dataart.techleaders.projectExample.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dataart.techleaders.projectExample.dao.DirectorDao;
import ru.dataart.techleaders.projectExample.dto.DirectorDto;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorDao directorDao;

    @Override
    public void addDirector(DirectorDto directorDto) {
        directorDao.addDirector(directorDto);
    }

    @Override
    public String findDirectorById(Integer id) {
        return directorDao.findDirectorById(id);
    }

    @Override
    public Integer findDirectorByName(String name) {
        return directorDao.findDirectorByName(name);
    }
}
