package com.core.mcprojetbibliotheque.Utils;

public class Constantes {
    public static String AJOUT_UTILISATEUR="INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `username`, `password`, `email`, `categorie`,`certificat`,`valide`) VALUES (null, ?, ?,?, ?, ?,?,?,0);";
    public static String TROUVE_UTILISATEUR="SELECT * FROM `utilisateur` WHERE `email`= ? AND `password`= ? and `valide`=1;";
    public static String LISTER_LIVRES="SELECT * FROM `livre`;";
    public static String CHERCHER_LIVRE="SELECT * FROM `livre`where titre like ?;";
    public static String TROUVE_UTILISATEUR_NOAUTH="SELECT * FROM `utilisateur` WHERE  `valide`= 0 ;";
    public static String MODIFIER_UTILISATEUR="UPDATE utilisateur SET valide = '1' WHERE id= ? ;";

    public static String NOMBRE_ABONNONNES_SELON_TYPE="SELECT COUNT(*) FROM utilisateur WHERE categorie = ?;";
    public static String LISTE_LIVRES_RESERVE_PAR_UTILISATEUR_SELON_ID="SELECT livre.*\n" +
            "FROM utilisateur\n" +
            "JOIN reservation AS r ON utilisateur.id = r.id_utilisateur\n" +
            "JOIN livre ON livre.id = r.id_livre\n" +
            "WHERE r.id_utilisateur = ?;";
    
    
    public static String TROUVE_UTILISATEURWITHEMAIL="SELECT * FROM `utilisateur` WHERE `email`= ?;";
  
    public static String TROUVE_ID_AND_DATE="SELECT * FROM `reservation`WHERE `accepté`=?;";
    public static String TROUVE_INFORMATION_UTILISATEUR="SELECT * FROM `utilisateur` WHERE `id`= ?;";

    public static String TROUVE_INFORMATION_LIVRE="SELECT * FROM `livre` WHERE `id`= ?;";

    public static String TROUVE_ID_LIVRE="SELECT * FROM `livre` WHERE `titre`= ? AND `auteur` = ?;";
    public static String TROUVE_ID_UILISATEUR="SELECT * FROM `utilisateur` WHERE `email`= ?;";
    public static String ADD_RESERVATION="INSERT INTO `reservation` (id_utilisateur,id_livre,date) VALUES (?, ?, ?);";
    //public static String UPDATE_NBR_RESERVATION = "UPDATE utilisateur SET nbrReservation = nbrReservation + 1 WHERE id = ?;";
    public static String UPDATE_RESERVATION="UPDATE reservation SET accepté = ?,DateAcceptation =  NOW() WHERE id_utilisateur = ? AND id_livre = ? AND date =?;";
    public static String DELETE_RESERVATION="DELETE FROM reservation WHERE id_utilisateur = ? AND id_livre = ? AND date = ?;";
    public static String CHERCHER_TITLE_LIVRE="SELECT * FROM `livre`where titre = ? AND auteur = ? ;";
    public static String ADD_LIVRE="INSERT INTO livre (titre, auteur, nbExemplaires, codeRayon, photo, filiere, dateAjout)VALUES (?, ?, ?, ?, null, ?,NOW());";
    public static String UPDATE_LIVRE="UPDATE livre SET titre = ?, auteur = ?,nbExemplaires = ?,codeRayon = ?,photo= ?,filiere = ? WHERE id = ?";
    public static String SUPPRIMER_LIVRE="DELETE FROM livre WHERE id = ?;";
    public static String AJOUTEREXEMPLAIRE = "UPDATE livre SET nbExemplaires = nbExemplaires + ? WHERE id = ?";
    
    public static String LISTER_EMPRUNT ="SELECT * FROM emprunt ;";
    public static String LISTER_EMPRUNT_NON_RESTITUER ="SELECT * FROM emprunt WHERE dateRestitution IS NULL ;";
    
    public static String UPDATE_EMPRUNT_RESTITUTION="UPDATE emprunt set dateRestitution = Now() where idEmprunt = ?;";
    public static String UPDATE_EXEMPLAIRE_NEMBER="UPDATE livre SET nbExemplaires = nbExemplaires + 1 where titre = ? and auteur = ?;";
    public static String DECREMENTER_NOMBRE_EXEMPLAIRE = "UPDATE livre SET nbExemplaires = nbExemplaires - 1 WHERE id = ?";
    public static String INCREMENTER_NOMBRE_EXEMPLAIRE = "UPDATE livre SET nbExemplaires = nbExemplaires - +1 WHERE id = ?";
    public static String AJOUTER_EMPRUNT = "INSERT INTO emprunt (idUtilisateur, idLivre, dateEmprunt) VALUES (?, ?, NOW());";
   
    public static String DECREMENTER_NOMBRE_EMPRUNT ="UPDATE utilisateur SET nbrEmprunt = nbrEmprunt - 1 WHERE email = ?;";
    public static String INCREMENTER_NOMBRE_EMPRUNT = "UPDATE utilisateur SET nbrEmprunt = nbrEmprunt + 1 WHERE id = ?;";
    public static String INCREMENTER_NOMBRE_RESERVATION = "UPDATE utilisateur SET nbrReservation = nbrReservation + 1 WHERE id = ?;";
    public static String DECREMENTER_NOMBRE_RESERVATION = "UPDATE utilisateur SET nbrReservation = nbrReservation - 1 WHERE id = ?;";
    public static String ADD_DEMANDE ="UPDATE emprunt set demandeProlonger = 1 WHERE idEmprunt = ?";
    public static String ACCEPTER_DEMANDE="UPDATE emprunt set prolonge = 1 WHERE idEmprunt = ?;";
    public static String REFFUSER_DEMANDE = "UPDATE emprunt set prolongéRufusé = 1 WHERE idEmprunt = ?;";
    public static String PENALISER_UTILISATEUR = "UPDATE utilisateur SET DateFinPenalisation = ? WHERE email = ?;";
    public static String ADD_PENALISATION = "UPDATE emprunt set penalisé = 1 WHERE idEmprunt = ?;";
    public static String TROUVE_RESERVATION ="SELECT * FROM reservation WHERE id_utilisateur = ? AND id_livre = ? AND date = ?";
    public static String ALL_UTILISATEUR = "SELECT * FROM utilisateur WHERE categorie NOT IN ('Gestionaire', 'Bibliothecaire')";
    public static String DELETE_UTILISATEUR ="DELETE FROM utilisateur WHERE email = ? ;";
    public static String UPDATE_PAYEMENT = "UPDATE utilisateur set dateFinPyement = NULL WHERE email = ?;";		
    public static String UPDATE_PAYEMENT2 = "UPDATE utilisateur set dateFinPyement = ? WHERE email = ?;";		

    public static String CHECK_EMAIL_IF_EXIST="SELECT * FROM utilisateur where email = ?;";
    public static String ANNULRE_PENALISER_UTILISATEUR ="UPDATE utilisateur SET DateFinPenalisation = NULL WHERE email = ?;";
    public static String NOMBRE_EXEMPLAIRE ="SELECT * FROM livre WHERE id = ?;";
}