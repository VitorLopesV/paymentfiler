package com.br.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class AdminMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static ConfigurableApplicationContext springContext;

    public static final String ADMIN_TITLE = "Admin Panel";

    public static final String ADMIN_FXML_FILE = "/com/br/admin/fxml/AdminFXMLFile.fxml";

    @Override
    public void init() {
        springContext = SpringApplication.run(SpringBootInitializer.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(ADMIN_FXML_FILE));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle(ADMIN_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
    }
}
