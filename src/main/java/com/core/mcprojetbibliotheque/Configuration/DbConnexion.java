package com.core.mcprojetbibliotheque.Configuration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnexion {
    private String dirver,url,usr,pwd;
    public DbConnexion() throws IOException {
        FileInputStream propFile=new FileInputStream("src/main/resources/application.properties");
        Properties properties=new Properties();
        properties.load(propFile);
        this.dirver=properties.getProperty("DATABASE_DRIVER");
        this.url=properties.getProperty("DATABASE_URL");
        this.usr=properties.getProperty("USERNAME");
        this.pwd=properties.getProperty("PASSWORD");
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dirver);
        return DriverManager.getConnection(url,usr,pwd);
    }
}
