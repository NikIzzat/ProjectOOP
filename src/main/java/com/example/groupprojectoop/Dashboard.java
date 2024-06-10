package com.example.groupprojectoop;

import java.time.LocalDate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class Dashboard {
    private TableView<TransactionRecord> tableView;
    private String passwords;

    public VBox createDashboard() {
        // Create the table and set its columns
        tableView = new TableView<>();

        TableColumn<TransactionRecord, Integer> indexColumn = new TableColumn<>("No.");
        indexColumn.setCellValueFactory(cellData -> {
            // Get the index of the item
            int index = tableView.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleObjectProperty<>(index);
        });
        indexColumn.setPrefWidth(80); // Set preferred width for the index column

        TableColumn<TransactionRecord, Double> valueColumn = new TableColumn<>("Spent");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        valueColumn.setPrefWidth(120); // Set preferred width for the value column

        TableColumn<TransactionRecord, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setPrefWidth(170); // Set preferred width for the category column

        TableColumn<TransactionRecord, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setPrefWidth(150); // Set preferred width for the date column

        TableColumn<TransactionRecord, String> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        noteColumn.setPrefWidth(300); // Set preferred width for the note column

        tableView.getColumns().addAll(indexColumn, valueColumn, categoryColumn, dateColumn, noteColumn);

        // Load data into the table
        loadTransactionData();

        VBox vbox = new VBox(tableView);
        return vbox;
    }


    private void loadTransactionData() {
        Login login = new Login();
        Transaction transaction = new Transaction(login.getName());
        ObservableList<TransactionRecord> data = FXCollections.observableArrayList(transaction.getTransactions());
        tableView.setItems(data);
    }
    
    public TransactionRecord getSelectedTransaction() {
        return tableView.getSelectionModel().getSelectedItem();
    }
    private ValidationPassword passwordValidator = new ValidationPassword ();

    public void setPassword(String password) {
        if (!passwordValidator.isValidPassword(password)) {
            // Display error message to user
            System.out.println("Invalid password. Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character.");
        } else {
            // Set the password
            this.passwords = password;
        }
    }

}


