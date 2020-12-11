package club.dreiachteins.zmrl.exceptions;

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
        return "This amount of Player: " + this.amount + " --> Min 2 | Max 6";
    }
}
