package br.ce.bene.services;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.bene.entities.Filme;
import br.ce.bene.entities.Locacao;
import br.ce.bene.entities.Usuario;
import br.ce.bene.utils.DataUtils;

public class LocacaoServiceTest {

    @Before
    public void setup() {

    }

    @Test
    public void test() {
        // cenario
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 1", 2, 5.0);

        // acao
        Locacao locacao = locacaoService.alugarFilme(usuario, filme);

        // verificacao
        Assert.assertTrue(locacao.getValor() == 5.0);
        Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));

    }
}
