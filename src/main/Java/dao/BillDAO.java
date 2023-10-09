package dao;

import model.*;
import service.dto.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BillDAO extends DatabaseConnection{

    public void create(Bill bill) {
        String CREATE_BILL_SQL = "INSERT INTO `bill` (`code`, `user_id`, `room_id`, `product_id`, `total_amount`,`date_invoice`) VALUES (?, ?, ?, ?, ?,?);";
        try{
            Connection connection = getConnection();
            PreparedStatement pre = connection.prepareStatement(CREATE_BILL_SQL);
            pre.setString(1,bill.getCode());
            pre.setInt(2,bill.getAuth().getId());
            pre.setInt(3,bill.getRoom().getId());
            pre.setInt(4,bill.getProduct().getId());
            pre.setBigDecimal(5,bill.getTotalAmount());
            pre.setDate(6,bill.getDateOfInvoice());
            pre.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Bill> getAllBill() {
        String SELECT_ALL_BILL = "SELECT b.id, b.code, r.name AS room_name, r.type, " +
                "p.name AS service, u.name AS user_name, u.phone, bk.number_room, b.total_amount AS total, b.status " +
                "FROM bill b " +
                "JOIN user u ON b.user_id = u.id " +
                "JOIN bookings bk ON b.booking_id = bk.id " +
                "JOIN rooms r ON b.room_id = r.id " +
                "JOIN products p ON b.product_id = p.id";
        List<Bill> bills = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BILL)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCode(rs.getString("code"));
                bill.setTotalAmount(rs.getBigDecimal("total"));

                Room room = new Room();
                room.setName(rs.getString("room_name"));
                room.setType(EType.valueOf(rs.getString("type")));

                Product product = new Product();
                product.setName(rs.getString("service"));

                Booking booking = new Booking();
                booking.setNumberRoom(rs.getInt("number_room"));

                Auth auth = new Auth();
                auth.setName(rs.getString("user_name"));
                auth.setPhone(rs.getString("phone"));

                bill.setRoom(room);
                bill.setBooking(booking);
                bill.setProduct(product);
                bill.setAuth(auth);

                // Lấy giá trị trạng thái và gán vào Enum EStatusBill
                String statusStr = rs.getString("status");
                if (statusStr != null) {
                    EStatusBill statusBill = EStatusBill.valueOf(statusStr);
                    bill.setStatusBill(statusBill);
                }

                bills.add(bill);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return bills;
    }

    public Bill findById(int id) {
        String SELECT_BILL_BY_ID = "SELECT b.id, b.code, r.id AS room_id, r.name AS room_name, r.type AS room_type, " +
                "p.id AS product_id, p.name AS product_name, " +
                "u.id AS user_id, u.name AS user_name, u.phone AS user_phone, " +
                "b.total_amount AS total, b.status " +
                "FROM bill b " +
                "JOIN rooms r ON b.room_id = r.id " +
                "JOIN products p ON b.product_id = p.id " +
                "JOIN user u ON b.user_id = u.id " +
                "WHERE b.id = ?";
        Bill bill = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BILL_BY_ID)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCode(rs.getString("code"));
                bill.setTotalAmount(rs.getBigDecimal("total"));

                Room room = new Room();
                room.setId(rs.getInt("room_id"));
                room.setName(rs.getString("room_name"));
                room.setType(EType.valueOf(rs.getString("room_type")));

                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));

                Auth auth = new Auth();
                auth.setId(rs.getInt("user_id"));
                auth.setName(rs.getString("user_name"));
                auth.setPhone(rs.getString("user_phone"));

                bill.setRoom(room);
                bill.setProduct(product);
                bill.setAuth(auth);

                // Lấy giá trị trạng thái và gán vào Enum EStatusBill
                String statusStr = rs.getString("status");
                if (statusStr != null) {
                    EStatusBill statusBill = EStatusBill.valueOf(statusStr);
                    bill.setStatusBill(statusBill);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return bill;
    }


    public Page<Bill> findAllBill(int page, String search){
        var result = new Page<Bill>();
        final int TOTAL_ELEMENT = 6;
        result.setCurrentPage(page);
        var content = new ArrayList<Bill>();
        if(search == null){
            search = "";
        }
        search = "%" + search.toLowerCase() + "%";
        var SELECT_ALL = "SELECT b.id, b.code, u.name AS user_name, u.phone, r.type, bk.number_room, b.total_amount AS total, b.status " +
                "FROM bill b " +
                "JOIN user u ON b.user_id = u.id " +
                "JOIN bookings bk ON b.booking_id = bk.id " +
                "JOIN rooms r ON b.room_id = r.id " +
                "JOIN products p ON b.product_id = p.id " +
                "WHERE (LOWER(b.code) LIKE ?) " +
                "LIMIT ? OFFSET ?";

        var SELECT_COUNT = "SELECT COUNT(1) cnt FROM bill b " +
                "WHERE (LOWER(b.code) LIKE ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setString(1, search);
            preparedStatement.setInt(2,TOTAL_ELEMENT);
            preparedStatement.setInt(3, (page - 1) * TOTAL_ELEMENT);
            System.out.println(preparedStatement);
            var rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setCode(rs.getString("code"));
                bill.setTotalAmount(rs.getBigDecimal("total"));

                Room room = new Room();
                room.setType(EType.valueOf(rs.getString("type")));

                Booking booking = new Booking();
                booking.setNumberRoom(rs.getInt("number_room"));

                Auth auth = new Auth();
                auth.setName(rs.getString("user_name"));
                auth.setPhone(rs.getString("phone"));

                bill.setRoom(room);
                bill.setBooking(booking);
                bill.setAuth(auth);

                // Lấy giá trị trạng thái và gán vào Enum EStatusBill
                String statusStr = rs.getString("status");
                if (statusStr != null) {
                    EStatusBill statusBill = EStatusBill.valueOf(statusStr);
                    bill.setStatusBill(statusBill);
                }

                content.add(bill);
            }
            result.setContent(content);
            var preparedStatementCount = connection.prepareStatement(SELECT_COUNT);
            preparedStatementCount.setString(1, search);
            var rsCount = preparedStatementCount.executeQuery();
            if(rsCount.next()){
                result.setTotalPage((int) Math.ceil((double) rsCount.getInt("cnt") /TOTAL_ELEMENT));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
        return result;
    }

}
