package ca.uqam.info.mgl7460.boutique.implementation;

import ca.uqam.info.mgl7460.boutique.domain.*;

import java.util.*;

public class FactureImpl implements Facture {

    private final Commande commande;
    private float reductionGlobale;
    private final Map<Produit, Float> reductionsSurProduit;
    private final List<Paiement> paiements;

    public FactureImpl(Commande commande) {
        this.commande = commande;
        this.reductionGlobale = 0;
        this.reductionsSurProduit = new HashMap<>();
        this.paiements = new ArrayList<>();
    }

    @Override
    public Commande getCommande() {
        return commande;
    }

    @Override
    public float getMontant() {
    float montant = 0;
    Iterator<LigneCommande> iterateur = commande.getLignesCommandes();

    while (iterateur.hasNext()) {
        LigneCommande ligneCommande = iterateur.next();
        Produit produit = ligneCommande.getProduit();
        float prixUnitaire = ligneCommande.getPrixUnitaire();
        int quantite = ligneCommande.getQuantite();
        float montantLigne = prixUnitaire * quantite;
        montant += montantLigne - reductionsSurProduit.getOrDefault(produit, 0f) * quantite;
    }

    return montant - reductionGlobale;
}



    @Override
    public float getBalance() {
        float totalPaiements = getTotalPaiements();
        return getMontant() - totalPaiements;
    }

    @Override
    public Paiement ajouterPaiement(float amount, Client payeur) {
        Paiement paiement = new PaiementImpl(payeur, this, amount);
        paiements.add(paiement);
        return paiement;
    }

    @Override
    public Iterator<LigneFacture> getDetailsFacture() {
        List<LigneFacture> lignesFacture = new ArrayList<>();
        Iterator<LigneCommande> iterateur = commande.getLignesCommandes();
    
        while (iterateur.hasNext()) {
            LigneCommande ligneCommande = iterateur.next();
            Produit produit = ligneCommande.getProduit();
            float prixUnitaire = ligneCommande.getPrixUnitaire();
            int quantite = ligneCommande.getQuantite();
            float reductionProduit = reductionsSurProduit.getOrDefault(produit, 0f);
            float prixTotal = (prixUnitaire - reductionProduit) * quantite;
            LigneFacture ligneFacture = new LigneFacture(produit, prixUnitaire, reductionProduit, quantite, prixTotal);
            lignesFacture.add(ligneFacture);
        }
    
        return lignesFacture.iterator();
    }
    

    @Override
    public Iterator<Paiement> getPaiements() {
        return Collections.unmodifiableList(paiements).iterator();
    }

    @Override
    public void setReductionGlobale(float reductionGlobale) {
        this.reductionGlobale = reductionGlobale;
    }

    @Override
    public float getReductionGlobale() {
        return reductionGlobale;
    }

    @Override
    public void setReductionSurProduit(Produit produit, float reductionProduit) {
        reductionsSurProduit.put(produit, reductionProduit);
    }

    @Override
    public float getReductionSurProduit(Produit produit) {
        return reductionsSurProduit.getOrDefault(produit, 0f);
    }

    @Override
    public float getTotalPaiements() {
        float totalPaiements = 0;
        for (Paiement paiement : paiements) {
            totalPaiements += paiement.getMontant();
        }
        return totalPaiements;
    }
}
