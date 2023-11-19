package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.*;

import java.util.*;

public class LivraisonImpl implements Livraison {

    private final Calendar dateCreation;
    private final Commande commande;
    private Client destinataire;
    private final List<ItemInventaire> contenuLivraison;
    private Adresse adresseDeLivraison;
    private StatutLivraison statutLivraison;
    private Calendar dateLivraison;

    public LivraisonImpl(Commande commande, Client destinataire) {
        this.dateCreation = Calendar.getInstance();
        this.commande = commande;
        this.destinataire = destinataire;
        this.contenuLivraison = new ArrayList<>();
        this.adresseDeLivraison = destinataire.getAdresse();
        this.statutLivraison = StatutLivraison.EN_PREPARATION;
        this.dateLivraison = null;
    }

    @Override
    public Calendar getDateCreation() {
        return dateCreation;
    }

    @Override
    public Commande getCommande() {
        return commande;
    }

    @Override
    public Client getClient() {
        return destinataire;
    }

    @Override
    public void setClient(Client destinataire) {
        this.destinataire = destinataire;
    }

    @Override
    public void ajouterItemInventaire(ItemInventaire item) {
        contenuLivraison.add(item);
    }

    @Override
    public void retirerItemInventaire(ItemInventaire item) {
        contenuLivraison.remove(item);
    }

    @Override
    public Iterator<ItemInventaire> getContenuLivraison() {
        return Collections.unmodifiableList(contenuLivraison).iterator();
    }

    @Override
    public void setAdresseDeLivraison(Adresse adresse) {
        this.adresseDeLivraison = adresse;
    }

    @Override
    public Adresse getAdresseDeLivraison() {
        return adresseDeLivraison;
    }

    @Override
    public StatutLivraison getStatutLivraison() {
        return statutLivraison;
    }

    @Override
    public void modifierStatutLivraison(StatutLivraison nouveauStatut) {
        this.statutLivraison = nouveauStatut;
        if (nouveauStatut == StatutLivraison.LIVREE) {
            this.dateLivraison = Calendar.getInstance();
        }
    }

    @Override
    public Calendar getDateLivraison() {
        return dateLivraison;
    }

    @Override
    public void setDateLivraison(Calendar date) {
        this.dateLivraison = date;
    }
}
 