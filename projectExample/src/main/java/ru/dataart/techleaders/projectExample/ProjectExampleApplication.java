package ru.dataart.techleaders.projectExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dataart.techleaders.projectExample.dao.MovieDao;
import ru.dataart.techleaders.projectExample.dto.MovieDto;
import ru.dataart.techleaders.projectExample.services.MovieService;

@SpringBootApplication
public class ProjectExampleApplication implements CommandLineRunner {

	@Autowired
	MovieService movieService;

	@Autowired
	MovieDao movieDao;

	public static void main(String[] args) {
		SpringApplication.run(ProjectExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("-----run------");
//		movieDao.createTable();
//		MovieDto titanic = new MovieDto(1,"Titanic", "12.10.1999", "Usa", "J. Cameron", "Drama");
//		MovieDto dunkerk = new MovieDto(2,"Дюнкерк", "12.10.2018", "Usa", "K. Nolan", "Historic");
//		System.out.println(movieService.addMovie(titanic));
//		System.out.println(movieService.addMovie(dunkerk));
		System.out.println(movieService.getMovies());
	}
}
