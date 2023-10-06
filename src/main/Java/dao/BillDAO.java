package dao;

import model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillDAO extends DatabaseConnection{

    public void create(Bill bill) {
        String CREATE_BILL_SQL = "INSERT INTO `bill` (`code`, `user_id`, `room_id`, `product_id`, `total_amount`) VALUES (?, ?, ?, ?, ?);";
        try{
            Connection connection = getConnection();
            PreparedStatement pre = connection.prepareStatement(CREATE_BILL_SQL);
            pre.setString(1,bill.getCode());
            pre.setInt(2,bill.getAuth().getId());
            pre.setInt(3,bill.getRoom().getId());
            pre.setInt(4,bill.getProduct().getId());
            pre.setBigDecimal(5,bill.getTotalAmount());
            pre.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
