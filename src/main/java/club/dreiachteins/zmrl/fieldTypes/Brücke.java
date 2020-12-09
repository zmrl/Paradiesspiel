package club.dreiachteins.zmrl.fieldTypes;

import club.dreiachteins.zmrl.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Brücke extends Field {

    private Brücke(Builder builder) {
        super(builder.number, builder.type);
    }

    public static class Builder {
        private int number;
        private final FieldTypes type = FieldTypes.BÜCKE;

        public Builder setFieldNumber(int number) {
            this.number = number;
            return this;
        }

        public Brücke build() {
            return new Brücke(this);
        }

    }
}
