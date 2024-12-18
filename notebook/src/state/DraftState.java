package state;

public class DraftState implements State {
    @Override
    public void changeState() {
        System.out.println("Note is in draft state.");
    }
}
