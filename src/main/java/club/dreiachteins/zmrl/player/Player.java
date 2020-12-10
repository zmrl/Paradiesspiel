package club.dreiachteins.zmrl.player;

import club.dreiachteins.zmrl.enums.Farbe;

/**
 * Spieler
 */
public class Player {

    private final Farbe colour;
    private final int playerCount;
    private boolean isPaused = false;

    private Player(Builder builder){
        this.colour = builder.colour;
        this.playerCount = builder.playerCount;
    }

    // ______________________________________| GET |__________________________________________________\\


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


    // ______________________________________| METHODS |__________________________________________________\\

    public void toggleIsPaused(){
        this.isPaused = !this.isPaused;
    }

    // ______________________________________| TO STRING |__________________________________________________\\

    @Override
    public String toString() {
        return "Player " + this.colour;
    }

    // ______________________________________| BUILDER |__________________________________________________\\

    public static class Builder{
        private Farbe colour;
        private int playerCount;

        public Builder setColour(Farbe colour){
            this.colour = colour;
            return this;
        }

        public Builder setPlayerCount(int i ){
            this.playerCount = i;
            return this;
        }

        public Player build(){
            return new Player(this);
        }
    }
}
