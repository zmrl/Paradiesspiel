package club.dreiachteins.zmrl.fieldTypes;

import club.dreiachteins.zmrl.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Desaster extends Field {

    private Desaster(Builder builder) {
        super(builder.number, builder.type);
    }

    public static class Builder {
        private int number;
        private final FieldTypes type = FieldTypes.DESASTER;


        public Builder setFieldNumber(int number) {
            this.number = number;
            return this;
        }

        public Desaster build() {
            return new Desaster(this);
        }

    }
}
