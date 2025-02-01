package br.ce.bene.services;

import br.ce.bene.exceptions.NaoDeveDividirPorZero;

public class Calculadora {

    public int somar(int a, int b) {
        if (a > 0 && b > 0) {
            return a + b;
        } else {
            return 0;

        }

    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int dividir(int a, int b) throws NaoDeveDividirPorZero {
        if (b == 0) {
            throw new NaoDeveDividirPorZero("Não é possível dividir por zero");
        }
        return a / b;
    }

}
