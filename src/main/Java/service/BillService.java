package service;

import dao.BillDAO;
import model.Bill;

import java.util.List;

public class BillService {
    private BillDAO billDAO;

    public BillService() {
        billDAO = new BillDAO();
    }

    public void create(Bill bill) {
        billDAO.create(bill);
    }

    public List<Bill> getAllBill(){
        return billDAO.getAllBill();
    }

    public Bill findById(int id){
        return billDAO.findById(id);
    }
}
