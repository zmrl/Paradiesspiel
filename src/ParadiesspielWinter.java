import enums.Farben;
import enums.FieldTypes;
import exceptions.WrongPlayerCountException;

import java.util.Hashtable;

/**
 * Paradiesspiel Child
 */
public class ParadiesspielWinter extends Paradiesspiel{

    private static final int MAX_FIELDS = 70;
    private static final int MAX_TOKENS_PER_PLAYER = 3;

    private static final Hashtable<Integer, FieldTypes> FIELDS;
    static {
        FIELDS = new Hashtable<>();
        FIELDS.put(0, FieldTypes.START);
        FIELDS.put(5, FieldTypes.PECH);
        FIELDS.put(9, FieldTypes.PECH);
        FIELDS.put(6, FieldTypes.BÜCKE);
        FIELDS.put(42, FieldTypes.BÜCKE);
        FIELDS.put(14, FieldTypes.GLÜCK);
        FIELDS.put(27, FieldTypes.GLÜCK);
        FIELDS.put(32, FieldTypes.GLÜCK);
        FIELDS.put(36, FieldTypes.GLÜCK);
        FIELDS.put(50, FieldTypes.GLÜCK);
        FIELDS.put(19, FieldTypes.LABYRINTH);
        FIELDS.put(46, FieldTypes.LABYRINTH);
        FIELDS.put(24, FieldTypes.DESASTER);
        FIELDS.put(41, FieldTypes.DESASTER);
        FIELDS.put(54, FieldTypes.DESASTER);
        FIELDS.put(52, FieldTypes.AUFSCHWUNG);
        FIELDS.put(58, FieldTypes.NEUANFANG);
        FIELDS.put(MAX_FIELDS - 1, FieldTypes.PARADIES);
    }

    public ParadiesspielWinter (Farben... farben){
        super(farben);
    }

    @Override
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

    @Override
    public void setPlayerTable() throws WrongPlayerCountException {

        if(isValidPlayerCount(this.farben.length)) {
            throw new WrongPlayerCountException(this.farben.length);
        } else {
            int counter = 1;
            for (Farben colour : this.farben) {
                this.playerTable.put(counter++, new Player(colour.toString(), MAX_TOKENS_PER_PLAYER));
            }
        }
    }
}
