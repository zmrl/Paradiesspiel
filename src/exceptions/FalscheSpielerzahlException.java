package exceptions;

public class FalscheSpielerzahlException extends RuntimeException{
    private int amount;

    public FalscheSpielerzahlException(int amount){
        this.amount = amount;
    }

    @Override
    public String getMessage() {
        return getAmount();
    }

    public String getAmount(){
        return "Amount of Players: " + this.amount + " --> Min 2 Player or Max 6 Player";
    }
}
