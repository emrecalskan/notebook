package observer;

import java.util.ArrayList;
import java.util.List;

public class NoteSubject {
    private List<NoteObserver> observers = new ArrayList<>();

    public void addObserver(NoteObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NoteObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (NoteObserver observer : observers) {
            observer.update(message);
        }
    }
}
