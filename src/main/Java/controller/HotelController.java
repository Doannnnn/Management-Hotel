package controller;

import service.AuthService;
import service.RatingService;
import service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "hotelController" , urlPatterns = "/hotel-page")
public class HotelController extends HttpServlet {
    private RoomService roomService;
    private RatingService ratingService;
    private AuthService authService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "room"-> showRoom(req,resp);
            case "room-detail"-> showRoomDetail(req,resp);
            case "about-us"-> showAboutUS(req,resp);
            case "bill-detail"-> showBill(req,resp);
            case "blog-details"-> showBlogDetail(req,resp);
            case "blog"-> showBlog(req,resp);
            case "contact"-> showContact(req,resp);
            default -> showIndex(req,resp);
        }
    }

    private void showRoomDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        req.setAttribute("room",roomService.findById(req));
        req.setAttribute("ratings",ratingService.findAll(id));
        req.getRequestDispatcher("hotel/room-details.jsp").forward(req,resp);

    }

    private void showContact(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("hotel/contact.jsp").forward(req,resp);
    }

    private void showBlog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("hotel/blog.jsp").forward(req,resp);
    }
    private void showBlogDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("hotel/blog-details.jsp").forward(req,resp);
    }

    private void showBill(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user",authService.findByID(7));
        req.setAttribute("room",roomService.findById(req));
        req.getRequestDispatcher("bill/bill-detail.jsp").forward(req,resp);
    }

    private void showAboutUS(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("hotel/about-us.jsp").forward(req,resp);
    }

    private void showRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("rooms",roomService.findAllRoom());
        req.getRequestDispatcher("hotel/rooms.jsp").forward(req,resp);
    }

    private void showIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("hotel/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        roomService = new RoomService();
        ratingService = new RatingService();
        authService = new AuthService();
    }
}
