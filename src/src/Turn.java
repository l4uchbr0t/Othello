package src;

public abstract class Turn {
    protected int team;
    protected String line;

    public Turn(int team) {
        this.team = team;
    }

    public abstract void onTurn(int x, int y); //was beim Zug zu tun ist

    public abstract boolean validturnavailable();

    public abstract void setLine(String line);

    public abstract int getStance();

    public abstract boolean validturn(int x, int y);
}
