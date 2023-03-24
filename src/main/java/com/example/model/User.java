package com.example.model;

import java.util.Objects;

public class User {
    private final String login;
    private String password;
    private String email;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, email);
    }
}
