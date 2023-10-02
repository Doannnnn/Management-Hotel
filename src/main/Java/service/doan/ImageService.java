package service.doan;

import dao.doan.ImageDAO;
import model.doan.Image;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class ImageService {
    private ImageDAO imageDAO;
    public ImageService(){
        imageDAO = new ImageDAO();
    }
    public void create(HttpServletRequest req){

    }
}
