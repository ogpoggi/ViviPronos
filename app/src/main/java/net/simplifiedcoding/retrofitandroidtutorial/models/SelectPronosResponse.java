package net.simplifiedcoding.retrofitandroidtutorial.models;

import java.util.List;

public class SelectPronosResponse {

    private boolean error;
    private String message;
    private List<SelectPronos> selectPronos;

    public SelectPronosResponse(boolean error, String message, List<SelectPronos> selectPronos) {
        this.error = error;
        this.message = message;
        this.selectPronos = selectPronos;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<SelectPronos> getSelectPronos() {
        return selectPronos;
    }

}
