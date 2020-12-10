package club.dreiachteins.zmrl.player;

import java.util.Objects;

/**
 * Spielfigur
 */
public class Token {
    private final String name;
    private int position = 0;
    private final int tokenNumber;

    public Token(Builder builder){
        this.name = builder.name;
        this.tokenNumber = builder.tokenNumber;
    }

    public String getName() {
        return this.name;
    }

    public int getPosition() {
        return position;
    }

    public int getTokenNumber() {
        return tokenNumber;
    }

    public boolean setPosition(int position) {
        this.position = position;
        return true;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.position;
    }

    public static class Builder {
        private String name;
        private int tokenNumber;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setTokenNumber(int i) {
            this.tokenNumber = i;
            return this;
        }

        public Token build(){
            return new Token(this);
        }

    }
}
