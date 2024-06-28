
/**
 * 在这里给出对类 ThirdRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) { // movie Id,
        int countor = 0;
        double total = 0;
        double averageRating = 0.0;
        for(Rater rater : myRaters) {
            if(rater.hasRating(id)) {
                countor++;
                total+=rater.getRating(id);
            }
        }
        if(countor >= minimalRaters) {
            averageRating = total / countor;
        }
        return averageRating;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        // System.out.println(movies);
        for(String movieId : movies) {
            if(movieId != null) {
                double movieRating = getAverageByID(movieId, minimalRaters);
                if(movieRating != 0.0) {
                    Rating rating = new Rating(movieId, movieRating);
                    ratings.add(rating);
                }
            }
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movieId : movies) {
            if(movieId != null) {
                double movieRating = getAverageByID(movieId, minimalRaters);
                if(movieRating != 0.0) {
                    Rating rating = new Rating(movieId, movieRating);
                    ratings.add(rating);
                }
            }
        }
        return ratings;
    }
}
