/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import privatemoviecollection.dal.dalException.DALException;

/**
 *
 * @author zilot
 */
public class DatabaseConnector
{
    private SQLServerDataSource dataSource;
    
    /*
    * reads our DBsettings file with input that you need to connect to the database
    */
    public DatabaseConnector() throws DALException
    {
        try{
        Properties props = new Properties();
        props.load(new FileReader("DBSettings.db"));
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));        
        dataSource.setServerName(props.getProperty("server"));
        }
        catch (IOException ex) {
            
        throw new DALException("forkert input, check username or password in file");
        }
    }
    
    /*
    * Tries to connect to get a connection to our database. 
    * @return Connection
    */
    public Connection getConnection() throws DALException  
    {
        try{
        return dataSource.getConnection();
        }
        catch (SQLServerException ex)
        {
        throw new DALException("Kunne ikke oprette forbindelse til serveren"); 
        }
    }
        
    
    
}
