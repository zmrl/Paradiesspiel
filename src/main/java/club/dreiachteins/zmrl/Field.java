package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.FieldTypes;

/**
 * Feld Super
 */
public class Field {
    private final int number;
    private final FieldTypes type;

    protected Field(Builder builder){
        this.number = builder.number;
        this.type = builder.type;
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

    public static class Builder {
        private int number;
        private FieldTypes type;

        public Builder setFieldNumber(int number){
            this.number = number;
            return this;
        }

        public Builder setFieldType(FieldTypes type){
            this.type = type;
            return this;
        }

        public Field build(){
            return new Field(this);
        }

    }
}
