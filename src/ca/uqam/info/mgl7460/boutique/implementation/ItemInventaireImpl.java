package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.Condition;
import ca.uqam.info.mgl7460.boutique.domain.ItemInventaire;
import ca.uqam.info.mgl7460.boutique.domain.Produit;

public class ItemInventaireImpl implements ItemInventaire {

    private final Produit produit;
    private final String numeroInventaire;
    private Condition condition;

    public ItemInventaireImpl(Produit produit, String numeroInventaire, Condition condition) {
        this.produit = produit;
        this.numeroInventaire = numeroInventaire;
        this.condition = Condition.GOOD; // Condition par défaut
    }

    @Override
    public Produit getProduit() {
        return produit;
    }

    @Override
    public String getNumeroInventaire() {
        return numeroInventaire;
    }

    @Override
    public Condition getCondition() {
        return condition;
    }

    @Override
    public void setCondition(Condition cond) {
        this.condition = cond;
    }
}
