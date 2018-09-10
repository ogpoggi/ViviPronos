package net.simplifiedcoding.retrofitandroidtutorial.models;

public class SelectPronosResponse {

    private boolean error;
    private String message;
    private SelectPronos selectPronos;

    public SelectPronosResponse(boolean error, String message, SelectPronos selectPronos) {
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

    public SelectPronos getSelectPronos() {
        return selectPronos;
    }

}
