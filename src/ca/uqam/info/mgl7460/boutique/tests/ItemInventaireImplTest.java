package ca.uqam.info.mgl7460.boutique.tests;

import org.junit.jupiter.api.Test;

import ca.uqam.info.mgl7460.boutique.domain.Condition;
import ca.uqam.info.mgl7460.boutique.domain.ItemInventaire;
import ca.uqam.info.mgl7460.boutique.domain.Produit;
import ca.uqam.info.mgl7460.boutique.implementation.ItemInventaireImpl;
import ca.uqam.info.mgl7460.boutique.implementation.ProduitImpl;

import static org.junit.jupiter.api.Assertions.*;

public class ItemInventaireImplTest {

    @Test
    void testGetProduit() {
        Produit produit = new ProduitImpl("CODE123", "Description du produit", 10, 25.99f);
        ItemInventaire itemInventaire = new ItemInventaireImpl(produit, "INV001", Condition.GOOD);

        assertEquals(produit, itemInventaire.getProduit());
    }

    @Test
    void testGetNumeroInventaire() {
        Produit produit = new ProduitImpl("CODE123", "Description du produit", 10, 25.99f);
        ItemInventaire itemInventaire = new ItemInventaireImpl(produit, "INV001", Condition.GOOD);

        assertEquals("INV001", itemInventaire.getNumeroInventaire());
    }

    @Test
    void testGetCondition() {
        Produit produit = new ProduitImpl("CODE123", "Description du produit", 10, 25.99f);
        ItemInventaire itemInventaire = new ItemInventaireImpl(produit, "INV001", Condition.GOOD);

        assertEquals(Condition.GOOD, itemInventaire.getCondition());
    }

    @Test
    void testSetCondition() {
        Produit produit = new ProduitImpl("CODE123", "Description du produit", 10, 25.99f);
        ItemInventaire itemInventaire = new ItemInventaireImpl(produit, "INV001", Condition.GOOD);

        itemInventaire.setCondition(Condition.DAMAGED);

        assertEquals(Condition.DAMAGED, itemInventaire.getCondition());
    }
}
