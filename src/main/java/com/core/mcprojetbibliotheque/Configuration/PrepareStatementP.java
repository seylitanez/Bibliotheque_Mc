package com.core.mcprojetbibliotheque.Configuration;

import com.core.mcprojetbibliotheque.Model.Utilisateur;

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
    public ArrayList<Utilisateur> data() throws Exception {
        preparedStatement=connexion.getConnection().prepareStatement(sql);
        resultSet=preparedStatement.executeQuery();
        ArrayList<Utilisateur> list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new Utilisateur(resultSet.getString("nom"),
                    resultSet.getString("prenom"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getString("categorie")));
        }
        return list;
    }
}
