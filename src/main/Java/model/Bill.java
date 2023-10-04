package model;

import java.math.BigDecimal;
import java.sql.Date;

public class Bill {
    private int id;
    private String code;
    private Booking booking;
    private Room room;
    private Product product;
    private BigDecimal totalAmount;
    private EStatusBill statusBill;

    public Bill(int id, String code, Room room, Product product, BigDecimal totalAmount, EStatusBill statusBill) {
        this.id = id;
        this.code = code;
        this.room = room;
        this.product = product;
        this.totalAmount = totalAmount;
        this.statusBill = statusBill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public EStatusBill getStatusBill() {
        return statusBill;
    }

    public void setStatusBill(EStatusBill statusBill) {
        this.statusBill = statusBill;
    }
}
