package ca.uqam.info.mgl7460.boutique.implementation;

import java.util.*;

import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.LigneCommande;
import ca.uqam.info.mgl7460.boutique.domain.Panier;
import ca.uqam.info.mgl7460.boutique.domain.Produit;

public class PanierImpl implements Panier {

    private final Client client;
    private final Calendar date;
    private final Map<Produit, LigneCommande> lignesCommande;

    public PanierImpl(Client client) {
        this.client = client;
        this.date = Calendar.getInstance();
        this.lignesCommande = new HashMap<>();
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public Calendar getDate() {
        return date;
    }

    @Override
    public void ajouterProduit(Produit prod) {
        if (lignesCommande.containsKey(prod)) {
            incrementerQuantiteProduit(prod);
        } else {
            LigneCommande nouvelleLigne = new LigneCommandeImpl(prod, 1);
            lignesCommande.put(prod, nouvelleLigne);
        }
    }

    @Override
    public void enleverProduit(Produit prod) {
        lignesCommande.remove(prod);
    }

    @Override
    public void modifierQuantiteProduit(Produit prod, int quantite) {
        if (lignesCommande.containsKey(prod)) {
            LigneCommande ligne = lignesCommande.get(prod);
            ligne.modifierQuantite(quantite);
        }
    }

    @Override
    public int incrementerQuantiteProduit(Produit prod) {
        if (lignesCommande.containsKey(prod)) {
            LigneCommande ligne = lignesCommande.get(prod);
            int nouvelleQuantite = ligne.incrementerQuantite();
            return nouvelleQuantite;
        }
        return -1;
    }

    @Override
    public int decrementerQuantiteProduit(Produit prod) {
        if (lignesCommande.containsKey(prod)) {
            LigneCommande ligne = lignesCommande.get(prod);
            int nouvelleQuantite = ligne.decrementerQuantite();
            if (nouvelleQuantite == 0) {
                lignesCommande.remove(prod);
            }
            return nouvelleQuantite;
        }
        return -1;
    }

    @Override
    public int getQuantiteProduit(Produit prod) {
        return lignesCommande.containsKey(prod) ? lignesCommande.get(prod).getQuantite() : -1;
    }

    @Override
    public float getValeurPanier() {
        float valeurPanier = 0;
        for (LigneCommande ligne : lignesCommande.values()) {
            valeurPanier += ligne.getPrixUnitaire() * ligne.getQuantite();
        }
        return valeurPanier;
    }

    @Override
    public Commande creerCommande() {
        Commande commande = client.creerCommande();
        for (LigneCommande ligne : lignesCommande.values()) {
            commande.ajouteLigneCommande(ligne.getProduit(), ligne.getQuantite());
        }
        return commande;
    }

    @Override
    public void reinitialiser() {
        lignesCommande.clear();
    }

    @Override
    public Iterator<LigneCommande> getLignesCommande() {
        return lignesCommande.values().iterator();
    }
}
