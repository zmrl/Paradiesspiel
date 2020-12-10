package club.dreiachteins.zmrl.player;

import club.dreiachteins.zmrl.enums.Farbe;
import club.dreiachteins.zmrl.playingField.Field;

import java.util.Objects;

/**
 * Spielfigur
 */
public class Token {
    private final Farbe farbe;
    private final String prefix;
    private int position = 0;
    private final int tokenNumber;

    public Token(Builder builder){
        this.farbe = builder.farbe;
        this.prefix = builder.prefix;
        this.tokenNumber = builder.tokenNumber;
    }

    public Farbe getFarbe() {
        return this.farbe;
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
        return this.farbe + this.prefix + ":" + this.position;
    }

    public static class Builder {
        private Farbe farbe;
        private String prefix;
        private int tokenNumber;

        public Builder setFarbe(Farbe farbe) {
            this.farbe = farbe;
            return this;
        }

        public Builder setPrefix(String prefix){
            this.prefix = prefix;
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
