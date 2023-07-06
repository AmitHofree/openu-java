import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PhoneBookController {
    @FXML
    private AnchorPane anchor;
    @FXML
    private TableView<PhoneBookEntry> table;
    @FXML
    private TableColumn<PhoneBookEntry, String> nameColumn, phoneNumberColumn;

    private String nameFilter;
    private final PhoneBook phoneBook;

    public PhoneBookController() {
        nameFilter = "";
        phoneBook = new PhoneBook();
    }

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    public void onAddClicked() {
        Dialog<PhoneBookEntry> dialog = new Dialog<>();
        dialog.setTitle("Add contact");
        dialog.setHeaderText("Add a new contact to the phone book");

        // Set the button types.
        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Create the name and phone number labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("+972000000000");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Phone number:"), 0, 1);
        grid.add(phoneNumberField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new PhoneBookEntry(nameField.getText(), phoneNumberField.getText());
            }
            return null;
        });

        Optional<PhoneBookEntry> result = dialog.showAndWait();
        if (result.isPresent()) {
            phoneBook.add(result.get());
        }
        refreshDisplay();
    }

    public void onEditClicked() {
        PhoneBookEntry selectedEntry = table.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Edit contact");
            dialog.setHeaderText(String.format("Edit the phone number for %s", selectedEntry.getName()));

            // Set the button types.
            ButtonType addButtonType = new ButtonType("Edit", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

            // Create the name and phone number labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField phoneNumberField = new TextField();
            phoneNumberField.setText(selectedEntry.getPhoneNumber());

            grid.add(new Label("Phone number:"), 0, 0);
            grid.add(phoneNumberField, 1, 0);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    return phoneNumberField.getText();
                }
                return null;
            });

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                phoneBook.edit(selectedEntry.getName(), phoneNumberField.getText());
            }
            refreshDisplay();
        }
    }

    public void onRemoveClicked() {
        PhoneBookEntry selectedEntry = table.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            phoneBook.remove(selectedEntry.getName());
            refreshDisplay();
        }
    }

    public void onLoadFileClicked() {
        try {
            Window window = anchor.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Phone book files", "*.bin"));
            File selectedFile = fileChooser.showOpenDialog(window);
            if (selectedFile != null) {
                phoneBook.loadFromFile(selectedFile);
                refreshDisplay();
            }
        } catch (IOException | ClassNotFoundException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Could not load phone book from file!");
            alert.showAndWait();
        }
    }

    public void onSaveFileClicked() {
        try {
            Window window = anchor.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("phonebook.bin");
            File selectedFile = fileChooser.showSaveDialog(window);
            if (selectedFile != null) {
                phoneBook.saveToFile(selectedFile);
            }
        } catch (IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Could not save phone book to file!");
            alert.showAndWait();
        }
    }

    public void onSearchType(KeyEvent keyEvent) {
        TextField searchField = (TextField) keyEvent.getSource();
        String searchString = searchField.getText();
        nameFilter = searchString;
        refreshDisplay();
    }

    private void refreshDisplay() {
        List<PhoneBookEntry> dataEntries = phoneBook.entries();
        List<PhoneBookEntry> filteredEntries = new ArrayList<>();
        for (PhoneBookEntry entry: dataEntries) {
            if (entry.getName().contains(nameFilter)) {
                filteredEntries.add(entry);
            }
        }
        ObservableList<PhoneBookEntry> tableEntries = table.getItems();
        tableEntries.clear();
        tableEntries.addAll(filteredEntries);
    }
}
