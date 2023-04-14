package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import com.core.mcprojetbibliotheque.Model.Abonne;

import java.sql.*;
import java.util.Date;

import static com.core.mcprojetbibliotheque.Utils.Constantes.TROUVE_UTILISATEUR;

public class ConnectionService {
    private DbConnexion dbConnexion;
    public ConnectionService() throws Exception{
        dbConnexion=new DbConnexion();
    }
    public void login(String username, String password) {
        PreparedStatement statement = null;

        try (var cnx =dbConnexion.getConnection()){

            System.out.println("connecte");
            statement = cnx.prepareStatement(TROUVE_UTILISATEUR);
            statement.setString(1, username);
            statement.setString(2, password);

            // resultSet me renvoi le resultat de la requete select
            ResultSet resultSet = statement.executeQuery();
            System.out.println(resultSet);
            while (resultSet.next()) {
                var  us = resultSet.getString("username");
                var  pss = resultSet.getString("password");
                System.out.println(us+' '+pss);
            }


        }catch (Exception e){

            e.getMessage();
        }


    }}
