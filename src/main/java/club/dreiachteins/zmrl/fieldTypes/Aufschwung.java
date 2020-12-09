package club.dreiachteins.zmrl.fieldTypes;

import club.dreiachteins.zmrl.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Aufschwung extends Field {

    private Aufschwung(Builder builder){
        super(builder.number, builder.type);
    }

    public static class Builder{
        private int number;
        private final FieldTypes type = FieldTypes.AUFSCHWUNG;


        public Builder setFieldNumber(int number){
            this.number = number;
            return this;
        }

        public Aufschwung build(){
            return new Aufschwung(this);
        }

    }
}
