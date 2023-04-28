package com.core.mcprojetbibliotheque.Utils;

public class Constantes {
    public static String AJOUT_UTILISATEUR="INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `username`, `password`, `email`, `categorie`,`certificat`,`valide`) VALUES (null, ?, ?,?, ?, ?,?,?,0);";
    public static String TROUVE_UTILISATEUR="SELECT * FROM `utilisateur` WHERE `username`= ? AND `password`= ? and `valide`=1;";
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






}