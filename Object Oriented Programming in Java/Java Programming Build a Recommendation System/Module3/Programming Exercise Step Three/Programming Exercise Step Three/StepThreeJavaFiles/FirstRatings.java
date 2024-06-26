
/**
 * 在这里给出对类 FirstRatings 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> movieData = new ArrayList<Movie>();
        for(CSVRecord movie : parser) {
            // String anID, String aTitle, String aYear, String theGenres, 
            // String aDirector, String aCountry, String aPoster, int theMinutes
            Movie newMovie = new Movie(
                movie.get("id"), movie.get("title"), movie.get("year"), movie.get("genre"),
                movie.get("director"), movie.get("country"), movie.get("poster"), Integer.parseInt(movie.get("minutes"))
            );
            movieData.add(newMovie);
        }
        return movieData;
    }
    
    public int countByGenre(ArrayList<Movie> movies, String genre) { // == genre
        int countor = 0;
        for(Movie movie : movies) {
            String genres = movie.getGenres();
            int pos = genres.indexOf(genre);
            if(pos != -1) {
                countor++;
            }
        }
        return countor;
    }
    
    public int countByTime(ArrayList<Movie> movies, int time) { // > time
        int countor = 0;
        for(Movie movie : movies) {
            int movieTime = movie.getMinutes();
            if(movieTime > time) {
                countor++;
            }
        }
        return countor;
    }
    
    public int countByDirector(ArrayList<Movie> movies, String director) {
        int countor = 0;
        for(Movie movie : movies) {
            String directors = movie.getDirector();
            int pos = directors.indexOf(director);
            if(pos != -1) {
                countor++;
            }
        }
        return countor;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("ratedmoviesfull.csv");
        System.out.println("The number of movies: " + movies.size());
        // for(Movie movie : movies) {
        //    System.out.println(movie);
        // }
        // System.out.println("countByGenre: " + countByGenre(movies, "Comedy"));
        // System.out.println("countByTime: " + countByTime(movies, 150));
        // System.out.println("countByDirector: " + countByDirector(movies, "Michael Mann"));
        int maxNumMovies = 0; // maximum number of films directed by one director?
        String directorName = null;
        for(Movie movie : movies) {
            String director = movie.getDirector();
            if(countByDirector(movies, director) > maxNumMovies) {
                maxNumMovies = countByDirector(movies, director);
                directorName = director;
            }
        }
        System.out.println("The maximum number of films directed by one director: " + maxNumMovies);
        System.out.println("Director Name: " + directorName);
    }
    
    private boolean containsRater(ArrayList<Rater> raterData, String raterId) {
        boolean isContain = false;
        for(Rater rater : raterData) {
            String id = rater.getID();
            if(id.equals(raterId)) {
                isContain = true;
            }
        }
        return isContain;
    }
    
    private Rater getRater(ArrayList<Rater> raterData, String raterId) {
        for(Rater rater : raterData) {
            String id = rater.getID();
            if(id.equals(raterId)) {
                return rater;
            }
        }
        return null;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> raterData = new ArrayList<Rater>();
        for(CSVRecord raterRecord : parser) {
            String raterId = raterRecord.get("rater_id");
            String movieId = raterRecord.get("movie_id");
            double movieRating = Double.parseDouble(raterRecord.get("rating"));
            if(containsRater(raterData, raterId)) {
                Rater raterExisted = getRater(raterData, raterId);
                raterExisted.addRating(movieId, movieRating);
            } else {
                Rater newRater = new EfficientRater(raterId);
                raterData.add(newRater);
                newRater.addRating(movieId, movieRating);
            }
        }
        return raterData;
    }
    
    private int findNumRaters(ArrayList<Rater> raters, String item) { // Stirng item : movieID
        int countor = 0;
        for(Rater rater : raters) {
            if(rater.hasRating(item)) {
                countor++;
            }
        }
        return countor;
    }
    
    private int findNumMoviesRated(ArrayList<Rater> raters) {
        HashSet<String> uniqueMoviesRated = new HashSet<String>();
        for(Rater rater : raters) {
            ArrayList<String> itemsRated = rater.getItemsRated(); // items: movies' id
            for(String item : itemsRated) {
                uniqueMoviesRated.add(item);
            }
        }
        return uniqueMoviesRated.size();
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("ratings.csv");
        System.out.println("The total number of raters: " + raters.size());
        
        // int maxNumRating = 0;
        // ArrayList<String> maxRaterIds = new ArrayList<String>();
        
        for(Rater rater : raters) {
            // System.out.println("Rater’s ID: " + rater.getID() + ", The number of ratings: " + rater.numRatings());
            ArrayList<String> movieIDs = rater.getItemsRated();
            // for(String movieID : movieIDs) {
            //    System.out.println("movieID: " + movieID + ", Rating " + rater.getRating(movieID));
            // }
            
            // if(Integer.parseInt(rater.getID()) == 193) {
                // System.out.println(movieIDs.size());
                // for(String movieID : movieIDs) {
                //    System.out.println("movieID: " + movieID + ", Rating " + rater.getRating(movieID));
                // }
            // }
            
            
            // if(movieIDs.size() > maxNumRating) {
            //   maxNumRating=movieIDs.size();
            //   maxRaterIds = new ArrayList<String>();
            //   maxRaterIds.add(rater.getID());
            // } else if(movieIDs.size() == maxNumRating) {
            //   maxRaterIds.add(rater.getID());
            // }
        }
        // System.out.println("The Num of raters that has the maximum ratings: " + maxRaterIds.size() + 
        //                   ", Maximum ratings are: " + maxNumRating);
        // for(String raterId : maxRaterIds) {
        //    System.out.println(raterId);
        // }
        
        System.out.println("The number of ratings a particular movie has: " + findNumRaters(raters, "1798709"));
        
        System.out.println("The Num of different movies have been rated by all these raters: " + findNumMoviesRated(raters));
    }
}
