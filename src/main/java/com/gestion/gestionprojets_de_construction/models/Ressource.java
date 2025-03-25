package com.gestion.gestionprojets_de_construction.models;



public class Ressource {
    private int id_RESS;
    private String nom;
    private String types;
    private Double quantite;
    private String fournisseur;
    private int quantityUsed;

    public Ressource() {

    }

    public Ressource(int id_RESS, String nom, String types, Double quantite, String fournisseur) {
        this.id_RESS = id_RESS;
        this.nom = nom;
        this.types = types;
        this.quantite = quantite;
        this.fournisseur = fournisseur;

    }

    public Ressource(String nom, String types, Double quantite, String fournisseur, int quantityUsed) {
        this.nom = nom;
        this.types = types;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
        this.quantityUsed = quantityUsed;
    }

    public Ressource(String nom, String types, Double quantite, String fournisseur) {
        this.nom = nom;
        this.types = types;
        this.quantite = quantite;
        this.fournisseur = fournisseur;

    }

    public int getId_RESS() {
        return id_RESS;
    }

    public void setId_RESS(int id_RESS) {
        this.id_RESS = id_RESS;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getQuantityUsed() {
        return quantityUsed;
    }

    public void setQuantityUsed(int quantityUsed) {
        this.quantityUsed = quantityUsed;
    }
}
