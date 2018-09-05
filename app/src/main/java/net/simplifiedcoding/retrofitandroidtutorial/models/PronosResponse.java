package net.simplifiedcoding.retrofitandroidtutorial.models;

import java.util.List;

public class PronosResponse {

    private boolean error;
    private List<Pronos> pronos;

    public PronosResponse(boolean error, List<Pronos> pronos) {
        this.error = error;
        this.pronos = pronos;
    }

    public boolean isError() {
        return error;
    }

    public List<Pronos> getPronos() {
        return pronos;
    }
}
