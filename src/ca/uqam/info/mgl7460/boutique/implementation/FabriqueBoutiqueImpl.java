package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.Adresse;
import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.FabriqueBoutique;
import ca.uqam.info.mgl7460.boutique.domain.Facture;
import ca.uqam.info.mgl7460.boutique.domain.InventaireEpuise;
import ca.uqam.info.mgl7460.boutique.domain.ItemInventaire;
import ca.uqam.info.mgl7460.boutique.domain.LigneCommande;
import ca.uqam.info.mgl7460.boutique.domain.Livraison;
import ca.uqam.info.mgl7460.boutique.domain.Paiement;
import ca.uqam.info.mgl7460.boutique.domain.Panier;
import ca.uqam.info.mgl7460.boutique.domain.Produit;
import ca.uqam.info.mgl7460.boutique.domain.Salutation;
import netscape.javascript.JSObject;

public class FabriqueBoutiqueImpl implements FabriqueBoutique {

    @Override
    public Client creerClient(String prenom, String nom, Salutation salutation, Adresse adresse) {
        return new ClientImpl(prenom, nom, salutation, adresse);
    }

    @Override
    public Adresse creerAdresse(JSObject jsonObject) {
        // Implémentez la logique de création d'adresse à partir du JSON
        return null;
    }

    @Override
    public Panier creerPanierPourClient(Client client) {
        return new PanierImpl(client);
    }

    @Override
    public Commande creerCommande(Client client) {
        return new CommandeImpl(client);
    }

    @Override
    public Produit creerProduit(String code, String description, int inventaire, float prixUnitaire) {
        return new ProduitImpl(code, description, inventaire, prixUnitaire);
    }

    @Override
    public Paiement creerPaiement(Facture facture, Client payeur, float montant) {
        return new PaiementImpl(payeur, facture, montant);
    }

    @Override
    public Livraison creerLivraison(Commande com, Client destinataire) {
        return new LivraisonImpl(com, destinataire);
    }

    @Override
    public Facture creerFacture(Commande com) {
        return new FactureImpl(com);
    }

    @Override
    public ItemInventaire creerItemInventaire(Produit produit, String numeroInventaire) throws InventaireEpuise {
        return new ItemInventaireImpl(produit, numeroInventaire);
    }

    @Override
    public LigneCommande creerLigneCommande(Produit produit, int quantite) {
        return new LigneCommandeImpl(produit, quantite);
    }

    @Override
    public Client creerClient(String prenom, String nom, Salutation sal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'creerClient'");
    }
}
