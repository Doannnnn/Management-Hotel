package service;

import dao.RatingDAO;
import model.Rating;

import java.util.List;


public class RatingService {
    private RatingDAO ratingDAO;

    public RatingService() {
        ratingDAO = new RatingDAO();

    }
    public List<Rating> findAll(int id) {
        return ratingDAO.selectAll(id);
    }
}
