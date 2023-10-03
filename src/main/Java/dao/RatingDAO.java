package dao;


import model.Auth;
import model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO extends DatabaseConnection {
    public List<Rating> selectAll(int id){
        String SELECT_RATING_SQL = "select r.*, u.img as img ,u.name as name from rating r \n" +
                "left join rooms ro on ro.id = ? \n" +
                "left join user u on r.user_id = u.id  ";
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RATING_SQL);
            preparedStatement.setInt(1,id);
            var rs = preparedStatement.executeQuery();
            var ratingList = new ArrayList<Rating>();
            while (rs.next()){
                Rating rating = new Rating();
                rating.setScores(rs.getDouble("scores"));
                rating.setDate(rs.getDate("date"));
                rating.setComment(rs.getString("comment"));
                rating.setAuth(new Auth(rs.getString("img"),rs.getString("name")));
                ratingList.add(rating);
            }
            return ratingList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
