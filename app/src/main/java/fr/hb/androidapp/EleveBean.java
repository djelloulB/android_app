package fr.hb.androidapp;

public class EleveBean {
    public static final int AGE_ADULTE = 18;


    private String nom;
    private String prenom;
    private int age;

    public boolean isAdult(){
        return age>=AGE_ADULTE;
    }
/////////// Getters and Setters ///////////////////
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    ///////////////// ToString /////////////////////

    @Override
    public String toString() {
        return "EleveBean{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                '}';
    }
}
