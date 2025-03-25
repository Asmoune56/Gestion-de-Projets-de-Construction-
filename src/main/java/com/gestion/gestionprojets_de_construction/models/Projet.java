package com.gestion.gestionprojets_de_construction.models;



import java.sql.Date;

public class Projet {
    private int id_PR;
    private String nom;
    private String description;
    private Date date_debut;
    private Date date_fin;
    private Double budget;

    public Projet(int id_PR, String nom, String description, Date date_debut, Date date_fin, Double budget) {
        this.id_PR = id_PR;
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.budget = budget;
    }

    public Projet(String nom, String description, Date date_debut, Date date_fin, Double budget) {
        this.nom = nom;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.budget = budget;
    }

    public int getId_PR() {
        return id_PR;
    }

    public void setId_PR(int id_PR) {
        this.id_PR = id_PR;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}
