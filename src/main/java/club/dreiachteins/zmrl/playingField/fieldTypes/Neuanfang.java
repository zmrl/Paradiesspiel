package club.dreiachteins.zmrl.playingField.fieldTypes;

import club.dreiachteins.zmrl.playingField.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Neuanfang extends Field {

    private Neuanfang(Builder builder) {
        super(builder.number, builder.type);
    }

    public static class Builder {
        private int number;
        private final FieldTypes type = FieldTypes.NEUANFANG;


        public Builder setFieldNumber(int number) {
            this.number = number;
            return this;
        }

        public Neuanfang build() {
            return new Neuanfang(this);
        }

    }
}