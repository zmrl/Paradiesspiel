import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Spieler
 */
public class Player {
    private final String colour;
    private final Hashtable<String, Token> tokenTable = new Hashtable<>();
    private boolean isPaused = false;

    public Player(String colour, int numberOfTokens) {
        this.colour = colour;
        setTokenTable(numberOfTokens);
    }

    public void setTokenTable(int numberOfTokens) {
        String[] appendix = {"-A", "-B", "-C"};
        for(int i = 0; i < numberOfTokens; i++) {
            String name = this.colour + appendix[i];
            this.tokenTable.put(name, new Token(name));
        }
    }

    public Token getTokenByName(String name){
        return tokenTable.get(name);
    }

    public String getColour(){
        return this.colour;
    }

    public boolean isPaused(){
        return this.isPaused;
    }

    public void toggleIsPaused(){
        this.isPaused = !this.isPaused;
    }

    public Collection<Token> getTokenCollection(){
        return this.tokenTable.values();
    }

    // STRING METHODEN

    @Override
    public String toString() {

        return "Player " + this.colour + " [" + this.tokenToString() + "]";
    }

    private String tokenToString(){
        AtomicInteger counter = new AtomicInteger(1);
        AtomicReference<String> token_temp = new AtomicReference<>("");
        this.tokenTable.forEach((k, v) -> {
            if(counter.get() < this.tokenTable.size()) {
                counter.getAndIncrement();
                token_temp.updateAndGet(v1 -> v1 + v + ", ");
            } else {
                token_temp.updateAndGet(v1 -> v1 + v);
            }

        });
        return token_temp.toString();
    }
}
