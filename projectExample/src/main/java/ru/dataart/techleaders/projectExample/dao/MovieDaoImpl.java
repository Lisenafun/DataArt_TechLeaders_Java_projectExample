package ru.dataart.techleaders.projectExample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dataart.techleaders.projectExample.dto.MovieDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {

    @Autowired
    private Authorization authorization;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private DirectorDao directorDao;

    @Override
    public void createTable(){
        String sql =
//        "CREATE TABLE IF NOT EXISTS directors ("
//                + " id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,"
//                + " name VARCHAR(50) NOT NULL"
//                + "); " +
//                        "CREATE TABLE IF NOT EXISTS movies ("
//                + "  id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,"
//                + "  name VARCHAR(50) NOT NULL,"
//                + "  release_date VARCHAR(50),"
//                + "  country VARCHAR(250),"
//                + "  director_id INT NOT NULL,"
//                + "  FOREIGN KEY (director_id) REFERENCES directors(id)"
//                + "); " +
//                "CREATE TABLE IF NOT EXISTS genres ("
//                + "  id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,"
//                + "  name VARCHAR(100) NOT NULL" + "); " +
                "CREATE TABLE IF NOT EXISTS genre_of_movie ("
                + "  movie_id INT NOT NULL,"
                + "  genre_id INT NOT NULL,"
                + "  PRIMARY KEY (movie_id, genre_id),"
                + "  FOREIGN KEY (movie_id) REFERENCES movies(id),"
                + "  FOREIGN KEY (genre_id) REFERENCES genres(id)"
                + ");"
                ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())){
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            System.out.println(row);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void addMovie(MovieDto movieDto) {
        String insertMovie = "INSERT INTO Movies (id, name, release_date, country, director_id)"
                + "VALUES (?, ?, ?, ?, ?);";
        String insertGenre = "INSERT INTO Genre_of_movie(movie_id, genre_id)" + "VALUES(?, ?)";
        try (Connection connection = DriverManager.getConnection (authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement statementMovie =
                    connection.prepareStatement (insertMovie);
            statementMovie.setInt(1, movieDto.getId());
            statementMovie.setString(2, movieDto.getName());
            statementMovie.setString(3, movieDto.getReleaseDate());
            statementMovie.setString(4, movieDto.getCountry());
            Integer directorId = directorDao.findByName(movieDto.getDirector());
            statementMovie.setInt(5, directorId);
            statementMovie.executeUpdate();
            PreparedStatement statementGenre = connection.prepareStatement(insertGenre);
            movieDto.getGenres().forEach(genre -> {
                try {
                    statementGenre.setInt(1, movieDto.getId());

                statementGenre.setInt(2,genreDao.findByName(genre));
                statementGenre.executeUpdate();
                } catch(SQLException throwables) {
                throwables.printStackTrace();
            }
            });
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    @Override
    public List<MovieDto> findAll() {
        String sql = "SELECT * FROM Movies;";
        List<MovieDto> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                MovieDto movieDto = new MovieDto();
                movieDto.setId(resultSet.getInt("id"));
                movieDto.setName(resultSet.getString("name"));
                movieDto.setCountry(resultSet.getString("country"));
                movieDto.setReleaseDate(resultSet.getString("release_date"));
                Integer directorId = resultSet.getInt("director_id");
                String director = directorDao.findById(directorId);
                movieDto.setDirector(director);
                List<Integer> genreIdList = genreDao.findByMovieId(movieDto.getId());
                List<String> genres = new ArrayList<>();
                genreIdList.forEach(genre -> {
                    genres.add(genreDao.findByGenreId(genre));
                });
                movieDto.setGenres(genres);
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public MovieDto findById(Integer id) {
        String sql = "SELECT * FROM Movies WHERE id = " + id + " ;";
        MovieDto movieDto = new MovieDto();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                movieDto.setId(id);
                movieDto.setName(resultSet.getString("name"));
                movieDto.setCountry(resultSet.getString("country"));
                movieDto.setReleaseDate(resultSet.getString("release_date"));
                Integer directorId = resultSet.getInt("director_id");
                String director = directorDao.findById(directorId);
                movieDto.setDirector(director);
                List<Integer> genreIdList = genreDao.findByMovieId(movieDto.getId());
                List<String> genres = new ArrayList<>();
                genreIdList.forEach(genre -> {
                    genres.add(genreDao.findByGenreId(genre));
                });
                movieDto.setGenres(genres);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movieDto;
    }

    @Override
    public List<MovieDto> findByName(String name) {
        String sql = "SELECT * FROM Movies WHERE UPPER(name) LIKE ?;";
        List<MovieDto> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            name = name.toUpperCase();
            ps.setString(1, "%" + name + "%");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                MovieDto movieDto = new MovieDto();
                movieDto.setId(resultSet.getInt("id"));
                movieDto.setName(resultSet.getString("name"));
                movieDto.setCountry(resultSet.getString("country"));
                movieDto.setReleaseDate(resultSet.getString("release_date"));
                Integer directorId = resultSet.getInt("director_id");
                String director = directorDao.findById(directorId);
                movieDto.setDirector(director);
                List<Integer> genreIdList = genreDao.findByMovieId(movieDto.getId());
                List<String> genres = new ArrayList<>();
                genreIdList.forEach(genre -> {
                    genres.add(genreDao.findByGenreId(genre));
                });
                movieDto.setGenres(genres);
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<MovieDto> findByDirector(String director) {
        Integer directorId = directorDao.findByName(director);
        String sql = "SELECT * FROM Movies WHERE director_id = " + directorId + " ;";
        List<MovieDto> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            Statement ps = connection.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while(resultSet.next()){
                MovieDto movieDto = new MovieDto();
                movieDto.setId(resultSet.getInt("id"));
                movieDto.setName(resultSet.getString("name"));
                movieDto.setCountry(resultSet.getString("country"));
                movieDto.setReleaseDate(resultSet.getString("release_date"));
                String directorInBase = directorDao.findById(directorId);
                movieDto.setDirector(directorInBase);
                List<Integer> genreIdList = genreDao.findByMovieId(movieDto.getId());
                List<String> genres = new ArrayList<>();
                genreIdList.forEach(genre -> {
                    genres.add(genreDao.findByGenreId(genre));
                });
                movieDto.setGenres(genres);
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<MovieDto> findByGenre(String genre) {
        Integer genreId = genreDao.findByName(genre);
        String sql = "SELECT movie_id FROM Genre_of_movie WHERE genre_id = " + genreId + " ;";
        List<MovieDto> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            Statement ps = connection.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while(resultSet.next()){
                MovieDto movieDto = findById(resultSet.getInt("movie_id"));
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }
}
