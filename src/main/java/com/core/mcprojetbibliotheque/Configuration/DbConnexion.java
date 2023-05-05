package com.core.mcprojetbibliotheque.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
*/
public class DbConnexion {
	static String user = "root";
	static String password ="";
	static String url ="jdbc:mysql://localhost/sql7614116";
	static String driver ="com.mysql.jdbc.Driver";
	
	
	
	
	
	
	
	
	
/*    private String driver,url,usr,pwd;
    public DbConnexion() throws IOException {
        FileInputStream propFile=new FileInputStream("src/main/resources/application.properties");
        Properties properties=new Properties();
        properties.load(propFile);
        this.driver=properties.getProperty("com.mysql.jdbc.Driver");
        this.url=properties.getProperty("jdbc:mysql://localhost/sql7614116");
        this.usr=properties.getProperty("root");
        this.pwd=properties.getProperty("");
    }
 */
    public Connection getConnection()  {
    	Connection connection = null ;
    	try {
			Class.forName(driver);
			try {
				connection =DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    return connection;
    }
}