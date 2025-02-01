package br.ce.bene.services;

import org.junit.Assert;
import org.junit.Test;

import br.ce.bene.exceptions.NaoDeveDividirPorZero;

public class CalculadoraTeste {

    @Test
    public void somarDoisValores() {
        System.out.println("Teste 1");
        // cenario
        int a = 5;
        int b = 3;

        Calculadora calc = new Calculadora();

        // acao

        int resultado = calc.somar(a, b);
        // verificacao
        Assert.assertEquals(8, resultado);
    }

    @Test
    public void subtrairDoisValores() {
        System.out.println("Teste 2");
        // cenario
        int a = 8;
        int b = 5;

        Calculadora calc = new Calculadora();

        // acao

        int resultado = calc.subtrair(a, b);
        // verificacao
        Assert.assertEquals(3, resultado);
    }

    @Test
    public void dividirDoisValores() throws NaoDeveDividirPorZero {
        System.out.println("Teste 3");
        // cenario
        int a = 6;
        int b = 3;

        Calculadora calc = new Calculadora();

        // acao

        int resultado = calc.dividir(a, b);
        // verificacao
        Assert.assertEquals(2, resultado);
    }

    @Test(expected = NaoDeveDividirPorZero.class)
    public void NaodeveDividirPorZero() throws NaoDeveDividirPorZero {
        System.out.println("Teste 4");
        // cenario
        int a = 6;
        int b = 0;

        Calculadora calc = new Calculadora();

        // acao

        int resultado = calc.dividir(a, b);
        // verificacao
        Assert.assertEquals(0, resultado);
    }

}
