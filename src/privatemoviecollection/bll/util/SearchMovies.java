/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.bll.util;

import java.util.ArrayList;
import java.util.List;
import privatemoviecollection.be.Movie;

/**
 *
 * @author Troels Klein
 */
public class SearchMovies {
    
    public static List<Movie> search(List<Movie> searchBase, String query)
    {
        List<Movie> results = new ArrayList<>();
        for (Movie movie : searchBase)
        {
            if (movie.getName().toLowerCase().contains(query.toLowerCase())) 
            {
                results.add(movie);
            }
        }
        return results;
    }

}
