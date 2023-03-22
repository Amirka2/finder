package com.example.service;

import com.example.model.User;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class AuthService {
    private static Set<User> activeUsers = new HashSet<>();
    private static Set<User> allUsers = new HashSet<>();

    public static void addUser(User user) {
        allUsers.add(user);
    }
    public static void setUserActive(User user) {
        activeUsers.add(user);
    }
    public static void clearActiveUsers() {
        activeUsers.clear();
    }
    public static void clearActiveUser(User user) {
        activeUsers.remove(user);
    }
    public static boolean isUserActive(User user) {
        return activeUsers.contains(user);
    }
    public static boolean isUserExists(String userLogin, String userPassword) {
        User user = new User(userLogin, userPassword, "");
        return allUsers.stream().anyMatch(u -> u.equals(user));
    }
    public static @Nullable User getUser(String userLogin, String userPassword) {
        try {
            return allUsers.stream()
                    .filter(u -> u.equals(new User(userLogin, userPassword, ""))).findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
