package factories;

import models.Note;

public class NoteFactory {
    public static Note createNote(int id, String title, String content) {
        return new Note(id, title, content);
    }
}
