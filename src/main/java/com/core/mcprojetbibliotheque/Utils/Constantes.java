package com.core.mcprojetbibliotheque.Utils;

public class Constantes {
    public static String AJOUT_UTILISATEUR="INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `username`, `password`, `email`, `categorie`,`certificat`) VALUES (null, ?, ?,?, ?, ?,?,?);";
    public static String TROUVE_UTILISATEUR="SELECT  `id`, `nom`, `prenom`, `username`, `password`, `email`, `categorie`,`certificat` FROM `utilisateur` WHERE `username`= ? AND `password`= ? ;";
}