package club.dreiachteins.zmrl.playingField.fieldTypes;

import club.dreiachteins.zmrl.playingField.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Paradies extends Field {

    public Paradies(Builder builder) {
        super(builder.number, builder.type);
    }

    @Override
    public void action() {
        //
    }

    public static class Builder{
        private int number;
        private final FieldTypes type = FieldTypes.PARADIES;

        public Builder setFieldNumber(int number){
            this.number = number;
            return this;
        }

        public Paradies build(){
            return new Paradies(this);
        }
    }
}
