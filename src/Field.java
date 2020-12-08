import enums.FieldTypes;

import java.lang.module.ModuleDescriptor;
import java.util.Arrays;

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
    public Field(FieldBuilder builder){

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



    public static class FieldBuilder{
        private int number;
        private FieldTypes type;

        public FieldBuilder fieldNumber(int number){
            this.number = number;
            return this;
        }

        public FieldBuilder fieldType(FieldTypes type){
            this.type = type;
            return this;
        }

    }
}
