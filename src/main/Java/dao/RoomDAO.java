package dao;

import dao.DatabaseConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.sql.DriverManager.getConnection;

public class RoomDAO extends DatabaseConnection {
    public List<Room> findAllRoom() {
        String SELECT_ALL_ROOMS = "SELECT r.*, group_concat(i.url) as images FROM rooms r JOIN images i ON r.id = i.room_id group by r.id;";
        List<Room> rooms = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROOMS)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setRoomClass(ERoomClass.valueOf(rs.getString("roomclass")));
                room.setType(EType.valueOf(rs.getString("type")));
                room.setPrice(rs.getBigDecimal("price"));
                room.setDescription(rs.getString("description"));
                String amenitiesString = rs.getString("amenities");
                String[] amnetArray = amenitiesString.split(",");
                List<EAmenities> amenitiesList = Arrays.stream(amnetArray).map(EAmenities::valueOf).collect(Collectors.toList());
                room.setAmenities(amenitiesList);
                String imageUrl = rs.getString("images");
                List<Image> imageList = new ArrayList<>();
                for (var item : imageUrl.split(",")) {
                    imageList.add(new Image(item));
                }
                room.setImages(imageList);
                room.setStatus(EStatus.valueOf(rs.getString("status")));
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }
    public int create(Room room){
        String CREATE = "INSERT INTO `case3`.`rooms` (`name`, `roomclass`, `type`, `price`, `description`, `amenities`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?);";
        String SELECT_MAX_ID = "SELECT MAX(id) as max_id FROM `case3`.`rooms`";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.setString(2, String.valueOf(room.getRoomClass()));
            preparedStatement.setString(3, String.valueOf(room.getType()));
            preparedStatement.setBigDecimal(4, room.getPrice());
            preparedStatement.setString(5, room.getDescription());
            preparedStatement.setString(6, getToString(room.getAmenities()));
            preparedStatement.setString(7, String.valueOf(room.getStatus()));
            preparedStatement.executeUpdate();
            PreparedStatement statementId = connection.prepareStatement(SELECT_MAX_ID);
            var rs = statementId.executeQuery();
            if(rs.next()){
                return rs.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return -1;
    }

    public Room findById(int id){
        String SELECT_ALL_ROOMS = "SELECT r.*, group_concat(i.url) as images FROM rooms r JOIN images i ON r.id = i.room_id where ( r.id = ? ) group by r.id;";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROOMS)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setRoomClass(ERoomClass.valueOf(rs.getString("roomclass")));
                room.setType(EType.valueOf(rs.getString("type")));
                room.setPrice(rs.getBigDecimal("price"));
                room.setDescription(rs.getString("description"));
                String amenitiesString = rs.getString("amenities");
                String[] amnetArray = amenitiesString.split(",");
                List<EAmenities> amenitiesList = Arrays.stream(amnetArray).map(EAmenities::valueOf).collect(Collectors.toList());
                room.setAmenities(amenitiesList);
                String imageUrl = rs.getString("images");
                List<Image> imageList = new ArrayList<>();
                for (var item : imageUrl.split(",")) {
                    imageList.add(new Image(item));
                }
                room.setImages(imageList);
                room.setStatus(EStatus.valueOf(rs.getString("status")));
                return room;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getToString(List<EAmenities> eAmenities) {
        return Arrays.stream(EAmenities.values())
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }
}
