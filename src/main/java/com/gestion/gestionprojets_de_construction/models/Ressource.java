package com.gestion.gestionprojets_de_construction.models;

public class Ressource {
    private int id;
    private String nom;
    private String type;
    private int quantite;
    private int fournisseurId;

    public Ressource() {
    }

    public Ressource(String nom, String type, int quantite, int fournisseurId) {
        this.nom = nom;
        this.type = type;
        this.quantite = quantite;
        this.fournisseurId = fournisseurId;
    }

    public Ressource(int id, String nom, String type, int quantite, int fournisseurId) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.quantite = quantite;
        this.fournisseurId = fournisseurId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(int fournisseurId) {
        this.fournisseurId = fournisseurId;
    }
}

