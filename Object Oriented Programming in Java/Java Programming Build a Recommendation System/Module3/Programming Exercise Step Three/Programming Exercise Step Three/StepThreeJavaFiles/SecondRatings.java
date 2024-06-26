
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public String getTitle(String id) { // ID of a movie
        String res = "The ID was not found.";
        for(Movie movie : myMovies) {
            if(movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return res;
    }
    
    public String getID(String title) { // Title of a movie
        String res = "NO SUCH TITLE.";
        for(Movie movie : myMovies) {
            if(movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return res;
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
        for(Movie movie : myMovies) {
            String movieId = movie.getID();
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
