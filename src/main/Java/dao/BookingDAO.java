package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookingDAO extends DatabaseConnection{

    public Booking findById(int id){
        String SELECT_BOOKING_BY_ID = "SELECT b.*, u.`name` name, u.phone phone, u.address address FROM bookings b join `user` u on b.user_id = u.id WHERE b.id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setCheckInDate(rs.getDate("check_in"));
                booking.setCheckOutDate(rs.getDate("check_out"));
                booking.setNumberGuests(rs.getInt("number_guests"));
                booking.setNumberRoom(rs.getInt("number_room"));
                booking.setAuth(new Auth(rs.getInt("id"), rs.getString("name"), rs.getString("phone"), rs.getString("address")));
                return booking;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void create(Booking booking){
        String CREATE_BOOKING = "INSERT INTO `case3`.`bookings` (`check_in`, `check_out`, `number_guests`, `number_room`, `user_id`) VALUES (?, ?, ?, ?, ?);";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BOOKING)) {
            preparedStatement.setDate(1, booking.getCheckInDate());
            preparedStatement.setDate(2, booking.getCheckOutDate());
            preparedStatement.setInt(3, booking.getNumberGuests());
            preparedStatement.setInt(4, booking.getNumberRoom());
            preparedStatement.setInt(5, booking.getAuth().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }
    public void delete(int id){
        String DELETE_BOOKING = "DELETE FROM `case3`.`bookings` WHERE (`id` = ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
