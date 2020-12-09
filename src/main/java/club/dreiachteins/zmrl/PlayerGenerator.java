package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.Farbe;
import club.dreiachteins.zmrl.exceptions.FalscheSpielerzahlException;

import java.util.Hashtable;

public class PlayerGenerator {

    private final Hashtable<Integer, Player> playerTable;

    private PlayerGenerator(Builder builder) {
        this.playerTable = builder.playerTable;
    }

    public Hashtable<Integer, Player> getPlayerTable() {
        return playerTable;
    }

    // ______________________________________| BUILDER |__________________________________________________\\

    public static class Builder {
        public Hashtable<Integer, Player> playerTable;

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
                int counter = 1;
                for (Farbe colour : colours) {
                    this.playerTable.put(counter++, new Player.Builder()
                            .setColour(colour.toString())
                            .setTokenTable(maxTokens)
                            .build());
                }
            }
            return this;
        }

        private boolean isValidPlayerCount(int i) {
            return i >= MIN_PLAYER && i <= MAX_PLAYER;
        }

        public PlayerGenerator build(){
            return new PlayerGenerator(this);
        }

    }
}
