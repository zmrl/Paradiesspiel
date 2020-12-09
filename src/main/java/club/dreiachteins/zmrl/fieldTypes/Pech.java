package club.dreiachteins.zmrl.fieldTypes;

import club.dreiachteins.zmrl.Field;
import club.dreiachteins.zmrl.enums.FieldTypes;

public class Pech extends Field {

    private Pech(Builder builder){
        super(builder.number, builder.type);
    }

    @Override
    public void action() {
        // Do Something
    }

    public static class Builder{
        private int number;
        private final FieldTypes type = FieldTypes.PECH;


        public Builder setFieldNumber(int number){
            this.number = number;
            return this;
        }

        public Pech build(){
            return new Pech(this);
        }
    }
}
