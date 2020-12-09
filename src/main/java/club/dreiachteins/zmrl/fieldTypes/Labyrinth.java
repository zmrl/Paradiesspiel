package club.dreiachteins.zmrl.fieldTypes;

import club.dreiachteins.zmrl.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Labyrinth extends Field {

    private Labyrinth(Builder builder) {
        super(builder.number, builder.type);
    }

    public static class Builder {
        private int number;
        private final FieldTypes type = FieldTypes.LABYRINTH;


        public Builder setFieldNumber(int number) {
            this.number = number;
            return this;
        }

        public Labyrinth build() {
            return new Labyrinth(this);
        }

    }
}
