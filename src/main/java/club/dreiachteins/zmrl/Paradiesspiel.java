package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.Farben;

import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * club.dreiachteins.zmrl.Paradiesspiel Super
 */
public class Paradiesspiel {

    // _____________________________________________________ CLASS VARIABLES _______________________________________ //

    public Hashtable<Integer, Field> fieldTable;
    public Hashtable<Integer, Player> playerTable;

    // _____________________________________________________ CONSTRUCTOR _______________________________________ //

    public Paradiesspiel(Farben... farben) {
        this.init(farben);
    }

    public Paradiesspiel(String conf, Farben... farben) {

    }

    public void init(Farben... farben){
        this.fieldTable = new PlayingFieldGenerator.Builder()
                .setFields(this.getClass().toString())
                .build()
                .getFieldTable();

        this.playerTable = new PlayerGenerator.Builder()
                .setPlayerTable(this.getClass().toString(), farben)
                .build()
                .getPlayerTable();
    }


    // _____________________________________________________ GET _______________________________________ //

    public Hashtable<Integer, Field> getFieldTable() {
        return fieldTable;
    }

    public Collection<Player> getPlayers() {
        return playerTable.values();
    }

    public Player getPlayerByID(int id){
        return this.playerTable.get(id);
    }

    // _____________________________________________________ TO STRING _______________________________________ //
    @Override
    public String toString() {
        return this.playerTableToString() + "\n" + this.fieldTableToStrings();
    }

    private String fieldTableToStrings(){
        AtomicInteger counter = new AtomicInteger(1);
        AtomicReference<String> fields_temp = new AtomicReference<>("");
        this.fieldTable.forEach((k, v) -> {
            if(counter.get() < this.fieldTable.size()){
                counter.getAndIncrement();
                fields_temp.updateAndGet(v1 -> v1 + v + "->");
            } else {
                fields_temp.updateAndGet(v1 -> v1 + v);
            }

        });
        return fields_temp.toString();
    }

    private String playerTableToString(){
        AtomicInteger counter = new AtomicInteger(1);
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
    }
}
