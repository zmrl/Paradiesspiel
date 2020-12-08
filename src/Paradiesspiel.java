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


    private static final Hashtable<Integer, FieldTypes> FIELDS;
        static {
            FIELDS = new Hashtable<>();
            FIELDS.put(0, FieldTypes.START);
            FIELDS.put(5, FieldTypes.PECH);
            FIELDS.put(9, FieldTypes.PECH);
            FIELDS.put(6, FieldTypes.BÜCKE);
            FIELDS.put(14, FieldTypes.GLÜCK);
            FIELDS.put(18, FieldTypes.GLÜCK);
            FIELDS.put(27, FieldTypes.GLÜCK);
            FIELDS.put(32, FieldTypes.GLÜCK);
            FIELDS.put(36, FieldTypes.GLÜCK);
            FIELDS.put(50, FieldTypes.GLÜCK);
            FIELDS.put(19, FieldTypes.LABYRINTH);
            FIELDS.put(24, FieldTypes.DESASTER);
            FIELDS.put(41, FieldTypes.DESASTER);
            FIELDS.put(54, FieldTypes.DESASTER);
            FIELDS.put(52, FieldTypes.AUFSCHWUNG);
            FIELDS.put(58, FieldTypes.NEUANFANG);
            FIELDS.put(MAX_FIELDS - 1, FieldTypes.PARADIES);
        }

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

    public void setFields(){
        for(int i = 0; i < MAX_FIELDS; i++){
            this.fieldTable.put(i, new Field.Builder()
                    .fieldNumber(i)
                    .fieldType(FieldTypes.STANDARD)
                    .build());
        }

        FIELDS.forEach((k, v) -> {
            this.fieldTable.put(k, new Field.Builder()
                    .fieldNumber(k)
                    .fieldType(v)
                    .build());
        });
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
