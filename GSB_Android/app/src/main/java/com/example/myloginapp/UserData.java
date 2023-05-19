package com.example.myloginapp;

public class UserData {
    private static UserData instance;
    private String userVar;

    private UserData() {
        // Constructeur privé pour empêcher l'instanciation directe de la classe
    }

    public static UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    public String getUserVar() {
        return userVar;
    }

    public void setUserVar(String userVar) {
        this.userVar = userVar;
    }
}
