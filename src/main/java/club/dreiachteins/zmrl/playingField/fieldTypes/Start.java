package club.dreiachteins.zmrl.playingField.fieldTypes;

import club.dreiachteins.zmrl.playingField.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Start extends Field {

    private Start(Builder builder) {
        super(builder.number, builder.type);
    }

    public static class Builder {
        private int number;
        private final FieldTypes type = FieldTypes.START;

        public Builder setFieldNumber(int number) {
            this.number = number;
            return this;
        }

        public Start build() {
            return new Start(this);
        }

    }
}
