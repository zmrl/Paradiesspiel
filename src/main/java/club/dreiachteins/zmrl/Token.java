package club.dreiachteins.zmrl;

/**
 * Spielfigur
 */
public class Token {
    private final String name;
    private int position = 0;

    public Token(String name){
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.position;
    }
}
