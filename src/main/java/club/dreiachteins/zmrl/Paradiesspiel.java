package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.Farbe;
import club.dreiachteins.zmrl.player.Party;
import club.dreiachteins.zmrl.playingField.PlayingField;

import java.util.Arrays;

/**
 * Paradiesspiel Super
 */
public class Paradiesspiel {

    // _____________________________________________________ CLASS VARIABLES _______________________________________ //

    public PlayingField playingField;
    public Party party;
    public Farbe gewinner;

    // _____________________________________________________ CONSTRUCTOR _______________________________________ //

    public Paradiesspiel(Farbe... farben) {
        this.init(farben);
    }

    public Paradiesspiel(String conf, Farbe... farben) {
        this.init(farben);
        this.configuration(conf);

    }

    public void init(Farbe... farben){
        this.playingField = new PlayingField.Builder()
                .setFields(this.getClass().toString())
                .build();

        this.party = new Party.Builder().setFarbe(farben)
                .setPlayerTable()
                .setTokenTable(this.getClass().toString())
                .build();
    }

    private void configuration(String conf){
        String[] temp = conf.replace(", ", ":").split(":");
        for(int i = 0; i < temp.length; i = i+2){
            this.setFigurPostiton(temp[i], Integer.parseInt(temp[i+1]));
        }
    }

    // _____________________________________________________ METHODS _______________________________________ //

    public boolean bewegeFigur(String s, int i, int i1) {
        if(this.gewinner != null) {
            return false;
        }

        else if (this.party.getToken(s) == null) {
            return false;
        }

        else if (this.playingField.checkParadies(this.party.getToken(s).getPosition())){
            return false;
        }

        else {
            int position = party.getPositionFromToken(s);
            int j = 0;

            if(position == -1){
                return false;
            }

            for(; j < (i + i1); j++) {
                if (!(position + 1 >= this.playingField.getFieldLength())) {
                    position = this.playingField.checkBruecke(++position);
                } else {
                    break;
                }
            }

            for(; j < (i + i1); j++) {
                --position;
            }
            if (this.playingField.checkParadies(position)){
                this.party.getToken(s).setInParadise();
            }
            boolean temp = party.moveToken(s, position);
            this.party.setGewinner(this.playingField.getFieldLength() - 1);
            return temp;
        }

    }

    // _____________________________________________________ GET _______________________________________ //

    public Farbe[] getAlleSpieler() {
        return party.getPlayers();
    }

    public Farbe getGewinner() {
        return this.gewinner = this.party.getGewinner();
    }

    public int getFigurposition(String figur) {
        return this.party.getPositionFromToken(figur);
    }

    // _____________________________________________________ SET _______________________________________ //

    public void setFarbeAmZug(Farbe farbe) {
        this.party.setPlayerOnTurn(farbe);
    }

    public void setFigurPostiton(String figur, int position) {
        this.party.setPosition(figur, position);
    }

    private void setGewinner(Farbe farbe){
        this.gewinner = farbe;
    }

    // _____________________________________________________ TO STRING _______________________________________ //
    @Override
    public String toString() {
        return this.party + "\n" + this.playingField;
    }
}
