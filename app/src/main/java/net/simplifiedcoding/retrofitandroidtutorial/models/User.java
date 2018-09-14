package net.simplifiedcoding.retrofitandroidtutorial.models;

public class User {

    private int id, admin;
    private String email, name;

    public User(int id, String email, String name, int admin) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAdmin(){
        return admin;
    }

}
