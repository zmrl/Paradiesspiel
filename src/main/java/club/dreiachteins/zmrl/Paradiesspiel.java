package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.Farbe;
import club.dreiachteins.zmrl.player.Party;
import club.dreiachteins.zmrl.playingField.PlayingField;

/**
 * Paradiesspiel Super
 */
public class Paradiesspiel {

    // _____________________________________________________ CLASS VARIABLES _______________________________________ //

    public PlayingField playingField;
    public Party party;

    // _____________________________________________________ CONSTRUCTOR _______________________________________ //

    public Paradiesspiel(Farbe... farben) {
        this.init(farben);
    }

    public Paradiesspiel(String conf, Farbe... farben) {

    }

    public void init(Farbe... farben){
        this.playingField = new PlayingField.Builder()
                .setFields(this.getClass().toString())
                .build();

        this.party = new Party.Builder()
                .setPlayerTable(this.getClass().toString(), farben)
                .build();
    }

    // _____________________________________________________ METHODS _______________________________________ //

    public boolean bewegeFigur(String s, int i, int i1) {
        return party.moveTokenFromPLayer(s, i, i1);
    }

    // _____________________________________________________ GET _______________________________________ //

    public Farbe[] getAlleSpieler() {
        return party.getPlayers();
    }

    public Farbe getGewinner() {
        return Farbe.BLAU;
    }

    public int getFigurposition(String figur) {
        return this.party.getPositionFromToken(figur);
    }

    // _____________________________________________________ SET _______________________________________ //

    public void setFarbeAmZug(Farbe farbe) {

    }

    // _____________________________________________________ TO STRING _______________________________________ //
    @Override
    public String toString() {
        return this.party + "\n" + this.playingField;
    }
}
