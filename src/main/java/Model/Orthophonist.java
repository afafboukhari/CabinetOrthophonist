package Model;

import com.sun.source.tree.Tree;

import java.util.TreeMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Orthophonist implements Serializable
{
    private String nom;
    private String prenom;
    private String adresse;
    private String num_tel;
    private String email;
    private Agenda agenda ;
    private TreeMap<Integer,Dossier> mes_dossiers;
    private Tests mes_test;
    private String motDePasse;
    private Compte compte;

    public Orthophonist(String nom, String prenom, String email,String motdepasse, String num_tel, String adresse)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.motDePasse = motdepasse;
        mes_test = new Tests();
        saveProfile(this);
    }

    public Orthophonist()
    {

    }

    public String getNom()
    {
        return nom;
    }

    // Les getters :
    public String getPrenom()
    {
        return prenom;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public String getNum_tel()
    {
        return num_tel;
    }

    public String getEmail()
    {
        return email;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    // Les setters :
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }
    public void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }
    public void saveProfile(Orthophonist orthophonist) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/data/"+orthophonist.getEmail() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orthophonist);
            out.close();
            fileOut.close();
            System.out.println("Compte sauvegardé avec succees !");
        } catch (IOException var3) {
            System.out.println("Une erreure est servenue : " + var3.getMessage());
        }

    }


    public Compte getCompte() {
        return compte;
    }

    public Tests getMes_test() {
        return mes_test;
    }

    public void setMes_test(Tests mes_test) {
        this.mes_test = mes_test;
    }
}
