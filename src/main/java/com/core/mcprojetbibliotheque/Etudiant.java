package com.core.mcprojetbibliotheque;

    public class Etudiant extends Abonne {
        private boolean externe;

        public Etudiant(String nom, String prenom, boolean externe) {
            super(nom,prenom);
            this.externe = externe;


        }

        @Override
        public boolean peutEmprunter() {
            return false;
        }
    }
