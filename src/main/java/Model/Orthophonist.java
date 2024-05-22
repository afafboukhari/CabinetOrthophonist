package Model;

import java.util.ArrayList;
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
    private ArrayList<Patient> listePatients;
    private String motDePasse;

    public Orthophonist(String nom, String prenom, String email,String motdepasse, String num_tel, String adresse)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.motDePasse = motdepasse;
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

    public ArrayList<Patient> getListPatient()
    {
        return listePatients;
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
    public static void saveProfile(Orthophonist orthophonist) {
        try {
            FileOutputStream fileOut = new FileOutputStream(orthophonist.getEmail() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(orthophonist);
            out.close();
            fileOut.close();
            System.out.println("Compte sauvegardé avec succees !");
        } catch (IOException var3) {
            System.out.println("Une erreure est servenue : " + var3.getMessage());
        }

    }

    public static Orthophonist loadOrthophonist(String email) {
        try {
            FileInputStream fileIn = new FileInputStream(email + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Orthophonist orthophonist = (Orthophonist)in.readObject();
            in.close();
            fileIn.close();
            return orthophonist;
        } catch (ClassNotFoundException | IOException var4) {
            System.out.println("Une erreure est servenue : " + var4.getMessage());
            return null;
        }
    }

}
