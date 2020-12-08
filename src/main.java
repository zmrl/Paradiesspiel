import enums.Farben;
import exceptions.WrongPlayerCountException;

public class main {

    public static void main(String[] args) {
        Paradiesspiel p1 = new Paradiesspiel(Farben.BLAU, Farben.GRÜN, Farben.ROT, Farben.GELB);
        System.out.println(p1);
        System.out.println();

        Paradiesspiel p2 = new ParadiesspielWinter(Farben.ROT, Farben.GELB, Farben.GRÜN);
        System.out.println(p2);
        System.out.println();

        //wirft exception --> zu viele spieler
        Paradiesspiel p3 = new Paradiesspiel(Farben.GELB, Farben.GELB, Farben.GELB, Farben.GELB, Farben.GELB, Farben.GELB, Farben.GELB );
        p1.getPlayerByID(1).getTokenByName("BLAU-A").setPosition(12);
        System.out.println(p3);
    }

}