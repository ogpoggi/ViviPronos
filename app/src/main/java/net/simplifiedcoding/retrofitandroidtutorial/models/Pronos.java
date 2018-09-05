package net.simplifiedcoding.retrofitandroidtutorial.models;

public class Pronos {
    private int id;
    private String equipe1, equipe2;
    private Float cote1, cote2, matchNull;

    public Pronos(int id, String equipe1, String equipe2, Float cote1, Float cote2, Float matchNull) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.cote1 = cote1;
        this.cote2 = cote2;
        this.matchNull = matchNull;
    }

    public int getId() {
        return id;
    }

    public String getEquipe1() {
        return equipe1;
    }

    public String getEquipe2() {
        return equipe2;
    }

    public Float getCote1() {
        return cote1;
    }

    public Float getCote2() {
        return cote2;
    }

    public Float getMatchNull() {
        return matchNull;
    }
}
