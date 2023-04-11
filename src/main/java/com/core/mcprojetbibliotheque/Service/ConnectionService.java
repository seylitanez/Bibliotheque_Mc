package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;

import java.sql.PreparedStatement;

import static com.core.mcprojetbibliotheque.Utils.Constantes.AJOUT_UTILISATEUR;

public class ConnectionService {
    private DbConnexion dbConnexion;
    public ConnectionService() throws Exception{
        dbConnexion=new DbConnexion();
    }
    public void login(String username, String password) throws Exception{
        var cnx = dbConnexion.getConnection();
        PreparedStatement statement = cnx.prepareStatement(AJOUT_UTILISATEUR);
        statement.setString(1, username);
        statement.setString(2, password);
        System.out.println("utilisateur ajoute avec succes");
    }
}
