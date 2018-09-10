package net.simplifiedcoding.retrofitandroidtutorial.models;

import java.util.List;

public class SelectPronosResponse {

    private boolean error;
    private List<SelectPronos> selectPronos;

    public SelectPronosResponse(boolean error, List<SelectPronos> selectPronos) {
        this.error = error;
        this.selectPronos = selectPronos;
    }

    public boolean isError() {
        return error;
    }

    public List<SelectPronos> getSelectPronos() {
        return selectPronos;
    }

}
