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
    private Tests mes_test;
    private String motDePasse;
    private Compte compte;
    private TreeMap<Integer,Dossier> Mes_dossiers;

    public Orthophonist(String nom, String prenom, String email,String motdepasse, String num_tel, String adresse)
    {
        this.Mes_dossiers = new TreeMap<Integer,Dossier >();
        this.agenda = new Agenda();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.email = email;
        this.motDePasse = motdepasse;
        mes_test = new Tests();
        compte = new Compte(nom,prenom,num_tel,email,motdepasse,adresse);
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

    public TreeMap<Integer, Dossier> getMes_dossiers() {
        return Mes_dossiers;
    }

    public TreeMap< Integer,Dossier > getMes_patients()
    {
        return Mes_dossiers;
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
    public void add_patient(Dossier dossier)
    {
        Mes_dossiers.put(dossier.getNumero(),dossier);

    }
    public Agenda getAgenda() {
        return agenda;
    }

    public Dossier rechercher_patient(int n)
    {

        if (Mes_dossiers!=null)
        {
            if(Mes_dossiers.containsKey(n))
            {
                return Mes_dossiers.get(n);}
            else
            {
                return null;
            }
        }
        return null;
    }

    public void add_rendez_vous_patient(int num,Rendez_vous rendez_vous){

        if (Mes_dossiers.containsKey(num)) {

            Dossier dossier = Mes_dossiers.get(num);
            dossier.add_rendez_vous(rendez_vous);
            Dossier dossier1 =Mes_dossiers.put(num,dossier);

        } else {
            System.out.println("La clé '" + num + "' n'est pas présente dans le TreeMap.");
        }

    }


    public Tests getMes_test() {
        return mes_test;
    }

    public void setMes_test(Tests mes_test) {
        this.mes_test = mes_test;
    }

}
