package models;

import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    private List<Note> notes;  // Notları tutacak liste

    // Constructor: NoteDao nesnesi oluşturulduğunda boş bir liste başlatır
    public NoteDao() {
        this.notes = new ArrayList<>();
    }

    // Not ekleme metodu
    public void add(Note note) {
        notes.add(note);
    }

    // Not güncelleme metodu
    public void update(Note updatedNote) {
        for (int i = 0; i < notes.size(); i++) {
            Note currentNote = notes.get(i);
            if (currentNote.getId() == updatedNote.getId()) {
                currentNote.setTitle(updatedNote.getTitle());
                currentNote.setContent(updatedNote.getContent());
                System.out.println("Not güncellendi: " + currentNote);
                break;
            }
        }
    }

    // Not silme metodu
    public void delete(int id) {
        boolean removed = notes.removeIf(note -> note.getId() == id);
        if (removed) {
            System.out.println("Not silindi.");
        } else {
            System.out.println("Silinecek not bulunamadı.");
        }
    }

    // Bütün notları alma metodu
    public List<Note> getAllNotes() {
        return notes;
    }
}
