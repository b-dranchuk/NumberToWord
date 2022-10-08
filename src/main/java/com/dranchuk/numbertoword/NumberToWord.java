package com.dranchuk.numbertoword;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NumberToWord extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NumberToWord.class.getResource("NumberToWord-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 340);

        stage.setTitle("Сума прописом");
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> System.exit(0));
        stage.show();
    }

    public static void main(String[] A) {
        launch();
    }
}