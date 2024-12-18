package state;

public class NoteContext {
    private State state;

    public NoteContext() {
        state = new DraftState();  // Başlangıç durumu
    }

    public void setState(State state) {
        this.state = state;
    }

    public void changeState() {
        state.changeState();
    }
}
