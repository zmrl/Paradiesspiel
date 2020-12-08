import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Spieler
 */
public class Player {
    private final String colour;
    private final Hashtable<String, Token> tokenTable;
    private boolean isPaused = false;

    private Player(Builder builder){
        this.colour = builder.colour;
        this.tokenTable = builder.tokenTable;
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

    // BUILDER

    public static class Builder{
        private String colour;
        private Hashtable<String, Token> tokenTable;

        public Builder setColour(String colour){
            this.colour = colour;
            return this;
        }

        public Builder setTokenTable(int numberOfTokens){
            this.tokenTable = new Hashtable<>();
            String[] appendix = {"-A", "-B", "-C"};
            for(int i = 0; i < numberOfTokens; i++) {
                String name = this.colour + appendix[i];
                this.tokenTable.put(name, new Token(name));
            }
            return this;
        }

        public Player build(){
            return new Player(this);
        }
    }
}
