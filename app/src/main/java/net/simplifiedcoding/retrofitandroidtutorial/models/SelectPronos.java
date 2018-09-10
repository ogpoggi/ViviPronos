package net.simplifiedcoding.retrofitandroidtutorial.models;

public class SelectPronos {

    private int id, idPronos, idUser;
    private String statut;

    public SelectPronos(int id,int idPronos,int idUser, String statut) {
        this.id = id;
        this.idPronos = idPronos;
        this.idUser = idUser;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public int getIdPronos(){return idPronos;}

    public int getIdUser(){return idUser;}

    public String getStatut(){return statut;}

}
