package club.dreiachteins.zmrl.playingField;

import club.dreiachteins.zmrl.enums.FieldTypes;

/**
 * Feld Super
 */
public abstract class Field {
    private final int number;
    private final FieldTypes type;

    protected Field(int number, FieldTypes type){
        this.number = number;
        this.type = type;
    }

    public void action(){

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
