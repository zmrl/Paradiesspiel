package exceptions;

public class WrongPlayerCountException extends RuntimeException{
    private int amount;

    public WrongPlayerCountException(int amount){
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
