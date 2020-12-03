package ru.dataart.techleaders.projectExample.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.dataart.techleaders.projectExample.dto.DirectorDto;

import java.sql.*;

@Repository
public class DirectorDaoImpl implements DirectorDao {

    @Autowired
    private Authorization authorization;

    @Override
    public Integer findByName(String name) {
        String sql = "SELECT id FROM Directors WHERE UPPER(name) LIKE ?;";
        Integer id = null;
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            name = name.toUpperCase();
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt("id");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public String findById(Integer id) {
        String sql = "SELECT name FROM Directors WHERE id = ?;";
        String name = null;
        try(Connection connection = DriverManager.getConnection(authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                name = rs.getString("name");
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    @Override
    public void addDirector(DirectorDto directorDto) {
        String insertDirector = "INSERT INTO Directors (id, name)"
                + "VALUES (?, ?);";
        try (Connection connection = DriverManager.getConnection (authorization.getUrl(), authorization.getUser(), authorization.getPassword())) {
            PreparedStatement statementDir =
                    connection.prepareStatement (insertDirector);
            statementDir.setInt(1, directorDto.getId());
            statementDir.setString(2, directorDto.getName());
            statementDir.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }
}
