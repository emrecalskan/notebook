package state;

public class CompletedState implements State {
    @Override
    public void changeState() {
        System.out.println("Note is completed.");
    }
}
