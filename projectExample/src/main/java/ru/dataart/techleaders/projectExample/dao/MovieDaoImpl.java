package ru.dataart.techleaders.projectExample.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dataart.techleaders.projectExample.dto.MovieDtoForDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieDaoImpl implements MovieDao {

    private final Authorization authorization;

    @Override
    public void createTable() {
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
                "CREATE TABLE IF NOT EXISTS genre_of_movie (" + "  movie_id INT NOT NULL," + "  genre_id INT NOT NULL," + "  PRIMARY KEY (movie_id, genre_id)," + "  FOREIGN KEY (movie_id) REFERENCES movies(id)," + "  FOREIGN KEY (genre_id) REFERENCES genres(id)" + ");";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            System.out.println(row);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addMovie(MovieDtoForDB movieDto) {
        String insertMovie = "INSERT INTO Movies (id, name, release_date, country, director_id)" + "VALUES (?, ?, ?, ?, ?);";
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement statementMovie = connection.prepareStatement(insertMovie);
            statementMovie.setInt(1, movieDto.getId());
            statementMovie.setString(2, movieDto.getName());
            statementMovie.setString(3, movieDto.getReleaseDate());
            statementMovie.setString(4, movieDto.getCountry());
            statementMovie.setInt(5, movieDto.getDirectorId());
            statementMovie.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MovieDtoForDB> findAllMovies() {
        String sql = "SELECT * FROM Movies;";
        List<MovieDtoForDB> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                MovieDtoForDB movieDto = getMovieDtoForDB(resultSet);
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public MovieDtoForDB findMovieById(Integer id) {
        String sql = "SELECT * FROM Movies WHERE id = ?;";
        MovieDtoForDB movieDto = new MovieDtoForDB();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                movieDto = getMovieDtoForDB(resultSet);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movieDto;
    }

    @Override
    public List<MovieDtoForDB> findMovieByName(String name) {
        String sql = "SELECT * FROM Movies WHERE UPPER(name) LIKE ?;";
        List<MovieDtoForDB> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            name = name.toUpperCase();
            ps.setString(1, "%" + name + "%");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                MovieDtoForDB movieDto = getMovieDtoForDB(resultSet);
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<MovieDtoForDB> findMovieByDirector(Integer directorId) {
        String sql = "SELECT * FROM Movies WHERE director_id = ?;";
        List<MovieDtoForDB> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, directorId);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                MovieDtoForDB movieDto = getMovieDtoForDB(resultSet);
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    @Override
    public List<MovieDtoForDB> findMovieByGenre(Integer genreId) {
        String sql = "SELECT movie_id FROM Genre_of_movie WHERE genre_id = ?;";
        List<MovieDtoForDB> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, genreId);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                MovieDtoForDB movieDto = findMovieById(resultSet.getInt("movie_id"));
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    private MovieDtoForDB getMovieDtoForDB(ResultSet resultSet) throws SQLException {
        MovieDtoForDB movieDto = new MovieDtoForDB();
        movieDto.setId(resultSet.getInt("id"));
        movieDto.setName(resultSet.getString("name"));
        movieDto.setCountry(resultSet.getString("country"));
        movieDto.setReleaseDate(resultSet.getString("release_date"));
        movieDto.setDirectorId(resultSet.getInt("director_id"));
        return movieDto;
    }
}
