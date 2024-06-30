
/**
 * 在这里给出对类 MovieRunnerSimilarRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
    public void printSimilarRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The Num of raters: " + RaterDatabase.size());
        
        String raterID = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        FourthRatings fr = new FourthRatings();
        // rater id, numSimilarRaters, minimalRaters
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        
        if(similarRatings.size() == 0 || similarRatings.size() == 1 ) {
            System.out.println(similarRatings.size() + " movie matched");
        } else {
            System.out.println(similarRatings.size() + " movies matched");
        }
        
        for(int i = 0; i < similarRatings.size(); i++) {
            if(i < 15) {
                System.out.printf("%d %.2f %s\n", i, similarRatings.get(i).getValue(), MovieDatabase.getTitle(similarRatings.get(i).getItem()));
            }
        }
    }
    
    public void printSimilarRatingsByGenre() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The Num of raters: " + RaterDatabase.size());
        
        String raterID = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        Filter filterCriteria = new GenreFilter("Mystery");
        FourthRatings fr = new FourthRatings();
        // rater id, numSimilarRaters, minimalRaters, Filter filterCriteria
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        
        if(similarRatings.size() == 0 || similarRatings.size() == 1 ) {
            System.out.println(similarRatings.size() + " movie matched");
        } else {
            System.out.println(similarRatings.size() + " movies matched");
        }
        
        for(int i = 0; i < similarRatings.size(); i++) {
            if(i < 15) {
                System.out.printf("%d %.2f %s %s\n", i, similarRatings.get(i).getValue(), MovieDatabase.getTitle(similarRatings.get(i).getItem()), MovieDatabase.getGenres(similarRatings.get(i).getItem()));
            }
        }
    }
    
    public void printSimilarRatingsByDirector() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The Num of raters: " + RaterDatabase.size());
        
        String raterID = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        Filter filterCriteria = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        FourthRatings fr = new FourthRatings();
        // rater id, numSimilarRaters, minimalRaters, Filter filterCriteria
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        
        if(similarRatings.size() == 0 || similarRatings.size() == 1 ) {
            System.out.println(similarRatings.size() + " movie matched");
        } else {
            System.out.println(similarRatings.size() + " movies matched");
        }
        
        for(int i = 0; i < similarRatings.size(); i++) {
            if(i < 15) {
                System.out.printf("%d %.2f %s %s\n", i, similarRatings.get(i).getValue(), MovieDatabase.getTitle(similarRatings.get(i).getItem()), MovieDatabase.getDirector(similarRatings.get(i).getItem()));
            }
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The Num of raters: " + RaterDatabase.size());
        
        String raterID = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        AllFilters filterCriteria = new AllFilters();
        Filter f1 = new GenreFilter("Drama");
        filterCriteria.addFilter(f1);
        Filter f2 = new MinutesFilter(80, 160);
        filterCriteria.addFilter(f2);
        FourthRatings fr = new FourthRatings();
        // rater id, numSimilarRaters, minimalRaters, Filter filterCriteria
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        
        if(similarRatings.size() == 0 || similarRatings.size() == 1 ) {
            System.out.println(similarRatings.size() + " movie matched");
        } else {
            System.out.println(similarRatings.size() + " movies matched");
        }
        
        for(int i = 0; i < similarRatings.size(); i++) {
            if(i < 15) {
                System.out.printf("%d %.2f %s %s %s\n", i, similarRatings.get(i).getValue(), MovieDatabase.getTitle(similarRatings.get(i).getItem()), MovieDatabase.getGenres(similarRatings.get(i).getItem()), MovieDatabase.getMinutes(similarRatings.get(i).getItem()));
            }
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The Num of raters: " + RaterDatabase.size());
        
        String raterID = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        AllFilters filterCriteria = new AllFilters();
        Filter f1 = new YearAfterFilter(1975);
        filterCriteria.addFilter(f1);
        Filter f2 = new MinutesFilter(70, 200);
        filterCriteria.addFilter(f2);
        FourthRatings fr = new FourthRatings();
        // rater id, numSimilarRaters, minimalRaters, Filter filterCriteria
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        
        if(similarRatings.size() == 0 || similarRatings.size() == 1 ) {
            System.out.println(similarRatings.size() + " movie matched");
        } else {
            System.out.println(similarRatings.size() + " movies matched");
        }
        
        for(int i = 0; i < similarRatings.size(); i++) {
            if(i < 15) {
                System.out.printf("%d %.2f %s %s %s\n", i, similarRatings.get(i).getValue(), MovieDatabase.getTitle(similarRatings.get(i).getItem()), MovieDatabase.getYear(similarRatings.get(i).getItem()), MovieDatabase.getMinutes(similarRatings.get(i).getItem()));
            }
        }
    }
    
    public void printAverageRatingsByYearAfter() {
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The Num of raters: " + RaterDatabase.size());
        YearAfterFilter yearFilter = new YearAfterFilter(2000);
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(20, yearFilter); // int minimalRaters, Filter filterCriteria
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem())); 
        }
    }
    
    public void printAverageRatingsByGenre() {
        FourthRatings fourthRatings = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The Num of movies: " + MovieDatabase.size());
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The Num of raters: " + RaterDatabase.size());
        GenreFilter genreFilter = new GenreFilter("Comedy");
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatingsByFilter(20, genreFilter); // int minimalRaters, Filter filterCriteria
        System.out.println("The Num of movies with ratings : " + averageRatings.size());
        for(Rating rating : averageRatings) {
           System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())); 
           System.out.println("    " + MovieDatabase.getGenres(rating.getItem()));
        }
    }
}
