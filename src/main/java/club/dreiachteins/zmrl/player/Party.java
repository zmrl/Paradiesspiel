package club.dreiachteins.zmrl.player;

import club.dreiachteins.zmrl.enums.Farbe;
import club.dreiachteins.zmrl.exceptions.FalscheSpielerzahlException;
import club.dreiachteins.zmrl.exceptions.SpielfigurNichtVorhandenException;

import java.util.Arrays;
import java.util.Hashtable;

public class Party {

    private final Hashtable<Farbe, Player> playerTable;

    private Party(Builder builder) {
        this.playerTable = builder.playerTable;
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
        int temp = -1;
        try {
            temp = getPLayerByTokenName(token).getTokenByName(token).getPosition();
        } catch (SpielfigurNichtVorhandenException e){
            e.printStackTrace();
            e.getMessage();
        }
        return temp;
    }

    public boolean moveTokenFromPLayer(String token, int i, int j) {
            return getPLayerByTokenName(token).setTokenPosition(token, i, j);
    }

    // ______________________________________| TO STRING |__________________________________________________\\

    @Override
    public String toString(){
        Player[] temp = new Player[playerTable.size()];
        playerTable.forEach((k, v) -> {
            temp[v.getPlayerCount()] = v;
        } );
        return Arrays.toString(temp);
    }



    // ______________________________________| BUILDER |__________________________________________________\\

    public static class Builder {
        public Hashtable<Farbe, Player> playerTable;

        private static final int MAX_TOKENS_PER_PLAYER = 2;
        private static final int MAX_TOKENS_PER_PLAYER_WINTER = 3;
        private static final int MAX_PLAYER = 6;
        private static final int MIN_PLAYER = 2;

        public Builder setPlayerTable(String className, Farbe... colours) throws FalscheSpielerzahlException{

            if(!(isValidPlayerCount(colours.length))) {
                throw new FalscheSpielerzahlException(colours.length);
            } else {
                int maxTokens;
                if(className.equals("class club.dreiachteins.zmrl.ParadiesspielWinter")) {
                    maxTokens = MAX_TOKENS_PER_PLAYER_WINTER;
                } else {
                    maxTokens = MAX_TOKENS_PER_PLAYER;
                }
                this.playerTable = new Hashtable<>();
                int playerCounter = 0;
                for (Farbe colour : colours) {
                    this.playerTable.put(colour, new Player.Builder()
                            .setColour(colour).setPlayerCount(playerCounter++)
                            .setTokenTable(maxTokens)
                            .build());
                }
            }
            return this;
        }

        private boolean isValidPlayerCount(int i) {
            return i >= MIN_PLAYER && i <= MAX_PLAYER;
        }

        public Party build(){
            return new Party(this);
        }

    }

    /* ______________________________________| BIN |__________________________________________________
    *
    * AtomicInteger counter = new AtomicInteger(1);
        AtomicReference<String> player_temp = new AtomicReference<>("");
        this.playerTable.forEach((k, v) -> {
            if(counter.get() < this.playerTable.size()){
                counter.getAndIncrement();
                player_temp.updateAndGet(v1 -> v1 + v + "\n");
            } else {
                player_temp.updateAndGet(v1 -> v1 + v);
            }

        });
        return player_temp.toString();
    *
    *
    *
    * */
}
