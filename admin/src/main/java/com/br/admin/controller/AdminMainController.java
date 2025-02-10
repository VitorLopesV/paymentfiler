package com.br.admin.controller;

import com.br.core.model.DataSourceConfigurator;
import com.br.core.service.DataSourceConfigurationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMainController {

    @FXML
    public TextField textFieldOriginPath;

    @FXML
    public TextField textFieldDestinationPath;

    @FXML
    public Button button;

    private final DataSourceConfigurationService dataSourceConfigurationService;

    @Autowired
    public AdminMainController(DataSourceConfigurationService dataSourceConfigurationService) {
        this.dataSourceConfigurationService = dataSourceConfigurationService;
    }

    @FXML
    public void initialize() {
        this.textFieldOriginPath.setText(this.getDataSourceValueForName("Caminho de origem"));
        this.textFieldDestinationPath.setText(this.getDataSourceValueForName("Caminho de destino"));
    }

    @FXML
    public void saveInformations(ActionEvent actionEvent) {
        this.dataSourceConfigurationService.save(
                new DataSourceConfigurator("Caminho de origem", this.textFieldOriginPath.getText()));
        this.dataSourceConfigurationService.save(
                new DataSourceConfigurator("Caminho de destino", this.textFieldDestinationPath.getText()));
        System.out.println("Salvo com sucesso!");
    }

    public String getDataSourceValueForName(String name){
        if(this.dataSourceConfigurationService.findByName(name) != null){
            return dataSourceConfigurationService.findByName(name).getPath();
        }
        return "";
    }
}
