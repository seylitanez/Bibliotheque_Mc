package com.core.mcprojetbibliotheque.Controller;

import com.core.mcprojetbibliotheque.HelloApplication;
import com.core.mcprojetbibliotheque.Model.Livre;
import com.core.mcprojetbibliotheque.Service.MyListener;
import com.core.mcprojetbibliotheque.Service.WindowEffect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private AnchorPane main;
    @FXML
    private GridPane grid;
    public Label getTitre() {
        return titre;
    }
    @FXML
    private Label titre;
    @FXML
    private ImageView photo;
    private List<Livre> livres=new ArrayList();
    private MyListener listener;
    private WindowEffect effect;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        effect=new WindowEffect(main);
        System.out.println();
        livres.add(new Livre("titre 1","auteur1",5,"4216","https://www.oumma-design.fr/wp-content/uploads/2015/12/LE_DEVOIR_modifi%C3%A9-1.jpg"));
        livres.add(new Livre("titre 2","auteur2",4,"4216","https://i.pinimg.com/originals/db/38/8d/db388d57cb3c1f9f4a8e3de7a7627510.jpg"));
        livres.add(new Livre("titre 2","auteur2",4,"4216","https://www.babelio.com/users/blobid1608755607567.jpg"));
        livres.add(new Livre("titre 2","auteur2",4,"4216","https://i1.wp.com/www.prom-auteur.com/wp-content/uploads/2018/01/couv-ebook-ok.jpg?w=1500"));
        livres.add(new Livre("titre 2","auteur2",4,"4216","https://club-stephenking.fr/wp-content/uploads/2020/06/stephenking-marcheoucreve-lelivredepoche-couverture2020.jpg"));
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
        //asynchrone pour recuperer la photo
        new Thread(()->{
        photo.setImage(new Image(livre.getPhoto()));
        }).start();
    }
    public void exit(ActionEvent e) {
        effect.exit(e);
    }
    public void dragged(MouseEvent e) {
        effect.dragged(e);
    }
    public void presse(MouseEvent e) {
        effect.pressed(e);
    }
    public void cache(ActionEvent e) {
        effect.cache(e);
    }
    public void back(ActionEvent e) throws Exception {
        effect.switchStage(e,"login.fxml");
    }
}
