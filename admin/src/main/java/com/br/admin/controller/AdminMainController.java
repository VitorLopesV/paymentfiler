package com.br.admin.controller;

import com.br.core.service.DataSourceConfigurationService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMainController {

    public TextField textFieldOriginPath;
    public TextField textFieldDestinationPath;
    public Button button;

    @Autowired
    private DataSourceConfigurationService dataSourceConfigurationService;

    public void saveInformations(ActionEvent actionEvent) {
        dataSourceConfigurationService.saveDataSource(textFieldOriginPath.getText(), textFieldDestinationPath.getText());
    }
}
