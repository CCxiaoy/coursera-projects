
/**
 * 在这里给出对类 FourthRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class FourthRatings {  
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0;
        
        ArrayList<String> myMovies = me.getItemsRated();
        for (String id: myMovies) {
            if (r.hasRating(id)){
                    double myRating = me.getRating(id);
                    double rRating = r.getRating(id);
                    myRating -= 5;
                    rRating -= 5;
                    dotProduct += myRating * rRating;
            }
        }
    
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) { // Rater id
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()) {
            if(!r.getID().equals(id)) {
                if(dotProduct(me, r) > 0) {
                    similarRatings.add(new Rating(r.getID(), dotProduct(me, r)));
                }
            }
        }
        Collections.sort(similarRatings, Collections.reverseOrder());
        return similarRatings;
    }
        
    // rater id, numSimilarRaters, minimalRaters  
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> movieRatings = new ArrayList<Rating>();
        
        ArrayList<Rating> similaritiesRatings = getSimilarities(id);
        // System.out.println(similaritiesRatings);
        
        ArrayList<String> movieIDs = MovieDatabase.filterBy(new TrueFilter());
        // System.out.println(movieIDs);
        
        for(String movieID : movieIDs) {
            int raterCoutor = 0;
            double sum = 0;
            double weightedAverage = 0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating similarRating = similaritiesRatings.get(i);
                double raterWeight = similarRating.getValue(); // rater similarity
                String raterID = similarRating.getItem(); // rater id
                Rater rater = RaterDatabase.getRater(raterID);
                if(rater.hasRating(movieID)) {
                    raterCoutor++;
                    sum += raterWeight * rater.getRating(movieID);
                }
            }
            if(raterCoutor >= minimalRaters) {
                weightedAverage = sum / raterCoutor;
                movieRatings.add(new Rating(movieID, weightedAverage));
            }
        }
                
        Collections.sort(movieRatings, Collections.reverseOrder());
        return movieRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> movieRatings = new ArrayList<Rating>();
        
        ArrayList<Rating> similaritiesRatings = getSimilarities(id);
        
        ArrayList<String> movieIDs = MovieDatabase.filterBy(filterCriteria);
        
        for(String movieID : movieIDs) {
            int raterCoutor = 0;
            double sum = 0;
            double weightedAverage = 0;
            for(int i = 0; i < numSimilarRaters; i++) {
                Rating similarRating = similaritiesRatings.get(i);
                double raterWeight = similarRating.getValue(); // rater similarity
                String raterID = similarRating.getItem(); // rater id
                Rater rater = RaterDatabase.getRater(raterID);
                if(rater.hasRating(movieID)) {
                    raterCoutor++;
                    sum += raterWeight * rater.getRating(movieID);
                }
            }
            if(raterCoutor >= minimalRaters) {
                weightedAverage = sum / raterCoutor;
                movieRatings.add(new Rating(movieID, weightedAverage));
            }
        }
                
        Collections.sort(movieRatings, Collections.reverseOrder());
        return movieRatings;
    }
    
    private double getAverageByID(String id, int minimalRaters) { // movie Id,
        int countor = 0;
        double total = 0;
        double averageRating = 0.0;
        for(Rater rater : RaterDatabase.getRaters()) {
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
