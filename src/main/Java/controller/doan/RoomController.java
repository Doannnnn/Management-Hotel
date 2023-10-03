package controller.doan;

import dao.doan.RoomDAO;
import model.doan.EAmenities;
import model.doan.ERoomClass;
import model.doan.EStatus;
import model.doan.EType;
import service.doan.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "roomController", urlPatterns = "/hotel")
public class RoomController extends HttpServlet {
    private RoomService roomService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(req, resp);
                break;
            default:
                showRoom(req, resp);
        }
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roomClass", ERoomClass.values());
        req.setAttribute("types", EType.values());
        req.setAttribute("amenities", EAmenities.values());
        req.setAttribute("status", EStatus.values());
        req.getRequestDispatcher("hotel/bills.jsp").forward(req,resp);
    }

    private void showRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("rooms", roomService.findAllRoom());
        req.getRequestDispatcher("hotel/rooms.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null) {
            action = "";
        }
        switch (action){
            case "create":
                roomService.create(req);
                resp.sendRedirect("/hotel");
                break;
        }
    }


    @Override
    public void init() throws ServletException {
        roomService = new RoomService();
    }
}


