package com.gestion.gestionprojets_de_construction.models;


public class TacheRessource {

    private int tache_ressource_id;
    private int id_ressource;
    private int id_tache;
    public TacheRessource() {}

    public TacheRessource(int tache_ressource_id, int id_ressource, int id_tache) {
        this.tache_ressource_id = tache_ressource_id;
        this.id_ressource = id_ressource;
        this.id_tache = id_tache;
    }

    public TacheRessource(int id_ressource, int id_tache) {
        this.id_ressource = id_ressource;
        this.id_tache = id_tache;
    }

    public int getTache_ressource_id() {
        return tache_ressource_id;
    }

    public void setTache_ressource_id(int tache_ressource_id) {
        this.tache_ressource_id = tache_ressource_id;
    }

    public int getId_ressource() {
        return id_ressource;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public int getId_tache() {
        return id_tache;
    }

    public void setId_tache(int id_tache) {
        this.id_tache = id_tache;
    }
}

