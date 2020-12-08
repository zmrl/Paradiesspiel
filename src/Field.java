import enums.FieldTypes;

/**
 * Feld Super
 */
public class Field {
    private int number;
    private FieldTypes type;

    private Field(Builder builder){
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

        public Builder fieldNumber(int number){
            this.number = number;
            return this;
        }

        public Builder fieldType(FieldTypes type){
            this.type = type;
            return this;
        }

        public Field build(){
            return new Field(this);
        }

    }
}
