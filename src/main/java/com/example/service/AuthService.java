package com.example.service;

import com.example.model.User;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class AuthService {
    private static Map<String, User> activeUsers = new HashMap();
    private static Map<String, User> allUsers = init();

    public static void addUser(User user) {
        allUsers.put(user.getLogin(), user);
        activeUsers.put(user.getLogin(), user);
        UserDB.insert(user);
    }
    public static void setUserActive(User user) {
        activeUsers.put(user.getLogin(), user);
    }
    public static void clearActiveUsers() {
        activeUsers.clear();
    }
    public static void clearActiveUser(String login) {
        activeUsers.remove(login);
    }
    public static boolean isUserActive(String login) {
        return activeUsers.containsKey(login);
    }
    public static boolean isUserExists(String login) {
        return allUsers.containsKey(login);
    }
    public static boolean deleteUser(User user) {
        UserDB.delete(user.getId());
        activeUsers.remove(user.getLogin(), user);
        return allUsers.remove(user.getLogin(), user);
    }
    public static @Nullable User getUser(String login) {
        return allUsers.get(login);
    }

    private static Map<String, User> init() {
        List<User> users = UserDB.select();
        return users.stream()
                .collect(Collectors.toMap(u -> u.getLogin(), u -> u));
    }
}
