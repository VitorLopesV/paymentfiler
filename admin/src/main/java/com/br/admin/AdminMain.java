package com.br.admin;

import com.br.admin.config.SpringContext;
import com.br.admin.config.SpringConfig; // Adicione sua classe de configuração aqui
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Inicializa o contexto do Spring
        SpringContext.initContext(SpringConfig.class);

        // Carrega o FXML e configura o controlador
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ADMIN_FXML_FILE));
        if (loader.getLocation() == null) {
            throw new RuntimeException("FXML file not found: " + ADMIN_FXML_FILE);
        }
        loader.setControllerFactory(SpringContext::getBean); // Injeta o controller pelo Spring

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle(ADMIN_TITLE);
        primaryStage.show();
    }

    public static final String ADMIN_TITLE = "Admin Panel";

    public static final String ADMIN_FXML_FILE = "/com/br/admin/fxml/AdminFXMLFile.fxml";
}
