package controller;

import model.EAmenities;
import model.ERoomClass;
import model.EStatus;
import model.EType;
import service.RoomService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "roomController", urlPatterns = "/admin")
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
            case "eidt":
                showEdit(req, resp);
                break;
            case "delete":
                showCreate(req, resp);
                break;
            default:
                showRoom(req, resp);
        }
    }

<<<<<<< Updated upstream
=======
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        roomService.delete(req);
        req.getRequestDispatcher("admin/index.jsp").forward(req,resp);
    }

>>>>>>> Stashed changes
    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("room", roomService.findById(req));
        req.setAttribute("roomClass", ERoomClass.values());
        req.setAttribute("types", EType.values());
        req.setAttribute("amenities", EAmenities.values());
        req.setAttribute("status", EStatus.values());
        req.getRequestDispatcher("admin/edit.jsp").forward(req,resp);
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roomClass", ERoomClass.values());
        req.setAttribute("types", EType.values());
        req.setAttribute("amenities", EAmenities.values());
        req.setAttribute("status", EStatus.values());
        req.getRequestDispatcher("admin/create.jsp").forward(req,resp);
    }

    private void showRoom(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageString = req.getParameter("page");
        if (pageString == null) {
            pageString = "1";
        }
        req.setAttribute("page", roomService.(Integer.parseInt(pageString), req.getParameter("search")));
<<<<<<< HEAD
        req.setAttribute("page", roomService.getRooms(Integer.parseInt(pageString), req.getParameter("search")));
=======
>>>>>>> 8495a344a398783ea17aeeaae4704c127ba94172
        req.setAttribute("search", req.getParameter("search"));
        req.setAttribute("rooms", roomService.findAllRoom());
        req.getRequestDispatcher("admin/index.jsp").forward(req,resp);
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
                resp.sendRedirect("/admin");
                break;
            case "eidt":
                roomService.create(req);
                resp.sendRedirect("/admin");
                break;
        }
    }


    @Override
    public void init() throws ServletException {
        roomService = new RoomService();
    }
}


