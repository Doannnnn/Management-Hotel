package dao;

import dao.DatabaseConnection;
import model.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ImageDAO extends DatabaseConnection {
    public void create(List<Image> images, int roomId){
        String CREATE = "INSERT INTO `case3`.`images` (`url`, `room_id`) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            for(Image image : images) {
                preparedStatement.setString(1, image.getUrl());
                preparedStatement.setInt(2, roomId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }
}
