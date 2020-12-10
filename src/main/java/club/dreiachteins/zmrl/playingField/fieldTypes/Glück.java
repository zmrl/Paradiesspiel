package club.dreiachteins.zmrl.playingField.fieldTypes;

import club.dreiachteins.zmrl.playingField.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Glück extends Field {

    private Glück(Builder builder) {
        super(builder.number, builder.type);
    }

    public static class Builder {
        private int number;
        private final FieldTypes type = FieldTypes.GLÜCK;


        public Builder setFieldNumber(int number) {
            this.number = number;
            return this;
        }

        public Glück build() {
            return new Glück(this);
        }

    }
}
