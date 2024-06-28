
/**
 * 在这里给出对类 MovieRunnerAverage 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        // SecondRatings secondRatings = new SecondRatings(); // default: "ratedmoviesfull.csv", "ratings.csv"
        SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        System.out.println("The Num of movies: " + secondRatings.getMovieSize());
        System.out.println("The Num of raters: " + secondRatings.getRaterSize());
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(3);
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem())); 
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        int minimalRaters = 12;
        // String movieTitle = "Vacation";
        // String movieId = secondRatings.getID(movieTitle);
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minimalRaters);
        // double movieRating = 0;
        // for(Rating averageRating : averageRatings) {
        //    if(averageRating.getItem().equals(movieId)) {
        //        movieRating = averageRating.getValue();
        //    }
        // }
        // System.out.println("The average for the movie " + "\"" + movieTitle +"\"" + " would be " + movieRating);
        
        // System.out.println(averageRatings.size());
        
        double minNumRating = 0;
        Rating minRating = null;
        for(Rating averageRating : averageRatings) {
            if(minRating == null) {
                minRating = averageRating;
                minNumRating = averageRating.getValue();
            } else if(averageRating.getValue() < minRating.getValue()) {
                minRating = averageRating;
                minNumRating = averageRating.getValue();
            }
        }
        System.out.println(secondRatings.getTitle(minRating.getItem()));
    }
}
