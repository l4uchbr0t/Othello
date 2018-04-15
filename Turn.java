package othello.system;

public abstract class Turn {
    protected int team;

    public Turn(int team) {
        this.team = team;
    }

    public abstract void onTurn();
}
