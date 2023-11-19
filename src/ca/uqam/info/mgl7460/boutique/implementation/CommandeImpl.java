package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.*;

import java.util.*;

public class CommandeImpl implements Commande {

    private static final String PREFIXE_NUMERO_COMMANDE = "CMD";
    private static int numeroCommandeSuivant = 1;

    private final String numeroCommande;
    private final Calendar dateCommande;
    private Adresse adresseDeLivraison;
    private final Client client;
    private final Map<Produit, Integer> quantites;
    private final List<LigneCommande> lignesCommandes;
    private Facture facture;
    private final List<Livraison> livraisons;

    public CommandeImpl(Client client) {
        this.numeroCommande = PREFIXE_NUMERO_COMMANDE + numeroCommandeSuivant++;
        this.dateCommande = Calendar.getInstance();
        this.client = client;
        this.quantites = new HashMap<>();
        this.lignesCommandes = new ArrayList<>();
        this.livraisons = new ArrayList<>();
    }

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public Calendar getDateCommande() {
        return dateCommande;
    }

    @Override
    public String getNumeroCommande() {
        return numeroCommande;
    }

    @Override
    public Adresse getAdresseDeLivraison() {
        return adresseDeLivraison != null ? adresseDeLivraison : client.getAdresse();
    }

    @Override
    public void setAdresseDeLivraison(Adresse adresse) {
        this.adresseDeLivraison = adresse;
    }

    @Override
    public Iterator<LigneCommande> getLignesCommandes() {
        return Collections.unmodifiableList(lignesCommandes).iterator();
    }

    @Override
    public int getQuantiteCommandee(Produit produit) {
        return quantites.getOrDefault(produit, 0);
    }

    @Override
    public void ajouteLigneCommande(Produit produit, int quantite) {
        if (quantite > 0) {
            quantites.put(produit, quantite);
            LigneCommande ligneCommande = new LigneCommandeImpl(produit, quantite);
            lignesCommandes.add(ligneCommande);
        }
    }

    @Override
    public Iterator<Livraison> getLivraisons() {
        return Collections.unmodifiableList(livraisons).iterator();
    }

    @Override
    public Livraison creerLivraison() {
        Livraison livraison = new LivraisonImpl(this, client);
        livraisons.add(livraison);
        return livraison;
    }

    @Override
    public Facture creerFacture() {
        if (facture == null) {
            facture = BoutiqueImpl.getBoutiqueSingleton().getFabriqueBoutique().creerFacture(this);
        }
        return facture;
    }

    @Override
    public Facture getFacture() {
        if (facture == null) {
            facture = BoutiqueImpl.getBoutiqueSingleton().getFabriqueBoutique().creerFacture(this);
        }
        return facture;
    }

    @Override
    public void initialiserAvecPanier(Panier panier) {
    Iterator<LigneCommande> iterator = panier.getLignesCommande();
    while (iterator.hasNext()) {
        LigneCommande lignePanier = iterator.next();
        ajouteLigneCommande(lignePanier.getProduit(), lignePanier.getQuantite());
    }
}

}
