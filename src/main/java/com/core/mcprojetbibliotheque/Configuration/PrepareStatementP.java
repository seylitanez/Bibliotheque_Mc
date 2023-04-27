package com.core.mcprojetbibliotheque.Configuration;

import com.core.mcprojetbibliotheque.Model.Abonne;
import com.core.mcprojetbibliotheque.Model.Utilisateur;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PrepareStatementP {
    private DbConnexion connexion;
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PrepareStatementP(String sql) throws Exception {
        connexion=new DbConnexion();
        this.sql = sql;
    }
    public ArrayList<Abonne> data() throws Exception {
        preparedStatement=connexion.getConnection().prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        ArrayList<Abonne> list=new ArrayList<>();
        while (resultSet.next()){
            Abonne utilisateur=new Abonne(resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("categorie"),null,
                    new File(resultSet.getString("certificat")));

            utilisateur.setId(resultSet.getInt("id"));
            list.add(utilisateur);
        }
        return list;
    }
}
