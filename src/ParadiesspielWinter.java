import enums.Farben;
import enums.FieldTypes;
import exceptions.WrongPlayerCountException;

/**
 * Paradiesspiel Child
 */
public class ParadiesspielWinter extends Paradiesspiel{

    private static final int MAX_FIELDS = 70;
    private static final int MAX_TOKENS_PER_PLAYER = 3;

    public ParadiesspielWinter (Farben... farben){
        super(farben);
    }

    @Override
    public void setFields() {
        for(int i = 0; i < MAX_FIELDS; i++) {
            if(i == 5 || i == 9){
                this.fieldTable.put(i, new Field(i, FieldTypes.PECH));
            }
            else if(i == 6 || i == 42){
                this.fieldTable.put(i, new Field(i, FieldTypes.BÜCKE));
            }
            else if (i == 14 || i == 27 || i == 32 || i == 36 || i == 50) {
                this.fieldTable.put(i, new Field(i, FieldTypes.GLÜCK));
            }
            else if(i == 19 || i == 46) {
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
            else if(i == MAX_FIELDS - 1) {
                this.fieldTable.put(i, new Field(i, FieldTypes.PARADIES));
            }
            else {
                this.fieldTable.put(i, new Field(i, FieldTypes.STANDARD));
            }

        }

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
