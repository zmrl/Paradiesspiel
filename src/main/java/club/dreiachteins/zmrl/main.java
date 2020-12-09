package club.dreiachteins.zmrl;

import club.dreiachteins.zmrl.enums.Farbe;

public class main {

    public static void main(String[] args) {
        Paradiesspiel p1 = new Paradiesspiel(Farbe.BLAU, Farbe.GRÜN, Farbe.ROT, Farbe.GELB);
        p1.getPlayerByID(1).getTokenByName("BLAU-A").setPosition(12);
        System.out.println(p1);
        System.out.println();

        Paradiesspiel p2 = new ParadiesspielWinter(Farbe.ROT, Farbe.GELB, Farbe.GRÜN);
        System.out.println(p2);
        System.out.println();

        // throws exception
        Paradiesspiel p3 = new Paradiesspiel(Farbe.GELB);
        System.out.println(p3);
    }

}
