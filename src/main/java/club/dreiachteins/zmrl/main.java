package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.Farbe;

import java.util.Arrays;

public class main {

    public static void main(String[] args) {
//        Paradiesspiel p1 = new Paradiesspiel(Farbe.BLAU, Farbe.GRUEN, Farbe.ROT, Farbe.GELB);
//        p1.setFigurPostiton("BLAU-A", 4);
//        p1.bewegeFigur("BLAU-A", 1, 1);
//        System.out.println(p1);
//        System.out.println();

        Paradiesspiel p2 = new ParadiesspielWinter("ROT-A:61, ROT-B:51, GELB-B:62", Farbe.ROT, Farbe.GELB, Farbe.GRUEN);
        System.out.println(p2);
        System.out.println();

//        // throws exception
//        Paradiesspiel p3 = new Paradiesspiel(Farbe.GELB);
//        System.out.println(p3);
    }

}
