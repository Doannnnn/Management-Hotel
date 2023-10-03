package service.doan;

import dao.doan.ImageDAO;
import dao.doan.RoomDAO;
import model.doan.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void create(HttpServletRequest req){
        String name = req.getParameter("name");
        ERoomClass roomClass = ERoomClass.valueOf(req.getParameter("roomClass"));
        EType type = EType.valueOf(req.getParameter("type"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String description = req.getParameter("description");
        String[] urls = req.getParameterValues("img");
        List<Image> imageList = new ArrayList<>();
        for (String url : urls) {
            Image image = new Image();
            image.setUrl(url);
            imageList.add(image);
        }
        List<EAmenities> amenitiesList = new ArrayList<>();
        for (String amenity : req.getParameterValues("selectedAmenities")) {
            EAmenities amenityEnum = EAmenities.valueOf(amenity);
            amenitiesList.add(amenityEnum);
        }
        EStatus status = EStatus.valueOf(req.getParameter("status"));
        Room room = new Room();
        room.setName(name);
        room.setRoomClass(roomClass);
        room.setType(type);
        room.setPrice(price);
        room.setDescription(description);
        room.setAmenities(amenitiesList);
        room.setStatus(status);
        int roomId = roomDAO.create(room);
        imageDAO.create(imageList, roomId);
    }
    public Room findById(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        return roomDAO.findById(id);
    }
}
