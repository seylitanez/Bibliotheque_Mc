package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;

import java.io.FileInputStream;
import java.sql.PreparedStatement;

import static com.core.mcprojetbibliotheque.Utils.Constantes.AJOUT_UTILISATEUR;

public class ConnectionService {
    private DbConnexion dbConnexion;
    public ConnectionService() throws Exception{
        dbConnexion=new DbConnexion();
    }
    public void login(String username, String password) throws Exception{
        try (var cnx =dbConnexion.getConnection()){
            PreparedStatement statement = cnx.prepareStatement(AJOUT_UTILISATEUR);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setBlob(3,new FileInputStream("C:\\Users\\lyes\\Desktop\\one_piece.jpg"));
            statement.execute();
        }catch (Exception e){e.printStackTrace();}
    }
}
