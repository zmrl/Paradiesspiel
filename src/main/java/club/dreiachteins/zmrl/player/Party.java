package club.dreiachteins.zmrl.player;

import club.dreiachteins.zmrl.enums.Farbe;
import club.dreiachteins.zmrl.exceptions.FalscheSpielerzahlException;
import club.dreiachteins.zmrl.playingField.Field;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

public class Party {

    public static final int ZERO = 0;
    private final Hashtable<Farbe, Player> playerTable;
    private final Hashtable<String, Token> tokenTable;
    private Farbe playerOnTurn;
    private Farbe[] gewinner;

    private Party(Builder builder) {
        this.playerTable = builder.playerTable;
        this.tokenTable = builder.tokenTable;
        this.playerOnTurn = builder.playerOnTurn;
        this.gewinner = builder.gewinner;

    }

    // ______________________________________| GET |__________________________________________________\\

    public Farbe[] getPlayers() {
        Farbe[] temp = new Farbe[playerTable.size()];
        playerTable.forEach((k, v) -> {
            temp[v.getPlayerCount()] = v.getColour();
        } );
        return temp;
    }

    public void setGewinner(int i){
        tokenTable.forEach((k, v) -> {
            if (v.getPosition() == i){
                gewinner[v.getTokenNumber()] = v.getFarbe();
            }
        });
    }

    public Farbe getGewinner(){
        for(int i = ZERO; i < this.tokenTable.size(); i+=(this.tokenTable.size()/this.playerTable.size())){
            int count = ZERO;
            for(int j = ZERO; j < (this.tokenTable.size()/this.playerTable.size()); j++){
                if (this.gewinner[i+j] != null){
                    count++;
                }
                if (count == (this.tokenTable.size()/this.playerTable.size())){
                    return this.gewinner[i];
                }
            }
        }
        return null;
    }

    public int getPositionFromToken(String token) {
        if (isToken(token)){
            return tokenTable.get(token).getPosition();
        }
        return -1;
    }

    public Token getToken(String token) {
        return this.tokenTable.get(token);
    }

    // ______________________________________| SET |__________________________________________________\\

    public void setPlayerOnTurn(Farbe farbe) {
        this.playerOnTurn = farbe;
    }

    public boolean setPosition(String token, int i){
        return this.tokenTable.get(token).setPosition(i);
    }

    // ______________________________________| IS / HAS |__________________________________________________\\

    private boolean isToken(String token){
        return tokenTable.containsKey(token);
    }

    private boolean isTokenFromPlayer(String token){
        return this.playerOnTurn == (Farbe.valueOf(token.split("-")[ZERO]));
    }

    // ______________________________________| Methods |__________________________________________________\\

    public boolean moveToken(String token, int i) {
        if(isToken(token) && isTokenFromPlayer(token)){
            return tokenTable.get(token).setPosition((i));
        }
        System.out.print("WrongPlayer. " + playerOnTurn + " ist dran");
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
        private Farbe[] gewinner;
        private Farbe playerOnTurn;


        private static final int MAX_TOKENS_PER_PLAYER = 2;
        private static final int MAX_TOKENS_PER_PLAYER_WINTER = 3;
        private static final int MAX_PLAYER = 6;
        private static final int MIN_PLAYER = 2;

        public Builder setFarbe(Farbe... farben) throws FalscheSpielerzahlException {
            if (!(isValidPlayerCount(farben.length))) {
                throw new FalscheSpielerzahlException(farben.length);
            } else {
                this.farben = farben;
                setFarbeAmZug();
                return this;
            }
        }

        public void setFarbeAmZug(){
            this. playerOnTurn = this.farben[ZERO];
        }

        public Builder setPlayerTable() {

            this.playerTable = new Hashtable<>();
            int tokenNumber = ZERO;
            int playerCounter = ZERO;
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
            int counter = ZERO;
            for(Farbe farbe : this.farben){
                for(int i = ZERO; i < maxTokens; i++) {
                    String name = farbe + appendix[i];
                    this.tokenTable.put(name, new Token.Builder()
                            .setFarbe(farbe).setPrefix(appendix[i])
                            .setTokenNumber(counter++)
                            .build());
                }
            }
            setGewinner();
            return this;
        }

        private void setGewinner() {
            this.gewinner = new Farbe[this.tokenTable.size()];
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
