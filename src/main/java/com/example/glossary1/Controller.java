package com.example.glossary1;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Map;

//controller for the glossary
public class Controller {

    @FXML
    private TextField searchText;
    @FXML
    private TableView<Map.Entry<String, String>> table;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> termCol;
    @FXML
    private TableColumn<Map.Entry<String, String>, String> meaningCol;
    @FXML
    private TextField addTermText;
    @FXML
    private TextField addMeaningText;
    @FXML
    private TextField updateTermText;
    @FXML
    private TextField updateMeaningText;
    @FXML
    private TextField removeTermText;

    private final Glossary glossary;
    private final FileChooser fileChooser;
    private final Alert error;
    private final Alert info;

    //Constructor for the controller
    public Controller() {
        glossary = new Glossary();
        fileChooser = new FileChooser();
        error = new Alert(Alert.AlertType.ERROR);
        info = new Alert(Alert.AlertType.INFORMATION);
    }

    //Init method
    @FXML
    private void initialize() {
        table.setPlaceholder(new Label("No terms to view"));
        termCol.setCellValueFactory(entryStringCellDataFeatures -> new SimpleStringProperty(entryStringCellDataFeatures.getValue().getKey()));
        meaningCol.setCellValueFactory(entryStringCellDataFeatures -> new SimpleStringProperty(entryStringCellDataFeatures.getValue().getValue()));

    }

    //Adding a term to the glossary
    @FXML
    void add() {
        try {
            glossary.add(addTermText.getText(), addMeaningText.getText());

        } catch (Exception e) {
            error.setContentText(e.getMessage());
            error.show();
        }
        viewGlossary();
        addTermText.setText("");
        addMeaningText.setText("");
    }

    //Delete a term from the glossary
    @FXML
    void remove() {
        try {
            glossary.remove(removeTermText.getText());
        } catch (Exception e) {
            error.setContentText(e.getMessage());
            error.show();
        }
        viewGlossary();
        removeTermText.setText("");
    }

    //Update an existing term
    @FXML
    void update() {
        try {
            glossary.update(updateTermText.getText(), updateMeaningText.getText());
        } catch (Exception e) {
            error.setContentText(e.getMessage());
            error.show();
        }
        viewGlossary();
        updateTermText.setText("");
        updateMeaningText.setText("");
    }

    //Search a term ,if exist on our glossary
    @FXML
    void search() {
        table.getItems().clear();
        table.getItems().addAll(glossary.search(searchText.getText()).entrySet());
        searchText.setText("");
    }

    //Clear the previous search
    @FXML
    void clear() {
        searchText.setText("");
        viewGlossary();
    }

    //View all of our terms on the table
    @FXML
    private void viewGlossary() {
        table.getItems().clear();
        table.getItems().addAll(glossary.getGlossary().entrySet());
    }

    //Save a new glossary file
    @FXML
    void save() {
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                ObjectOutputStream objOutPut = new ObjectOutputStream(new FileOutputStream(file));
                objOutPut.writeObject(glossary);
                objOutPut.close();
                info.setContentText("File goes by the name: (" + file.getName() + ") has been saved!");
                info.show();
            } catch (Exception e) {
                error.setContentText(e.getMessage());
                error.show();
            }
        }
    }

    //Load an existing file
    @FXML
    void load() {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                ObjectInputStream objInPut = new ObjectInputStream(new FileInputStream(file));
                glossary.setGlossary((Glossary) objInPut.readObject());
                objInPut.close();
            } catch (Exception e) {
                error.setContentText(e.getMessage());
                error.show();
            }
            viewGlossary();
        }
    }


}
