package service;

import dao.BillDAO;
import model.Bill;

public class BillService {
    private BillDAO billDAO;

    public BillService() {
        billDAO = new BillDAO();
    }
    public void create(Bill bill) {
        billDAO.create(bill);
    }
}
