
    public abstract class Abonne {
        protected String nom;
        protected String prenom;
        protected int nbEmprunts;

        public Abonne(String nom, String prenom) {
            this.nom = nom;
            this.prenom = prenom;
            this.nbEmprunts = 0;
        }

        public String getNom() {
            return this.nom;
        }

        public String getPrenom() {
            return this.prenom;
        }

        public int getNbEmprunts() {
            return this.nbEmprunts;
        }

        public void setNbEmprunts(int nbEmprunts) {
            this.nbEmprunts = nbEmprunts;
        }

    }
