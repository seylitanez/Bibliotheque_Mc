package com.core.mcprojetbibliotheque.Service;

import com.core.mcprojetbibliotheque.Configuration.DbConnexion;
import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Utils.Constantes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.core.mcprojetbibliotheque.Utils.Constantes.*;

public class LivreService {

    private DbConnexion dbConnexion;

    public LivreService() throws IOException {
        dbConnexion=new DbConnexion();

    }

    public List<Livre> getAllLivres() throws SQLException, ClassNotFoundException, IOException {
        var connection= dbConnexion.getConnection();

        var preparedStatement=connection.prepareStatement(LISTER_LIVRES);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList list=new ArrayList<>();

        while (resultSet.next()){
            list.add(new Livre(resultSet.getString("titre"),resultSet.getString("auteur"),resultSet.getInt("nbExemplaires"),resultSet.getInt("codeRayon"),resultSet.getString("photo")));
        }


        return list;
    }

    public List<Livre> searchLivres(String motCle) throws SQLException, ClassNotFoundException {
        var connection= dbConnexion.getConnection();
        var preparedStatement=connection.prepareStatement(CHERCHER_LIVRE);
        preparedStatement.setString(1,"%"+motCle+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList list=new ArrayList<>();
        while (resultSet.next()){
            list.add(new Livre(resultSet.getString("titre"),resultSet.getString("auteur"),resultSet.getInt("nbExemplaires"),resultSet.getInt("codeRayon"),resultSet.getString("photo")));
        }

        return list;
    }
}
