package club.dreiachteins.zmrl.playingField;

import club.dreiachteins.zmrl.playingField.fieldTypes.*;

import java.util.Arrays;
import java.util.Hashtable;

public class PlayingField {
    private final Hashtable<Integer, Field> fieldTable;

    private PlayingField(Builder builder){
        this.fieldTable = builder.fieldTable;
    }

    // ______________________________________| TO STRING |__________________________________________________\\

    public Hashtable<Integer, Field> getFieldTable() {
        return this.fieldTable;
    }

    // ______________________________________| TO STRING |__________________________________________________\\

    @Override
    public String toString() {
        Field[] temp = new Field[fieldTable.size()];
        fieldTable.forEach((k, v) -> {
            temp[k] = v;
        } );
        return Arrays.toString(temp);
    }


    // ______________________________________| BUILDER |__________________________________________________\\

    public static class Builder {
        public Hashtable<Integer, Field> fieldTable;

        private static final int MAX_FIELDS = 64;
        private static final int MAX_FIELDS_WINTER = 70;

        private Hashtable<Integer, Field> fields(String className, int max) {
            Hashtable<Integer, Field> FIELDS = new Hashtable<>();

            FIELDS.put(0, new Start.Builder().setFieldNumber(0).build());
            FIELDS.put(5, new Pech.Builder().setFieldNumber(0).build());
            FIELDS.put(9, new Pech.Builder().setFieldNumber(0).build());
            FIELDS.put(6, new Brücke.Builder().setFieldNumber(6).build());
            FIELDS.put(14, new Glück.Builder().setFieldNumber(14).build());
            FIELDS.put(18, new Glück.Builder().setFieldNumber(18).build());
            FIELDS.put(27, new Glück.Builder().setFieldNumber(27).build());
            FIELDS.put(32, new Glück.Builder().setFieldNumber(32).build());
            FIELDS.put(36, new Glück.Builder().setFieldNumber(36).build());
            FIELDS.put(50, new Glück.Builder().setFieldNumber(50).build());
            FIELDS.put(19, new Labyrinth.Builder().setFieldNumber(19).build());
            FIELDS.put(24, new Desaster.Builder().setFieldNumber(24).build());
            FIELDS.put(41, new Desaster.Builder().setFieldNumber(41).build());
            FIELDS.put(54, new Desaster.Builder().setFieldNumber(54).build());
            FIELDS.put(52, new Aufschwung.Builder().setFieldNumber(52).build());
            FIELDS.put(58, new Neuanfang.Builder().setFieldNumber(58).build());
            FIELDS.put(max, new Paradies.Builder().setFieldNumber(max).build());
            if (className.equals("class club.dreiachteins.zmrl.ParadiesspielWinter")) {
                FIELDS.put(42, new Brücke.Builder().setFieldNumber(42).build());
                FIELDS.put(46, new Labyrinth.Builder().setFieldNumber(46).build());
                FIELDS.put(18, new Standard.Builder().setFieldNumber(18).build());
                }
            return FIELDS;
        }

        public Builder setFields(String className){
            this.fieldTable = new Hashtable<>();
            int max_fields;
            Hashtable<Integer, Field> fields;
            if(className.equals("class club.dreiachteins.zmrl.ParadiesspielWinter")) {
                max_fields = MAX_FIELDS_WINTER - 1;
            } else {
                max_fields = MAX_FIELDS - 1;
            }
            fields = fields(className, max_fields);


            for(int i = 0; i < max_fields; i++){
                this.fieldTable.put(i, new Standard.Builder()
                        .setFieldNumber(i)
                        .build());
            }

            fields.forEach((k, v) -> {
                this.fieldTable.put(k, v);
            });

            return this;
        }

        public PlayingField build(){
            return new PlayingField(this);
        }

    }
}
