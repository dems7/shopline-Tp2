package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.InventaireEpuise;
import ca.uqam.info.mgl7460.boutique.domain.Produit;

public class ProduitImpl implements Produit {

    private String codeProduit;
    private String description;
    private int inventaire;
    private float prixUnitaire;

    public ProduitImpl(String codeProduit, String description, int inventaire, float prixUnitaire) {
        this.codeProduit = codeProduit;
        this.description = description;
        this.inventaire = inventaire;
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public String getCodeProduit() {
        return codeProduit;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getInventaire() {
        return inventaire;
    }

    @Override
    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    @Override
    public void setPrixUnitaire(float prix) {
        this.prixUnitaire = prix;
    }

    @Override
    public int incrementeInventaire(int increment) {
        this.inventaire += increment;
        return this.inventaire;
    }

    @Override
    public int decrementeInventaire(int decrement) throws InventaireEpuise {
        if (this.inventaire >= decrement) {
            this.inventaire -= decrement;
            return this.inventaire;
        } else {
            throw new InventaireEpuise(null, "Inventaire épuisé pour le produit " + this.codeProduit);
        }
    }
}
