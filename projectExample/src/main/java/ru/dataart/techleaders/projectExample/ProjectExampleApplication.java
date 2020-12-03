package ru.dataart.techleaders.projectExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dataart.techleaders.projectExample.dao.DirectorDao;
import ru.dataart.techleaders.projectExample.dao.GenreDao;
import ru.dataart.techleaders.projectExample.dao.MovieDao;
import ru.dataart.techleaders.projectExample.dto.DirectorDto;
import ru.dataart.techleaders.projectExample.dto.GenreDto;
import ru.dataart.techleaders.projectExample.services.MovieService;

@SpringBootApplication
public class ProjectExampleApplication implements CommandLineRunner {

	@Autowired
	MovieDao movieDao;

	@Autowired
	GenreDao genreDao;

	@Autowired
	DirectorDao directorDao;

	public static void main(String[] args) {
		SpringApplication.run(ProjectExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-----run------");
//		movieDao.createTable();
//		directorDao.addDirector(new DirectorDto(1, "Никита Михалков"));
//		directorDao.addDirector(new DirectorDto(2, "Кристофер Нолан"));
//		directorDao.addDirector(new DirectorDto(3, "Питер Джексон"));
//		genreDao.addGenre(new GenreDto(1, "Исторический"));
//		genreDao.addGenre(new GenreDto(2, "Драма"));
//		genreDao.addGenre(new GenreDto(3, "Фантастика"));
//		genreDao.addGenre(new GenreDto(4, "Приключения"));
	}
}
