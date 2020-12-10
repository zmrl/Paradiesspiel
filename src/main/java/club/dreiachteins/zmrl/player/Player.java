package club.dreiachteins.zmrl.player;

import club.dreiachteins.zmrl.enums.Farbe;
import club.dreiachteins.zmrl.exceptions.FalscheSpielerzahlException;
import club.dreiachteins.zmrl.exceptions.SpielfigurNichtVorhandenException;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Spieler
 */
public class Player {

    private final Farbe colour;
    private final int playerCount;
    private final Hashtable<String, Token> tokenTable;
    private boolean isPaused = false;

    private Player(Builder builder){
        this.colour = builder.colour;
        this.playerCount = builder.playerCount;
        this.tokenTable = builder.tokenTable;
    }

    // ______________________________________| GET |__________________________________________________\\

    public Token getTokenByName(String name) throws SpielfigurNichtVorhandenException {
        if(!tokenTable.containsKey(name)){
            throw new SpielfigurNichtVorhandenException(name);
        }
        return tokenTable.get(name);
    }

    public Token[] getToken(){
        Token[] temp = new Token[tokenTable.size()];
        tokenTable.forEach((k, v) -> {
            temp[v.getTokenNumber()] = v;
        } );
        return temp;
    }

    public Farbe getColour(){
        return this.colour;
    }

    public int getPlayerCount() {
        return this.playerCount;
    }

    public boolean isPaused(){
        return this.isPaused;
    }

    // ______________________________________| SET |__________________________________________________\\

    public boolean setTokenPosition(String token, int i, int j){
        int pos = i + j;
        try {
            return getTokenByName(token).setPosition(pos);
        } catch (SpielfigurNichtVorhandenException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return false;
    }

    // ______________________________________| METHODS |__________________________________________________\\

    public void toggleIsPaused(){
        this.isPaused = !this.isPaused;
    }

    // ______________________________________| TO STRING |__________________________________________________\\

    @Override
    public String toString() {
        return "Player " + this.colour + " " + Arrays.toString(this.getToken());
    }

    // ______________________________________| BUILDER |__________________________________________________\\

    public static class Builder{
        private Farbe colour;
        private int playerCount;
        private Hashtable<String, Token> tokenTable;

        public Builder setColour(Farbe colour){
            this.colour = colour;
            return this;
        }

        public Builder setPlayerCount(int i ){
            this.playerCount = i;
            return this;
        }

        public Builder setTokenTable(int numberOfTokens){
            this.tokenTable = new Hashtable<>();
            String[] appendix = {"-A", "-B", "-C"};
            for(int i = 0; i < numberOfTokens; i++) {
                String name = this.colour + appendix[i];
                this.tokenTable.put(name, new Token.Builder()
                        .setName(name)
                        .setTokenNumber(i)
                        .build());
            }
            return this;
        }

        public Player build(){
            return new Player(this);
        }
    }
}
