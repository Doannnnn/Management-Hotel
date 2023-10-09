package service;

import dao.ImageDAO;
import dao.RoomDAO;
import model.*;
import service.dto.Page;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private RoomDAO roomDAO;
    private ImageDAO imageDAO;
    public RoomService(){
        roomDAO = new RoomDAO();
        imageDAO = new ImageDAO();
    }
    public List<Room> findAllRoom(){
        return roomDAO.findAllRoom();
    }

    public Page<Room> getRooms(int page, String search){
        return roomDAO.findAll(page, search);
    }

    public int create(Room room){
       return roomDAO.create(room);
    }
    public Room findById(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        return roomDAO.findById(id);
    }

    public void update(Room room){
        roomDAO.update(room);
    }

    public void delete(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        roomDAO.delete(id);
    }
}
