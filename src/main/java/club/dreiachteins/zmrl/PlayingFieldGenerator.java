package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.FieldTypes;

import java.util.Hashtable;

public class PlayingFieldGenerator {
    private final Hashtable<Integer, Field> fieldTable;

    private PlayingFieldGenerator(Builder builder){
        this.fieldTable = builder.fieldTable;
    }

    public Hashtable<Integer, Field> getFieldTable() {
        return this.fieldTable;
    }

    // ______________________________________| BUILDER |__________________________________________________\\

    public static class Builder {
        public Hashtable<Integer, Field> fieldTable;

        private static final int MAX_FIELDS = 64;
        private static final int MAX_FIELDS_WINTER = 70;

        private static final Hashtable<Integer, FieldTypes> FIELDS;
        static {
            FIELDS = new Hashtable<>();
            FIELDS.put(0, FieldTypes.START);
            FIELDS.put(5, FieldTypes.PECH);
            FIELDS.put(9, FieldTypes.PECH);
            FIELDS.put(6, FieldTypes.BÜCKE);
            FIELDS.put(14, FieldTypes.GLÜCK);
            FIELDS.put(18, FieldTypes.GLÜCK);
            FIELDS.put(27, FieldTypes.GLÜCK);
            FIELDS.put(32, FieldTypes.GLÜCK);
            FIELDS.put(36, FieldTypes.GLÜCK);
            FIELDS.put(50, FieldTypes.GLÜCK);
            FIELDS.put(19, FieldTypes.LABYRINTH);
            FIELDS.put(24, FieldTypes.DESASTER);
            FIELDS.put(41, FieldTypes.DESASTER);
            FIELDS.put(54, FieldTypes.DESASTER);
            FIELDS.put(52, FieldTypes.AUFSCHWUNG);
            FIELDS.put(58, FieldTypes.NEUANFANG);
            FIELDS.put(MAX_FIELDS - 1, FieldTypes.PARADIES);
        }

        private static final Hashtable<Integer, FieldTypes> FIELDS_WINTER;
        static {
            FIELDS_WINTER = new Hashtable<>();
            FIELDS_WINTER.put(0, FieldTypes.START);
            FIELDS_WINTER.put(5, FieldTypes.PECH);
            FIELDS_WINTER.put(9, FieldTypes.PECH);
            FIELDS_WINTER.put(6, FieldTypes.BÜCKE);
            FIELDS_WINTER.put(42, FieldTypes.BÜCKE);
            FIELDS_WINTER.put(14, FieldTypes.GLÜCK);
            FIELDS_WINTER.put(27, FieldTypes.GLÜCK);
            FIELDS_WINTER.put(32, FieldTypes.GLÜCK);
            FIELDS_WINTER.put(36, FieldTypes.GLÜCK);
            FIELDS_WINTER.put(50, FieldTypes.GLÜCK);
            FIELDS_WINTER.put(19, FieldTypes.LABYRINTH);
            FIELDS_WINTER.put(46, FieldTypes.LABYRINTH);
            FIELDS_WINTER.put(24, FieldTypes.DESASTER);
            FIELDS_WINTER.put(41, FieldTypes.DESASTER);
            FIELDS_WINTER.put(54, FieldTypes.DESASTER);
            FIELDS_WINTER.put(52, FieldTypes.AUFSCHWUNG);
            FIELDS_WINTER.put(58, FieldTypes.NEUANFANG);
            FIELDS_WINTER.put(MAX_FIELDS_WINTER - 1, FieldTypes.PARADIES);
        }

        public Builder setFields(String className){
            this.fieldTable = new Hashtable<>();
            int max_fields;
            Hashtable<Integer, FieldTypes> fields;
            if(className.equals("class club.dreiachteins.zmrl.ParadiesspielWinter")) {
                max_fields = MAX_FIELDS_WINTER;
                fields = FIELDS_WINTER;
            } else {
                max_fields = MAX_FIELDS;
                fields = FIELDS;
            }


            for(int i = 0; i < max_fields; i++){
                this.fieldTable.put(i, new Field.Builder()
                        .setFieldNumber(i)
                        .setFieldType(FieldTypes.STANDARD)
                        .build());
            }

            fields.forEach((k, v) -> {
                this.fieldTable.put(k, new Field.Builder()
                        .setFieldNumber(k)
                        .setFieldType(v)
                        .build());
            });

            return this;
        }

        public PlayingFieldGenerator build(){
            return new PlayingFieldGenerator(this);
        }

    }
}
