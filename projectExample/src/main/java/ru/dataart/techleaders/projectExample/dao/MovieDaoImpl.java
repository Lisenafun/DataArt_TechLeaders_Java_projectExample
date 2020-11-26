package ru.dataart.techleaders.projectExample.dao;

import org.springframework.stereotype.Repository;
import ru.dataart.techleaders.projectExample.dto.MovieDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {

    private static final String url = "jdbc:mysql://localhost:3306/movies_db?useUnicode=true&serverTimezone=UTC&characterEncoding=utf8";
    private static final String user = "root";
    private static final String password = "123";

    @Override
    public void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS Movies (" +
                "id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL," +
                "name VARCHAR(100) NOT NULL," +
                "release_date VARCHAR(100)," +
                "country VARCHAR(100)," +
                "director VARCHAR(100)," +
                "genre VARCHAR(100)" +
                ");";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            Statement statement = connection.createStatement();
            int row = statement.executeUpdate(sql);
            System.out.println(row);
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }

    @Override
    public Integer addMovie(MovieDto movieDto) {
        String insert = "INSERT INTO Movies (id, name, release_date, country, director, genre)" + "VALUES (?, ?, ?, ?, ?, ?);";
        Integer row = null;
        try (Connection connection = DriverManager.getConnection (url, user, password)) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement (insert);
            preparedStatement.setInt(1, movieDto.getId());
            preparedStatement.setString(2, movieDto.getName());
            preparedStatement.setString(3, movieDto.getReleaseDate());
            preparedStatement.setString(4, movieDto.getCountry());
            preparedStatement.setString(5, movieDto.getDirector());
            preparedStatement.setString(6, movieDto.getGenre());
            row = preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return row;
    }

    @Override
    public List<MovieDto> getMovies() {
        String sql = "SELECT * FROM Movies;";
        List<MovieDto> movies = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                MovieDto movieDto = new MovieDto();
                movieDto.setId(resultSet.getInt("id"));
                movieDto.setName(resultSet.getString("name"));
                movieDto.setGenre(resultSet.getString("genre"));
                movieDto.setCountry(resultSet.getString("country"));
                movieDto.setReleaseDate(resultSet.getString("release_date"));
                movieDto.setDirector(resultSet.getString("director"));
                movies.add(movieDto);
            }
        } catch(SQLException ex) {
            ex.getStackTrace();
        }
        return movies;
    }

//    @Override
//    public List<MovieDto> getMovies() {
//        return new ArrayList<>(){{
//            add(new MovieDto(){{
//                setName("Titanic");
//                setRelease_date("24.10.1999");
//                setCountry("USA");
//                setDirector("James Cameron");
//                setGenre("Drama");
//            }});
//        }};
//    }
}
