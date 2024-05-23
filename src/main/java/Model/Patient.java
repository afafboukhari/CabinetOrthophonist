package Model;

import java.io.Serializable;
import java.util.Date;

public abstract class Patient implements Serializable
{
    private String nom;
    private String prenom;
    private Date date_de_naissance;
    private String lieu_naissance;
    private String adresse;

    public Patient(String nom, String prenom, Date dateDeNaissance, String lieuNaissance, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_de_naissance = dateDeNaissance;
        this.lieu_naissance = lieuNaissance;
        this.adresse = adresse;
    }

    public String getNom()
    {
        return this.nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getLieu_naissance() {
        return lieu_naissance;
    }

    public void setLieu_naissance(String lieu_naissance) {
        this.lieu_naissance = lieu_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
