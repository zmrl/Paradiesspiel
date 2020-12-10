package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.Farbe;

import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        Paradiesspiel p1 = new Paradiesspiel(Farbe.BLAU, Farbe.GRUEN, Farbe.ROT, Farbe.GELB);
        System.out.println(p1);
        System.out.println();

        Paradiesspiel p2 = new ParadiesspielWinter(Farbe.ROT, Farbe.GELB, Farbe.GRUEN);
        System.out.println(p2);
        System.out.println(p2.getFigurposition("GELB-F"));
        System.out.println();

        // throws exception
        Paradiesspiel p3 = new Paradiesspiel(Farbe.GELB, Farbe.GRUEN);
        System.out.println(p3);
    }

}
