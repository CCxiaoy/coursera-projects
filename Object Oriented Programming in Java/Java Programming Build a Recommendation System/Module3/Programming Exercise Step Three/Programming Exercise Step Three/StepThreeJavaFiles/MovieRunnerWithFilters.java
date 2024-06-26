
/**
 * 在这里给出对类 MovieRunnerWithFilters 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        System.out.println("The Num of raters: " + thirdRatings.getRaterSize());
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(35);
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())); 
        }
    }
    
    public void printAverageRatingsByYearAfter() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        System.out.println("The Num of raters: " + thirdRatings.getRaterSize());
        YearAfterFilter yearFilter = new YearAfterFilter(2000);
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(20, yearFilter); // int minimalRaters, Filter filterCriteria
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem())); 
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        System.out.println("The Num of raters: " + thirdRatings.getRaterSize());
        GenreFilter genreFilter = new GenreFilter("Comedy");
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(20, genreFilter); // int minimalRaters, Filter filterCriteria
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())); 
           System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        System.out.println("The Num of raters: " + thirdRatings.getRaterSize());
        MinutesFilter minutesFilter = new MinutesFilter(105, 135);
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(5, minutesFilter); // int minimalRaters, Filter filterCriteria
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + "Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem())); 
        }
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        System.out.println("The Num of raters: " + thirdRatings.getRaterSize());
        DirectorsFilter directorsFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(4, directorsFilter); // int minimalRaters, Filter filterCriteria
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())); 
           System.out.println("    " + MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        System.out.println("The Num of raters: " + thirdRatings.getRaterSize());
        AllFilters allFilters = new AllFilters();
        YearAfterFilter yearFilter = new YearAfterFilter(1990);
        allFilters.addFilter(yearFilter);
        GenreFilter genreFilter = new GenreFilter("Drama");
        allFilters.addFilter(genreFilter);
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(8, allFilters); // int minimalRaters, Filter filterCriteria
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem())); 
           System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings thirdRatings = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        System.out.println("The Num of raters: " + thirdRatings.getRaterSize());
        AllFilters allFilters = new AllFilters();
        MinutesFilter minutesFilter = new MinutesFilter(90, 180);
        allFilters.addFilter(minutesFilter);
        DirectorsFilter directorsFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        allFilters.addFilter(directorsFilter);
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(3, allFilters); // int minimalRaters, Filter filterCriteria
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + "Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem())); 
           System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
}
