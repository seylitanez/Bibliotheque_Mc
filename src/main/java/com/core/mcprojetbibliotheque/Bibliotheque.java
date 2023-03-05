package com.core.mcprojetbibliotheque;
public class Bibliotheque {
    private List<Livre> livres;
    private List<Abonne> abonnes;
    private Gestionnaire gestionnaire;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
        this.abonnes = new ArrayList<>();
        this.gestionnaire = new Gestionnaire();
    }

    public void ajouterLivre(Livre livre) {
        this.livres.add(livre);
    }

    public void ajouterAbonne(Abonne abonne) {
        this.abonnes.add(abonne);
    }

    public void retirerLivre(Livre livre) {
        this.livres.remove(livre);
    }

    public void retirerAbonne(Abonne abonne) {
        this.abonnes.remove(abonne);
    }

    public List<Livre> getLivres() {
        return this.livres;
    }

    public List<Abonne> getAbonnes() {
        return this.abonnes;
    }

    public Gestionnaire getGestionnaire() {
        return this.gestionnaire;
    }
}

