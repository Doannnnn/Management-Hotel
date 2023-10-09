package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                "p.name AS service, u.name AS user_name, u.phone, b.total_amount AS total, b.status " +
                "FROM bill b " +
                "JOIN user u ON b.user_id = u.id " +
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

                Auth auth = new Auth();
                auth.setName(rs.getString("user_name"));
                auth.setPhone(rs.getString("phone"));

                bill.setRoom(room);
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
        String SELECT_BILL_BY_ID = "SELECT b.id, b.code, r.*, p.*, u.*, b.total_amount total, b.status " +
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
                room.setName(rs.getString("name"));
                room.setType(EType.valueOf(rs.getString("type")));

                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));

                Auth auth = new Auth();
                auth.setId(rs.getInt("user_id"));
                auth.setName(rs.getString("name"));
                auth.setPhone(rs.getString("phone"));

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

}
