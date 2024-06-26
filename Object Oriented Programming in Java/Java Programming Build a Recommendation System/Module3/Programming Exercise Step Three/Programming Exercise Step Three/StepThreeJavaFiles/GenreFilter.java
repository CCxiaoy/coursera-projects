
/**
 * 在这里给出对类 GenreFilter 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class GenreFilter implements Filter {
    private String genre;
    
    public GenreFilter(String movieGenre) {
        genre = movieGenre;
    }
    
    public boolean satisfies(String id) {
        String genres = MovieDatabase.getGenres(id);
        return (genres.indexOf(genre) != -1);
    }
}
