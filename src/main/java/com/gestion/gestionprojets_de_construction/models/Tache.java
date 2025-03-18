package com.gestion.gestionprojets_de_construction.models;

import java.sql.Date;

public class Tache {
    private int id;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private int projetId;

    public Tache() {
    }

    public Tache(String description, Date dateDebut, Date dateFin, int projetId) {
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.projetId = projetId;
    }

    public Tache(int id, String description, Date dateDebut, Date dateFin, int projetId) {
        this.id = id;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.projetId = projetId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getProjetId() {
        return projetId;
    }

    public void setProjetId(int projetId) {
        this.projetId = projetId;
    }
}

