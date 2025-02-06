@echo off
set JFX_PATH="C:\Program Files\java-libs\javafx-sdk-22.0.1\lib"
set PATH_TO_JAR="C:\Users\vitor\IdeaProjects\paymentfiler\admin\target\admin-0.0.1-SNAPSHOT.jar"

java --module-path %JFX_PATH% --add-modules javafx.controls,javafx.fxml -jar %PATH_TO_JAR%
