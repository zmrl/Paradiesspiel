package club.dreiachteins.zmrl.playingField.fieldTypes;

import club.dreiachteins.zmrl.playingField.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Standard extends Field {

    private Standard(Builder builder) {
        super(builder.number, builder.type);
    }

    public static class Builder {
        private int number;
        private final FieldTypes type = FieldTypes.STANDARD;

        public Builder setFieldNumber(int number){
            this.number = number;
            return this;
        }

        public Standard build(){
            return new Standard(this);
        }
    }
}
