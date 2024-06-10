package com.example.groupprojectoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BudgetPlanning {
    private static String FILE_PATH1 = null;
    private double income = 0;
    private double[] spendBudget = {0, 0, 0, 0, 0, 0, 0, 0};
    private double[] expenses = {0, 0, 0, 0, 0, 0, 0, 0};
    private double total_expenses = 0;
    public String[] categories = {"Food", "Entertainment", "Bills", "Fuel", "Beauty", "Health", "Transportation", "Shopping"};

    public BudgetPlanning(String name) {
        setFile(name);
        readData();
        getExpenses();
    }

    public BudgetPlanning() {
        readData();
    }
    
    public double getTotalExpenses() {
        getExpenses();
        return total_expenses;
    }

    public void getExpenses() {
        total_expenses = 0;
        for (int x = 0; x < expenses.length; x++) {
            total_expenses += expenses[x];
        }
    }
    
    public void calcExpense(double value, String category) {
        int index = -1;
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equalsIgnoreCase(category)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            expenses[index] += value;
            System.out.println("Expense added: " + value + " to category " + category);
            writeData(); // Ensure data is written back to the file
        } else {
            System.out.println("Category not found: " + category);
        }
    }


    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income += income;
        writeData();
    }

    public double getBalance() {
        return income - total_expenses;
    }

    public void setFile(String name) {
        FILE_PATH1 = name + ".txt";
    }

    public void createFile(String name) {
        String filename1 = name + ".txt";
        try {
            new File(filename1).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        if (FILE_PATH1 == null) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH1))) {
            String tempRead = reader.readLine();
            if (tempRead != null) {
                String[] values = tempRead.split(",");
                if (values.length >= 10) { // Ensure correct length for both budgets and expenses
                    income = Double.parseDouble(values[0]);
                    total_expenses = Double.parseDouble(values[1]);
                    for (int i = 2; i < values.length; i++) {
                        if (i < 10) {
                            spendBudget[i - 2] = Double.parseDouble(values[i]);
                        } else {
                            expenses[i - 10] = Double.parseDouble(values[i]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeData() {
        if (FILE_PATH1 == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH1))) {
            StringBuilder sb = new StringBuilder();
            sb.append(income).append(",").append(total_expenses);
            for (double value : spendBudget) {
                sb.append(",").append(value);
            }
            for (double expense : expenses) {
                sb.append(",").append(expense);
            }
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String setBudget(Double value, String category, String period) {
        double totalBudget = 0;
        for (double budget : spendBudget) {
            totalBudget += budget;
        }

        if ((totalBudget + value) > income) {
            return "Error: Budget exceeds income.";
        } else {
            int index = -1;
            for (int i = 0; i < categories.length; i++) {
                if (categories[i].equalsIgnoreCase(category)) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                if (period.equalsIgnoreCase("weekly")) {
                    value *= 4;
                }
                spendBudget[index] = value;
                writeData();
                return "Budget set successfully for category: " + category;
            } else {
                return "Error: Invalid category.";
            }
        }
    }
}
