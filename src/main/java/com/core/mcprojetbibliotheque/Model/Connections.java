package com.core.mcprojetbibliotheque.Model;
import java.io.*;
import java.sql.*;
import java.util.*;

public class Connections {
    private String login,password,url;
    private Properties prop;
    private Connection connection;

    public Connections() throws Exception{
        prop =new Properties();
        try(FileInputStream fis=new FileInputStream("Conf.properties")){
            prop.load(fis);
        }
    }
    private void getProperties() throws Exception{
        Class.forName(prop.getProperty("jdbc.driver.class"));
        login=prop.getProperty("jdbc.login");
        url=prop.getProperty("jdbc.url");
        password=prop.getProperty("jdbc.password");
    }
    public void conection()throws Exception{
        getProperties();
        connection= DriverManager.getConnection(url,login,password);
    }
    public void desConection() throws Exception {
        connection.close();
    }
}
