package controller;


import model.Auth;
import model.Rating;
import model.Room;
import service.AuthService;
import service.RatingService;
import service.RoomService;
import model.*;
import service.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.math.BigDecimal;


@WebServlet(name = "hotelController", urlPatterns = "/hotel-page")
public class HotelController extends HttpServlet {
    private RoomService roomService;
    private RatingService ratingService;
    private AuthService authService;
    private BookingService bookingService;
    private ProductService productService;
    private BillService billService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "room" -> showRoom(req, resp);
            case "room-detail" -> showRoomDetail(req, resp);
            case "about-us" -> showAboutUS(req, resp);
            case "bill-detail" -> showBill(req, resp);
            case "blog-details" -> showBlogDetail(req, resp);
            case "blog" -> showBlog(req, resp);
            case "contact" -> showContact(req, resp);
            default -> showIndex(req, resp);
        }
    }

    private void showRoomDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("room", roomService.findById(req));
        req.setAttribute("ratings", ratingService.findAll(id));
        req.getRequestDispatcher("hotel/room-details.jsp").forward(req, resp);

    }

    private void showContact(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("hotel/contact.jsp").forward(req, resp);
    }

    private void showBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("hotel/blog.jsp").forward(req, resp);
    }

    private void showBlogDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("hotel/blog-details.jsp").forward(req, resp);
    }

    private void showBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("room", roomService.findById(req));
        req.setAttribute("book", bookingService.findByIDAuth(7));
        req.setAttribute("products", productService.findAll());
        req.getRequestDispatcher("bill/bill-detail.jsp").forward(req, resp);
    }

    private void showAboutUS(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("hotel/about-us.jsp").forward(req, resp);
    }

    private void showRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("rooms", roomService.findAllRoom());
        req.getRequestDispatcher("hotel/rooms.jsp").forward(req, resp);
    }

    private void showIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        Auth auth = (Auth) req.getAttribute("Auth");
//        req.setAttribute("auth",auth);
        req.getRequestDispatcher("hotel/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "comment" -> saveRating(req, resp);
            case "pay" -> pay(req, resp);
        }
    }


    private void pay(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idUser = Integer.parseInt(req.getParameter("id"));
        int idRoom = Integer.parseInt(req.getParameter("room"));
        Auth auth = new Auth(idUser);

        String productPriceInput = req.getParameter("productPrice");
        BigDecimal productFee;
        if (productPriceInput != null && !productPriceInput.isEmpty()) {
            productFee = new BigDecimal(productPriceInput);
        } else {

            productFee = BigDecimal.ZERO;
        }
        int idProduct = Integer.parseInt(req.getParameter("productID"));
        Product product = new Product(idProduct, productFee);
        Room room = new Room(idRoom);

        String totalFee = req.getParameter("totalAmount");
        BigDecimal total;
        if (totalFee != null && !totalFee.isEmpty()) {
            total = new BigDecimal(totalFee);
        } else {
            total = BigDecimal.ZERO;
        }

        Bill bill = new Bill();
        bill.setCode(String.valueOf(Math.floor(Math.random() * 9000) + 1000));
        bill.setProduct(product);
        bill.setRoom(room);
        bill.setTotalAmount(total);
        bill.setStatusBill(EStatusBill.PAID);
        bill.setAuth(auth);
        billService.create(bill);
        resp.sendRedirect("/hotel-page?message=Payment Success");

    }

    public void saveRating(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("usernhap");
        String email = req.getParameter("emailnhap");

        Auth auth = authService.findByNameAndEmail(name, email);
        if (auth != null) {
            int id = Integer.parseInt(req.getParameter("room_id"));
            int authId = auth.getId(); // Lấy authId từ đối tượng Auth
            ratingService.saveRating(getRating(req, authId), id);
            resp.sendRedirect("hotel-page?action=room-detail&id=1");
        } else {
            resp.sendRedirect("/auth/login.jsp"); // Chuyển hướng về trang trước đó nếu không tìm thấy bản ghi phù hợp
        }
    }

    public Rating getRating(HttpServletRequest req, int authId) {
        Room room = new Room(Integer.parseInt(req.getParameter("room_id")));
        Auth auth = new Auth(authId);
        String scores = req.getParameter("rating");
        String comment = req.getParameter("comment");
        LocalDate date = LocalDate.now();
        return new Rating(room, auth, Double.parseDouble(scores), comment, Date.valueOf(date));
    }

    @Override
    public void init() throws ServletException {
        roomService = new RoomService();
        ratingService = new RatingService();
        authService = new AuthService();
        bookingService = new BookingService();
        productService = new ProductService();
        billService = new BillService();
    }
}
