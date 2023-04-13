package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import com.core.mcprojetbibliotheque.Model.Abonne;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.util.Date;

import static com.core.mcprojetbibliotheque.Utils.Constantes.AJOUT_UTILISATEUR;

public class ConnectionService {
    private DbConnexion dbConnexion;
    public ConnectionService() throws Exception{
        dbConnexion=new DbConnexion();
    }
    public Abonne login(String username, String password) throws Exception{
        try (var cnx =dbConnexion.getConnection()){
            PreparedStatement statement = cnx.prepareStatement(AJOUT_UTILISATEUR);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3,"photo/url/....");
            statement.execute();
        }catch (Exception e){e.printStackTrace();}
        return new Abonne("","","","",new Date());
    }
}
