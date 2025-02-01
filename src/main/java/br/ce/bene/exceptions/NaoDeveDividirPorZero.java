package br.ce.bene.exceptions;

public class NaoDeveDividirPorZero extends Exception {

    private static final long serialVersionUID = 3837982804180390821L;

    public NaoDeveDividirPorZero(String message) {
        super(message);
    }
}
