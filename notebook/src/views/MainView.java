package views;

import models.Note;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kişisel Not Defteri");

        VBox layout = new VBox();
        layout.setSpacing(10);

        Label titleLabel = new Label("Başlık:");
        TextField titleField = new TextField();

        Label contentLabel = new Label("İçerik:");
        TextArea contentArea = new TextArea();

        Button saveButton = new Button("Kaydet");

        layout.getChildren().addAll(titleLabel, titleField, contentLabel, contentArea, saveButton);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
