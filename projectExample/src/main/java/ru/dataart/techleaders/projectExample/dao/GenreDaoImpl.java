package ru.dataart.techleaders.projectExample.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dataart.techleaders.projectExample.dto.GenreDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final Authorization authorization;

    @Override
    public void addGenre(GenreDto genreDto) {
        String insertGenre = "INSERT INTO Genres (id, name)" + "VALUES (?, ?);";
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement statementGenre = connection.prepareStatement(insertGenre);
            statementGenre.setInt(1, genreDto.getId());
            statementGenre.setString(2, genreDto.getName());
            statementGenre.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addGenreOfMovie(Integer movieId, Integer genreId) {
        String insert = "INSERT INTO Genre_of_movie(movie_id, genre_id)" + "VALUES(?, ?)";
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement statementGenre = connection.prepareStatement(insert);
            statementGenre.setInt(1, movieId);
            statementGenre.setInt(2, genreId);
            statementGenre.executeUpdate();
        } catch(SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Integer findGenreByName(String name) {
        String sql = "SELECT id FROM Genres WHERE UPPER(name) LIKE ?;";
        Integer id = null;
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            name = name.toUpperCase();
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                id = rs.getInt("id");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Integer> findGenreByMovieId(Integer movieId) {
        String sql = "SELECT genre_id FROM Genre_of_movie WHERE movie_id = ?;";
        List<Integer> genreIdList = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Integer genreId = rs.getInt("genre_id");
                genreIdList.add(genreId);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return genreIdList;
    }

    @Override
    public String findGenreById(Integer genreId) {
        String sql = "SELECT name FROM Genres WHERE id = ?;";
        String name = null;
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, genreId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                name = rs.getString("name");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return name;
    }
}
