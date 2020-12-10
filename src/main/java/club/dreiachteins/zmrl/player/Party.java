package club.dreiachteins.zmrl.player;

import club.dreiachteins.zmrl.enums.Farbe;
import club.dreiachteins.zmrl.exceptions.FalscheSpielerzahlException;
import club.dreiachteins.zmrl.playingField.Field;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

public class Party {

    private final Hashtable<Farbe, Player> playerTable;
    private final Hashtable<String, Token> tokenTable;
    private Farbe playerOnTurn;

    private Party(Builder builder) {
        this.playerTable = builder.playerTable;
        this.tokenTable = builder.tokenTable;
    }

    // ______________________________________| GET |__________________________________________________\\

    public Farbe[] getPlayers() {
        Farbe[] temp = new Farbe[playerTable.size()];
        playerTable.forEach((k, v) -> {
            temp[v.getPlayerCount()] = v.getColour();
        } );
        return temp;
    }

    public Player getPlayerByName(Farbe farbe){
        return this.playerTable.get(farbe);
    }

    public Player getPLayerByTokenName(String token){
        return getPlayerByName(Farbe.valueOf(token.split("-")[0]));
    }

    public int getPositionFromToken(String token) {
        if (isToken(token)){
            return tokenTable.get(token).getPosition();
        }
        return -1;
    }

    // ______________________________________| SET |__________________________________________________\\

    public void setPlayerOnTurn(Farbe farbe) {
        this.playerOnTurn = farbe;
    }

    // ______________________________________| IS / HAS |__________________________________________________\\

    private boolean isToken(String token){
        return tokenTable.containsKey(token);
    }

    private boolean isTokenFromPlayer(String token){
        return this.playerOnTurn == (Farbe.valueOf(token.split("-")[0]));
    }

    // ______________________________________| Methods |__________________________________________________\\

    public boolean moveToken(String token, int i, int j) {
        if(isToken(token) && isTokenFromPlayer(token)){
            return tokenTable.get(token).setPosition((i+j));
        }
        return false;
    }

    // ______________________________________| TO STRING |__________________________________________________\\

    @Override
    public String toString(){
        return playerToString() + "\n" + tokenToString();
    }

    private String playerToString(){
        Player[] temp = new Player[playerTable.size()];
        playerTable.forEach((k, v) -> {
            temp[v.getPlayerCount()] = v;
        } );
        return Arrays.toString(temp);
    }

    private String tokenToString(){
        Token[] temp = new Token[tokenTable.size()];
        tokenTable.forEach((k, v) -> {
            temp[v.getTokenNumber()] = v;
        } );
        return Arrays.toString(temp);
    }

    // ______________________________________| BUILDER |__________________________________________________\\

    public static class Builder {
        private Hashtable<Farbe, Player> playerTable;
        private final Hashtable<String, Token> tokenTable = new Hashtable<>();
        private Farbe[] farben;

        private static final int MAX_TOKENS_PER_PLAYER = 2;
        private static final int MAX_TOKENS_PER_PLAYER_WINTER = 3;
        private static final int MAX_PLAYER = 6;
        private static final int MIN_PLAYER = 2;

        public Builder setFarbe(Farbe... farben) throws FalscheSpielerzahlException {
            if (!(isValidPlayerCount(farben.length))) {
                throw new FalscheSpielerzahlException(farben.length);
            } else {
                this.farben = farben;
                return this;
            }
        }

        public Builder setPlayerTable() {

            this.playerTable = new Hashtable<>();
            int tokenNumber = 0;
            int playerCounter = 0;
            for (Farbe colour : this.farben) {
                this.playerTable.put(colour, new Player.Builder()
                        .setColour(colour).setPlayerCount(playerCounter++)
                        .build());
            }
            return this;
        }

        private boolean isValidPlayerCount(int i) {
            return i >= MIN_PLAYER && i <= MAX_PLAYER;
        }

        public Builder setTokenTable(String className){

            int maxTokens;
            if(className.equals("class club.dreiachteins.zmrl.ParadiesspielWinter")) {
                maxTokens = MAX_TOKENS_PER_PLAYER_WINTER;
            } else {
                maxTokens = MAX_TOKENS_PER_PLAYER;
            }

            String[] appendix = {"-A", "-B", "-C"};
            int counter = 0;
            for(Farbe farbe : this.farben){
                for(int i = 0; i < maxTokens; i++) {
                    String name = farbe + appendix[i];
                    this.tokenTable.put(name, new Token.Builder()
                            .setFarbe(farbe).setPrefix(appendix[i])
                            .setTokenNumber(counter++)
                            .build());
                }
            }
            return this;
        }

        public Party build(){
            return new Party(this);
        }
    }

    /* ______________________________________| BIN |__________________________________________________
    *
    *
    *
    *
    *
    * */
}
