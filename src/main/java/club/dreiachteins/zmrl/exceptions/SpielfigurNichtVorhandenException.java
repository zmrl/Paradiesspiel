package club.dreiachteins.zmrl.exceptions;

public class SpielfigurNichtVorhandenException extends NullPointerException{
    private String name;

    public SpielfigurNichtVorhandenException(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return name + " doesn't exist";
    }
}
