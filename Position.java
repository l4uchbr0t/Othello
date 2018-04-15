package othello.system;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean valid() {
        return x != -1 && y != -1;
    }

    public Position addGradient(Position gradient) {
        return new Position(x + gradient.x, y + gradient.y);
    }
}
