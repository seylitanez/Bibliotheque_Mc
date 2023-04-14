package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.HelloApplication;
import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Service.MyListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class AbonnesDashboard implements Initializable {

    @FXML
    private GridPane grid;

    public Label getTitre() {
        return titre;
    }

    @FXML
    private Label titre;


    private List<Livre> livres=new ArrayList();


    private MyListener listener;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println();

        livres.add(new Livre("titre 1","auteur1",5,"4216","https://www.oumma-design.fr/wp-content/uploads/2015/12/LE_DEVOIR_modifi%C3%A9-1.jpg"));
        livres.add(new Livre("titre 2","auteur2",4,"4216","https://www.oumma-design.fr/wp-content/uploads/2015/12/LE_DEVOIR_modifi%C3%A9-1.jpg"));
//        livres.add(new Livre("titre 3","auteur1",2,"4216","target/classes/com/core/mcprojetbibliotheque/images/inscription.png"));
//        livres.add(new Livre("titre 4","auteur1",1,"4216","target/classes/com/core/mcprojetbibliotheque/images/plus.png"));

        listener=new MyListener() {
            @Override
            public void OnClickList(Livre livre) {

                System.out.println(livre.getTitre());
                System.out.println(livre.getTitre());
                selected(livre);

            }
        };


            int ligne=0;

        for (int colonne = 0; colonne < livres.size(); colonne++) {
            try {
            FXMLLoader fxmlLoader=new FXMLLoader(HelloApplication.class.getResource("LivreItem.fxml"));
            AnchorPane anchorPane= fxmlLoader.load();
            LivreItem livreItem=fxmlLoader.getController();

            livreItem.setData(livres.get(colonne), listener);



//                livreItem.getPhoto().setImage(new Image(livres.get(colonne).getPhoto()));



//            livreItem.selectioner(new ActionEvent());





            if (colonne%3==0) {
                ligne++;
            }



                grid.add(anchorPane,(colonne%3)+1,ligne);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }


    private void selected(Livre livre){
        titre.setText(livre.getTitre());
    }
    public void dragged(MouseEvent mouseEvent) {
    }

    public void presse(MouseEvent mouseEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }

    public void cache(ActionEvent actionEvent) {
    }
}
