/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.database;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import privatemoviecollection.be.Category;
import privatemoviecollection.be.Movie;
import privatemoviecollection.dal.dalException.DALException;

/**
 *
 * @author Christina
 */
public class MovieDBDAO
{

    private DatabaseConnector dbCon;

    public MovieDBDAO() throws DALException
    {
        
            dbCon = new DatabaseConnector();
    }

    /**
     * Creates a movie in the database. 
     * @param name
     * @param rating
     * @param filelink
     * @param imdb
     * @param idList
     * @return Movie
     * @throws DALException 
     */
    public Movie createMovie(String name, int rating, String filelink, float imdb, List<Category> idList) throws DALException
    {

        try
        {
            Connection con = dbCon.getConnection();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String sql = "INSERT INTO Movie VALUES (?,?,?,?,?);";
            String sql2 = "INSERT INTO CatMovie VALUES (?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement ps2 = con.prepareStatement(sql2);

            ps.setString(1, name);
            ps.setInt(2, rating);
            ps.setString(3, filelink);
            ps.setDate(4, sqlDate);
            ps.setFloat(5, imdb);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);

                    for (Category i : idList)
                    {
                        ps2.setInt(1, i.getId());
                        ps2.setInt(2, id);
                        ps2.executeUpdate();
                    }

                    Movie movie = getMovie(id);

                    return movie;
                }

            }
        } catch (SQLException ex)
        {   
            ex.printStackTrace();
            throw new DALException("Could not create Movie");
        }
        return null;
    }

    /**
     * Gets a movie from the database with an id.
     * @param id
     * @return
     * @throws DALException 
     */
    public Movie getMovie(int id) throws DALException
    {
        // only one movie will be returned when searching on id
        // so always return the first and only movie in the result list
        return getMoviesQuery(id).get(0);
    }

    public List<Movie> getAllMovies() throws DALException
    {
        return getMoviesQuery(0);
    }

    public List<Movie> getMoviesQuery(int movieId) throws DALException
    {
        try ( Connection con = dbCon.getConnection())
        {
            List<Movie> movies = new ArrayList<>();

            String sql = "SELECT * FROM Movie;";
            if (movieId > 0)
            {
                sql = "SELECT * FROM Movie WHERE id = " + movieId + ";";
            }

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String filelink = rs.getString("filelink");
                String name = rs.getString("name");
                int rating = rs.getInt("rating");
                Date lastview = rs.getDate("lastview");
                float imdb = rs.getFloat("imdb");

                Movie movie = new Movie(id, filelink, name, imdb, rating);

                // get categories for movie
                String sql2 = "SELECT id, name FROM Category WHERE id IN (SELECT CategoryId FROM CatMovie WHERE MovieId = " + id + ");";
                Statement statement2 = con.createStatement();
                ResultSet rs2 = statement2.executeQuery(sql2);

                while (rs2.next())
                {
                    int catId = rs2.getInt("id");
                    String catName = rs2.getString("name");

                    Category category = new Category(catId, catName);
                    movie.addCategory(category);
                }

                movies.add(movie);
            }

            System.out.println("\n\n*** TEST ***\n");
            for (Movie movie : movies)
            {
                System.out.println("Movie name: " + movie.getName() + " is in " + movie.getCategories().size() + " categories");
            }

            return movies;
        } catch (SQLException ex)
        {   
            ex.printStackTrace();
            throw new DALException("Could not get movie");
        }
    }

    /**
     * Deletes a movie from the database.
     * @param mov
     * @throws DALException 
     */
    public void deleteMovie(Movie mov) throws DALException
    {
        try
        {

            Connection con = dbCon.getConnection();

            int id = mov.getId();
            String sql = "DELETE FROM Movie WHERE id=?;";
            String sql2 = "DELETE FROM CatMovie WHERE MovieId = (?)";
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps.setInt(1, id);
            ps2.setInt(1, id);

            ps2.executeUpdate();
            ps.executeUpdate();
        } catch (SQLException ex)
        {   
            ex.printStackTrace();
            throw new DALException("Could not delete movie");
        }
    }

    /**
     * Updates a movie in the database with a certain id.
     * @param mov
     * @throws DALException 
     */
    public void updateMovie(Movie mov) throws DALException
    {
        try
        {
            Connection con = dbCon.getConnection();

            int id = mov.getId();
            String sql = "UPDATE Movie SET name = ?, rating = ?, filelink = ?, imdb = ? WHERE id=" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, mov.getName());
            ps.setInt(2, mov.getRating());
            ps.setString(3, mov.getFilelink());
            ps.setFloat(4, mov.getImdb());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DALException("Could not update Movie");
        }
    }

    /**
     * Updates the lastview of a chosen movie. 
     * @param mov
     * @throws DALException 
     */
    public void updateLastView(Movie mov) throws DALException
    {
        try
        {
            Connection con = dbCon.getConnection();

            int id = mov.getId();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String Sql = "Update Movie set lastview = ? where id=?";
            PreparedStatement ps = con.prepareStatement(Sql);
            ps.setDate(1, sqlDate);
            ps.setInt(2, id);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex)
        {   
            ex.printStackTrace();
            throw new DALException("Could not update lastview");
        }

    }
    
    /**
     * Updates the categories for a movie in CatMovie, 
     * when they have been changed. 
     * @param list
     * @param mov
     * @throws DALException 
     */
    public void updateCategoryInCatMovie(Movie movie) throws DALException
    {
        try
        {
            Connection con = dbCon.getConnection();
            
            String sql = "DELETE FROM CatMovie WHERE MovieId = ?;";
            String sql2 = "INSERT INTO CatMovie VALUES (?,?);";
            
            PreparedStatement ps = con.prepareStatement(sql);
            PreparedStatement ps2 = con.prepareStatement(sql2);
            
            ps.setInt(1, movie.getId());
            ps.executeUpdate();
            ps.close();
            
            for (Category category : movie.getCategories())
            {
                ps2.setInt(1, category.getId());
                ps2.setInt(2, movie.getId());
                ps2.executeUpdate();
            }
            ps2.close();
            
            
        } catch (DALException ex)
        {
            throw new DALException("Could not update Category");
        } catch (SQLException ex)
        {
            throw new DALException("Could not update Category");
        }
        
        
    }
    
    /**
     * Gets the movies that the user hasn't seen in over 2 years, 
     * and has a personal rating under 6.
     * @return List<Movie>
     * @throws DALException 
     */
    public List<Movie> timeSinceLastview() throws DALException
    {
        try
        {

            Connection con = dbCon.getConnection();

            List<Movie> oldMovies = new ArrayList<Movie>();
            String sql = "SELECT * FROM Movie WHERE lastview < DATEADD(year,-2,GETDATE()) AND rating < 6;";
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String filelink = rs.getString("filelink");
                String name = rs.getString("name");
                int rating = rs.getInt("rating");
                Date lastview = rs.getDate("lastview");
                float imdb = rs.getFloat("imdb");

                Movie movie = new Movie(id, filelink, name, imdb, rating);
                oldMovies.add(movie);
            }

            return oldMovies;

        } catch (SQLException ex)
        {   
            ex.printStackTrace();
            throw new DALException("The list of movies could not be returned");
        }
    }

    public List<Movie> searchMovies(String searchName, int searchRating, List<Category> searchCategories) throws DALException {
        try {
            Connection con = dbCon.getConnection();
            
            List<Movie> movies = new ArrayList<>();
                
            // build sql for name
            String sqlName = "";
            if(!searchName.isEmpty()) {
                sqlName = " name LIKE ? AND";
            }
            
            // build sql for rating
            String sqlRating = "";
            if(searchRating > 0) {
                sqlRating += " IMDB > ? AND";
            }            
            
            // build sql for categories
            String sqlCategories = "";
            if(searchCategories.size() > 0) {
                String sqlNumCategories = "";
                for (int i = 0 ; i < searchCategories.size() ; i++ ) {
                    sqlNumCategories += "?,";
                }
                sqlNumCategories = sqlNumCategories.replaceAll(",$", "");
                sqlCategories += " id IN (SELECT MovieId FROM CatMovie WHERE CategoryId IN (" + sqlNumCategories + ") GROUP BY MovieId HAVING COUNT(*) = ?)";
            }
            
            // build sql for where clause
            String sqlWhere = "";
            if(!sqlName.isEmpty() || !sqlRating.isEmpty() || !sqlCategories.isEmpty()) {
                sqlWhere = " WHERE";
            }
            
            // build final sql statement
            String sql = "SELECT * FROM Movie" + sqlWhere + sqlName + sqlRating + sqlCategories +";";
            sql = sql.replaceAll(" AND;$", ";");

            System.out.println("SQL: " + sql); // debug (remove)
            PreparedStatement ps = con.prepareStatement(sql);
            
            int index = 1;
            
            // set name
            if(!searchName.isEmpty()) {
                ps.setString(index++, "%" + searchName + "%");
            }
            
            // set rating
            if(searchRating > 0) {
                ps.setInt(index++, searchRating);
            }
            
            // set category id(s) and amount
            if(searchCategories.size() > 0) {
                for(Category category : searchCategories) {
                    ps.setInt(index++, category.getId());
                }
                ps.setInt(index++, searchCategories.size());
            }
           
            // excute query
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                // get data
                int id = rs.getInt("id");
                String filelink = rs.getString("filelink");
                String name = rs.getString("name");
                int rating = rs.getInt("rating");
                Date lastview = rs.getDate("lastview");
                float imdb = rs.getFloat("imdb");

                // create object
                Movie movie = new Movie(id, filelink, name, imdb, rating);

                // get categories for movie
                String sql2 = "SELECT id, name FROM Category WHERE id IN (SELECT CategoryId FROM CatMovie WHERE MovieId = " + id + ");";
                               
                Statement statement2 = con.createStatement();
                ResultSet rs2 = statement2.executeQuery(sql2);

                // for each category
                while (rs2.next())
                {
                    int catId = rs2.getInt("id");
                    String catName = rs2.getString("name");

                    Category category = new Category(catId, catName);
                    
                    // add category to movie
                    movie.addCategory(category);
                }

                // add movie to list
                movies.add(movie);
            }

            return movies;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DALException("Could not search Movies");
        }
    }
}
