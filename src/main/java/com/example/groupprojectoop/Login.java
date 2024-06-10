package com.example.groupprojectoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Login {
    private static final ArrayList<String> usernames = new ArrayList<>();
    private static final ArrayList<String> passwords = new ArrayList<>();
    private static final String FILE_PATH = "data.txt";
    private static String name;
    
    public Login() {
        readData();
    }

    private  void writeData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {  // Append mode
            int lastIndex = usernames.size() - 1;
            writer.write(usernames.get(lastIndex) + "," + passwords.get(lastIndex) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void readData() {
        usernames.clear();
        passwords.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String tempRead;
            while ((tempRead = reader.readLine()) != null) {
                String[] data = tempRead.split(",");
                if (data.length == 2) {
                    usernames.add(data[0]);
                    passwords.add(data[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void createAccount(String username, String password) {
        usernames.add(username);
        passwords.add(password);
        writeData();
        BudgetPlanning budget = new BudgetPlanning();
        budget.createFile(username);  // Create the file for the new user
    }

    public  boolean checkUser(String username, String password) {
        name = username;
        for (int x = 0; x < usernames.size(); x++) {
            if (username.equals(usernames.get(x)) && password.equals(passwords.get(x))) {
                return true;
            }
        }
        return false;
    }

    public  String getName() {
        return name;
    }
}
