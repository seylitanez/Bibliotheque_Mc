package com.core.mcprojetbibliotheque.Utils;

public class Constantes {
    public static String AJOUT_UTILISATEUR="INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `username`, `password`, `email`, `categorie`,`certificat`,`valide`) VALUES (null, ?, ?,?, ?, ?,?,?,0);";
    public static String TROUVE_UTILISATEUR="SELECT * FROM `utilisateur` WHERE `username`= ? AND `password`= ? ;";
}