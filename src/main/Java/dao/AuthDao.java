package dao;




import model.Auth;
import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthDao extends DatabaseConnection {

    public List<Auth> getAllAuth(){
        List<Auth> results = new ArrayList<>();
        final String query = "SELECT * FROM `quanlykhachsan`.`user`";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Auth auth=new Auth();
                auth.setId(rs.getInt("id"));
                auth.setImg(rs.getString("img"));
                auth.setName(rs.getString("name"));
                auth.setEmail(rs.getString("email"));
                auth.setPhone(rs.getString("phone"));
                auth.setAddress(rs.getString("address"));
                auth.setPassword(rs.getString("password"));
                auth.setRole(new Role(rs.getInt("id"),rs.getString("name")));
                results.add(auth);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    public void register(Auth auth){
        final String REGISTER_USER = "INSERT INTO `quanlykhachsan`.`user` (`img`,`name`, `email`,`phone`,`address`, `password` ) VALUES (?,?,?,?,?,?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_USER);
            preparedStatement.setString(1, auth.getImg());
            preparedStatement.setString(2, auth.getName());
            preparedStatement.setString(3, auth.getEmail());
            preparedStatement.setString(4, auth.getPhone());
            preparedStatement.setString(5, auth.getAddress());
            preparedStatement.setString(6, auth.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void registerAdmin(Auth auth){
        final String REGISTER_USER = "INSERT INTO `quanlykhachsan`.`user` (`img`,`name`, `email`,`phone`,`address`, `password`,`role_id` ) VALUES (?,?,?,?,?,?,?)";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_USER);
            preparedStatement.setString(1, auth.getImg());
            preparedStatement.setString(2, auth.getName());
            preparedStatement.setString(3, auth.getEmail());
            preparedStatement.setString(4, auth.getPhone());
            preparedStatement.setString(5, auth.getAddress());
            preparedStatement.setString(6, auth.getPassword());
            preparedStatement.setInt(7, auth.getRole().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public Auth findByUsernameOrEmail(String usernameOrEmail){
        var SELECT_BY_ID = "SELECT u.*, r.name role_name " +
                " FROM user u JOIN roles r on " +
                " u.role_id = r.id " +
                " WHERE  u.email = ? ";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setString(1, usernameOrEmail);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Auth auth = new Auth();
                auth.setId(rs.getInt("id"));
                auth.setName(rs.getString("img"));
                auth.setName(rs.getString("name"));
                auth.setEmail(rs.getString("email"));
                auth.setEmail(rs.getString("phone"));
                auth.setAddress(rs.getString("address"));
                auth.setPassword(rs.getString("password"));
                auth.setRole(new Role(rs.getInt("id"), rs.getString("role_name")));
                return auth;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public boolean checkEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM user WHERE email = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void updatePassword(String email, String password) {
        if (checkEmailExists(email)) {
            String query = "UPDATE user SET password = ? WHERE email = ?";
            try {
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, password);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Email does not exist in the database");
        }
    }
}
