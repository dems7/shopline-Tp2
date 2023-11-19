package ca.uqam.info.mgl7460.boutique.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.uqam.info.mgl7460.boutique.domain.Adresse;
import ca.uqam.info.mgl7460.boutique.domain.Client;
import ca.uqam.info.mgl7460.boutique.domain.Commande;
import ca.uqam.info.mgl7460.boutique.domain.Paiement;
import ca.uqam.info.mgl7460.boutique.domain.Panier;
import ca.uqam.info.mgl7460.boutique.domain.Province;
import ca.uqam.info.mgl7460.boutique.domain.Salutation;

public class ClientImpl implements Client {

    private final Identification identification;
    private Adresse adresse;
    private final List<Commande> commandes;
    private final List<Paiement> paiements;
    private Panier panier;

    public ClientImpl(String prenom, String nom, Salutation salutation, String numeroPorte, 
                        String numeroRue, String nomRue, String ville, String codePostal, 
                        Province province) {
        this.identification = new Identification(prenom, nom, salutation);
        this.commandes = new ArrayList<>();
        this.paiements = new ArrayList<>();
        this.adresse = new Adresse(numeroPorte, numeroRue, nomRue, ville, codePostal, province);
    }
    public ClientImpl(String prenom, String nom, Salutation salutation) {
        this.identification = new Identification(prenom, nom, salutation);
        this.commandes = new ArrayList<>();
        this.paiements = new ArrayList<>();
    }

    @Override
    public Identification getIdentification() {
        return identification;
    }

    @Override
    public Adresse getAdresse() {
        return adresse;
    }

    @Override
    public void setAdresse(Adresse nouvelle) {
        this.adresse = nouvelle;
    }

    @Override
    public Panier creerPanier() {
        this.panier = BoutiqueImpl.getBoutiqueSingleton().demarrerSessionClient(this);
        return panier;
    }

    @Override
    public Panier getPanier() {
        return panier;
    }

    @Override
    public void ajouterCommande(Commande commande) {
        commandes.add(commande);
    }

    @Override
    public void retirerCommande(Commande commande) {
        commandes.remove(commande);
    }

    @Override
    public Iterator<Commande> getCommandes() {
        return commandes.iterator();
    }

    @Override
    public Iterator<Paiement> getPaiements() {
        return paiements.iterator();
    }

    @Override
    public void ajouterPaiement(Paiement paiement) {
        paiements.add(paiement);
    }

    @Override
    public Iterator<Paiement> getPaiementsPourCommande(Commande commande) {
        List<Paiement> paiementsPourCommande = new ArrayList<>();
        for (Paiement paiement : paiements) {
            if (paiement.getFacture().getCommande().equals(commande)) {
                paiementsPourCommande.add(paiement);
            }
        }
        return paiementsPourCommande.iterator();
    }

   /* @Override
    public Commande creerCommande() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'creerCommande'");
    }*/ 
}
