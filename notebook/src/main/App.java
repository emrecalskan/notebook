package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Note;
import models.NoteDao;

public class App extends Application {

    private NoteDao noteDao = new NoteDao();  // Notların veritabanına erişimi sağlayan DAO
    private TextField idTextField = new TextField();  // ID'yi girecek alan
    private TextField noteTextField = new TextField();  // Notu girecek alan
    private ListView<String> notesListView = new ListView<>();  // Notları listeleyecek ListView

    @Override
    public void start(Stage primaryStage) {
        VBox vbox = new VBox(10);

        Label idLabel = new Label("ID (Güncelle veya Silmek için):");
        Label noteLabel = new Label("Not:");

        Button addButton = new Button("Not Ekle");
        Button updateButton = new Button("Not Güncelle");
        Button deleteButton = new Button("Not Sil");

        // ID ve Not alanlarını yerleştir
        vbox.getChildren().addAll(idLabel, idTextField, noteLabel, noteTextField, addButton, updateButton, deleteButton, notesListView);

        addButton.setOnAction(event -> addNote());
        updateButton.setOnAction(event -> updateNote());
        deleteButton.setOnAction(event -> deleteNote());

        // İlk başta veritabanındaki notları listeler
        refreshNotes();

        // Notlar ListView'den seçildiğinde, ID'yi TextField'a yerleştir
        notesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Seçilen notun ID'sini, not içeriğinden çıkartalım
                for (Note note : noteDao.getAllNotes()) {
                    if (note.getContent().equals(newValue)) {
                        idTextField.setText(String.valueOf(note.getId()));
                        noteTextField.setText(note.getContent());
                        break;
                    }
                }
            }
        });

        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setTitle("Not Defteri Uygulaması");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addNote() {
        String noteContent = noteTextField.getText();
        String idInput = idTextField.getText();  // ID'yi kullanıcıdan al
        if (noteContent.isEmpty()) {
            showAlert("Geçersiz Not", "Lütfen geçerli bir not girin.");
            return;
        }

        if (idInput.isEmpty()) {
            showAlert("Geçersiz ID", "Lütfen geçerli bir ID girin.");
            return;
        }

        try {
            int id = Integer.parseInt(idInput);  // ID'yi sayıya çevir

            // Aynı ID'ye sahip bir not var mı kontrol et
            for (Note note : noteDao.getAllNotes()) {
                if (note.getId() == id) {
                    showAlert("ID Mevcut", "Bu ID'ye sahip bir not zaten mevcut.");
                    return;
                }
            }

            // Yeni notu veritabanına ekle
            Note newNote = new Note(id, "Yeni Başlık", noteContent);  // Başlık geçici olarak "Yeni Başlık" olarak atanıyor
            noteDao.add(newNote);
            showAlert("Başarılı", "Not başarıyla eklendi!");
            refreshNotes();  // Güncellenmiş notları listele

        } catch (NumberFormatException e) {
            showAlert("Geçersiz ID", "Geçersiz bir ID girildi.");
        }
    }

    private void updateNote() {
        String idInput = idTextField.getText();  // Kullanıcıdan ID'yi al
        if (idInput.isEmpty()) {
            showAlert("Geçersiz ID", "Lütfen geçerli bir ID girin.");
            return;
        }

        try {
            int id = Integer.parseInt(idInput);  // ID'yi sayıya çevir
            String newNoteContent = noteTextField.getText(); // Yeni notu al
            if (newNoteContent.isEmpty()) {
                showAlert("Geçersiz Not", "Lütfen güncellemek için geçerli bir not girin.");
                return;
            }

            // Güncellenmek istenen notun var olup olmadığını kontrol et
            Note noteToUpdate = null;
            for (Note note : noteDao.getAllNotes()) {
                if (note.getId() == id) {
                    noteToUpdate = note;
                    break;
                }
            }

            if (noteToUpdate != null) {
                // Güncelleme işlemini burada gerçekleştirin
                noteToUpdate.setContent(newNoteContent);  // İçeriği güncelle
                noteDao.update(noteToUpdate);  // Güncelleme işlemi
                showAlert("Başarılı", "Not başarıyla güncellendi.");
                refreshNotes();  // Güncellenmiş notları yeniden listele
            } else {
                showAlert("Not Bulunamadı", "Güncellenecek not bulunamadı.");
            }

        } catch (NumberFormatException e) {
            showAlert("Geçersiz ID", "Geçersiz bir ID girildi.");
        }
    }

    private void deleteNote() {
        String idInput = idTextField.getText();  // Kullanıcıdan ID'yi al
        if (idInput.isEmpty()) {
            showAlert("Geçersiz ID", "Lütfen geçerli bir ID girin.");
            return;
        }

        try {
            int id = Integer.parseInt(idInput);  // ID'yi sayıya çevir

            // Silme işlemini burada gerçekleştirin
            Note noteToDelete = null;
            for (Note note : noteDao.getAllNotes()) {
                if (note.getId() == id) {
                    noteToDelete = note;
                    break;
                }
            }

            if (noteToDelete != null) {
                noteDao.delete(id);  // Silme işlemi
                showAlert("Başarılı", "Not başarıyla silindi.");
                refreshNotes();  // Silinen notu listeden çıkar
            } else {
                showAlert("Not Bulunamadı", "Silinecek not bulunamadı.");
            }

        } catch (NumberFormatException e) {
            showAlert("Geçersiz ID", "Geçersiz bir ID girildi.");
        }
    }

    private void refreshNotes() {
        notesListView.getItems().clear();  // Eski listeyi temizle
        for (Note note : noteDao.getAllNotes()) {
            notesListView.getItems().add(note.getContent());
        }
    }

    private void showAlert(String title, String message) {
        // Popup ile kullanıcıya uyarı göster
        Alert alert = new Alert(Alert.AlertType.INFORMATION);  // Başarı mesajı için
        if (title.equals("Geçersiz Not") || title.equals("Geçersiz ID") || title.equals("Not Bulunamadı") || title.equals("ID Mevcut")) {
            alert.setAlertType(Alert.AlertType.WARNING);  // Uyarı mesajı için
        }
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
