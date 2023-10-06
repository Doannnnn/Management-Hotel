package service;

import dao.BookingDAO;
import model.Booking;

public class BookingService {
    private BookingDAO bookingDAO;

    public BookingService() {
        bookingDAO = new BookingDAO();
    }
    public Booking findByIDAuth(int idAuth){
        return bookingDAO.findById(idAuth);
    }
}