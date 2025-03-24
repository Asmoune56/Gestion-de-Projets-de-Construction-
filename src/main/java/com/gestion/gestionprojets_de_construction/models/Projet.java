package com.gestion.gestionprojets_de_construction.models;

import java.sql.Date;

public class Projet {
    private int id;
    private String nom;
    private String description;
    private Date dateDebut;  // Change to java.sql.Date
    private Date dateFin;    // Change to java.sql.Date
    private double budget;

    // Constructeur principal
    public Projet(int id, String nom, String description, Date dateDebut, Date dateFin, double budget) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setBudget(budget);
    }

    // Constructeur sans ID (utilisé lors de la création d'un nouveau projet)
    public Projet(String nom, String description, Date dateDebut, Date dateFin, double budget) {
        this(0, nom, description, dateDebut, dateFin, budget); // Appelle le constructeur principal
    }

    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) {
        if (dateDebut != null && dateFin != null && dateFin.before(dateDebut)) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début !");
        }
        this.dateFin = dateFin;
    }

    public double getBudget() { return budget; }
    public void setBudget(double budget) {
        if (budget < 0) {
            throw new IllegalArgumentException("Le budget ne peut pas être négatif !");
        }
        this.budget = budget;
    }
}
