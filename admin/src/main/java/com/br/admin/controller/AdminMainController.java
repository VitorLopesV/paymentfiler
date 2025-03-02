package com.br.admin.controller;

import com.br.core.model.DataSourceConfigurator;
import com.br.core.service.DataSourceConfigurationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class AdminMainController {

    @FXML
    public TextField textFieldOriginPath;

    @FXML
    public TextField textFieldDestinationPath;

    @FXML
    public Button button;

    public AnchorPane mainPane;

    private final DataSourceConfigurationService dataSourceConfigurationService;

    private static final String SOURCE_CONFIGURATION_ATTRIBUTE_NAME = "Caminho de origem";

    private static final String DESTINATION_CONFIGURATION_ATTRIBUTE_NAME = "Caminho de destino";

    private static final String SAVES_VALUE_MASSAGE = "Salvo com sucesso!";

    private static final String SAVES_VALUE_MASSAGE_TITLE = "Salvando configuração";

    @Autowired
    public AdminMainController(DataSourceConfigurationService dataSourceConfigurationService) {
        this.dataSourceConfigurationService = dataSourceConfigurationService;
    }

    @FXML
    public void initialize() {
        this.textFieldOriginPath.setText(this.getDataSourceValueForName(SOURCE_CONFIGURATION_ATTRIBUTE_NAME));
        this.textFieldDestinationPath.setText(this.getDataSourceValueForName(DESTINATION_CONFIGURATION_ATTRIBUTE_NAME));
    }

    @FXML
    public void saveInformations(ActionEvent actionEvent) {
        this.dataSourceConfigurationService.save(
                new DataSourceConfigurator(SOURCE_CONFIGURATION_ATTRIBUTE_NAME, this.textFieldOriginPath.getText()));
        this.dataSourceConfigurationService.save(new DataSourceConfigurator(DESTINATION_CONFIGURATION_ATTRIBUTE_NAME,
                this.textFieldDestinationPath.getText()));
        this.showMessage(SAVES_VALUE_MASSAGE, SAVES_VALUE_MASSAGE_TITLE);
    }

    private void showMessage(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public String getDataSourceValueForName(String name) {
        if (this.dataSourceConfigurationService.findByName(name) != null) {
            return dataSourceConfigurationService.findByName(name).getPath();
        }
        return "";
    }
}
