package service;

import dao.ImageDAO;

import javax.servlet.http.HttpServletRequest;

public class ImageService {
    private ImageDAO imageDAO;
    public ImageService(){
        imageDAO = new ImageDAO();
    }

    public void create(HttpServletRequest req){

    }
}
