import enums.FieldTypes;

/**
 * Feld Super
 */
public class Field {
    private int number;
    private FieldTypes type;

    public Field(int number, FieldTypes type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public FieldTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return "" + this.type + "[" + this.number + "]";
    }
}
