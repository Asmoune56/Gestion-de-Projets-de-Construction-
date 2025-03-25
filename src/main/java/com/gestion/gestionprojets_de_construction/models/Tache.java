package com.gestion.gestionprojets_de_construction.models;



import java.util.Date;
import java.util.List;

public class Tache {
    private int id_TA;
    private int projet_id;
    private String description;
    private Date date_debut;
    private Date date_fin;


    public Tache(int id_TA, int projet_id, String description, Date date_debut, Date date_fin) {
        this.id_TA = id_TA;
        this.projet_id = projet_id;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Tache(int projet_id, String description, Date date_debut, Date date_fin) {
        this.projet_id = projet_id;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public int getId_TA() {
        return id_TA;
    }

    public void setId_TA(int id_TA) {
        this.id_TA = id_TA;
    }

    public int getProjet_id() {
        return projet_id;
    }

    public void setProjet_id(int projet_id) {
        this.projet_id = projet_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

}

