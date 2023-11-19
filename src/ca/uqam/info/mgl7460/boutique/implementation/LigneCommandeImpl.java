package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.LigneCommande;
import ca.uqam.info.mgl7460.boutique.domain.Produit;

public class LigneCommandeImpl implements LigneCommande {

    private final Produit produit;
    private int quantite;
    private float prixUnitaire;

    public LigneCommandeImpl(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
        this.prixUnitaire = produit.getPrixUnitaire();
    }

    @Override
    public Produit getProduit() {
        return produit;
    }

    @Override
    public int getQuantite() {
        return quantite;
    }

    @Override
    public void modifierQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    @Override
    public void setPrixUnitaire(float prix) {
        this.prixUnitaire = prix;
    }
/* 
    @Override
    public int incrementerQuantite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incrementerQuantite'");
    }

    @Override
    public int decrementerQuantite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decrementerQuantite'");
    }*/
}
