package br.ce.bene.services;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {
    public static int contador = 0;

    @Test
    public void inicio() {
        contador = 1;
    }

    @Test
    public void verifica() {
        Assert.assertEquals(1, contador);
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        OrdemTest.contador = contador;
    }

    @Override
    public String toString() {
        return "OrdemTest []";
    }

}
