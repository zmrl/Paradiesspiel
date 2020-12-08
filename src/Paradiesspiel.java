import enums.Farben;
import enums.FieldTypes;
import exceptions.WrongPlayerCountException;

import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Paradiesspiel Super
 */
public class Paradiesspiel {

    public Hashtable<Integer, Field> fieldTable = new Hashtable<>();
    public Hashtable<Integer, Player> playerTable = new Hashtable<>();
    public Farben[] farben;

    private static final int MAX_FIELDS = 64;
    private static final int MAX_TOKENS_PER_PLAYER = 2;
    private static final int MAX_PLAYER = 6;
    private static final int MIN_PLAYER = 2;
    private static final boolean DEBUG = false;

    public Paradiesspiel(Farben... farben) {
        this.farben = farben;
        this.init();
    }
    public Paradiesspiel(String conf, Farben... farben) {

    }

    public void init(){
        this.setFields();
        try {
            this.setPlayerTable();
        } catch (WrongPlayerCountException e) {
            e.printStackTrace();
            e.getMessage();
        }


        if(DEBUG){
            this.fieldTable.forEach((k, v) -> {
                System.out.print(v + ", ");
            });
            this.playerTable.forEach((k, v) -> {
                System.out.print(v + " -> ");
                System.out.println(v.getTokenCollection());
            });
        }
    }

    public void setFields() {
        for(int i = 0; i < MAX_FIELDS; i++) {
            if(i == 5 || i == 9){
                this.fieldTable.put(i, new Field(i, FieldTypes.PECH));
            }
            else if(i == 6){
                this.fieldTable.put(i, new Field(i, FieldTypes.BÜCKE));
            }
            else if (i == 14 || i == 18 || i == 27 || i == 32 || i == 36 || i == 50) {
                this.fieldTable.put(i, new Field(i, FieldTypes.GLÜCK));
            }
            else if(i == 19) {
                this.fieldTable.put(i, new Field(i, FieldTypes.LABYRINTH));
            }
            else if(i == 24 || i == 41 || i == 54){
                this.fieldTable.put(i, new Field(i, FieldTypes.DESASTER));
            }
            else if(i == 52) {
                this.fieldTable.put(i, new Field(i, FieldTypes.AUFSCHWUNG));
            }
            else if(i == 58) {
                this.fieldTable.put(i, new Field(i, FieldTypes.NEUANFANG));
            }
            else if(i == 63) {
                this.fieldTable.put(i, new Field(i, FieldTypes.PARADIES));
            }
            else {
                this.fieldTable.put(i, new Field(i, FieldTypes.STANDARD));
            }

        }

    }

    public void setPlayerTable() throws WrongPlayerCountException {

        if(isValidPlayerCount(this.farben.length)){
            throw new WrongPlayerCountException(this.farben.length);
        } else {
            int counter = 1;
            for (Farben colour : this.farben) {
                this.playerTable.put(counter++, new Player(colour.toString(), MAX_TOKENS_PER_PLAYER));
            }
        }
    }

    protected boolean isValidPlayerCount(int i) {
        return i < MIN_PLAYER || i > MAX_PLAYER;
    }

    public Hashtable<Integer, Field> getFieldTable() {
        return fieldTable;
    }

    public Collection<Player> getPlayers() {
        return playerTable.values();
    }

    public Player getPlayerByID(int id){
        return this.playerTable.get(id);
    }

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
