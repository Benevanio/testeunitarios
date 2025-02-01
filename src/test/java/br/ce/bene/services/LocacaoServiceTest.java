package br.ce.bene.services;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.bene.build.BuilderFilme;
import br.ce.bene.build.BuilderUsuario;
import br.ce.bene.entities.Filme;
import br.ce.bene.entities.Locacao;
import br.ce.bene.entities.Usuario;
import br.ce.bene.exceptions.FilmeSemEstoqueException;
import br.ce.bene.exceptions.LocadoraException;
import br.ce.bene.utils.DataUtils;
import junit.framework.Assert;

public class LocacaoServiceTest {
    private int contador = 0;

    private LocacaoService locacaoService;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        System.out.println("Iniciando 2...");
        locacaoService = new LocacaoService();
        contador++;
        System.out.println("Contador\n" + contador);

    }

    @After
    public void tearDown() {
        System.out.println("Finalizando 2...");
    }

    @BeforeClass
    public static void setupClass() {
        System.out.println("Iniciando 2...");

    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Finalizando 2...");
    }

    @Test
    public void testeLocacao() throws Exception {
        // cenario
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = BuilderUsuario.umUsuario().agora();
        List<Filme> filmes = Arrays.asList(BuilderFilme.umFilme().agora());
        // acao
        Locacao locacao = locacaoService.alugarFilme(usuario, filmes);

        // verificacao usando o Before
        // error.checkThat(locacao.getValor(), org.hamcrest.CoreMatchers.is(5.0));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
                org.hamcrest.CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
                org.hamcrest.CoreMatchers.is(true));

    }

    @Test
    public void test() throws FilmeSemEstoqueException, LocadoraException {
        // cenario
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = BuilderUsuario.umUsuario().agora();
        Filme filme = BuilderFilme.umFilme().agora();

        // action
        Locacao locacao = locacaoService.alugarFilme(usuario, Arrays.asList(filme));
        // verificacao

        // error.checkThat(locacao.getValor(), org.hamcrest.CoreMatchers.is(5.0));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()),
                org.hamcrest.CoreMatchers.is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)),
                org.hamcrest.CoreMatchers.is(true));

    }

    @SuppressWarnings("deprecation")
    @Test
    @Ignore
    public void NaoDeveDevolverNodomingo() throws FilmeSemEstoqueException, LocadoraException {
        // cenario
        LocacaoService locacaoService = new LocacaoService();
        Usuario usuario = BuilderUsuario.umUsuario().agora();
        Filme filme = BuilderFilme.umFilme().agora();

        // action
        Locacao locacao = locacaoService.alugarFilme(usuario, Arrays.asList(filme));

        // verificacao
        boolean ehSegunda = DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY);
        Assert.assertTrue(ehSegunda);

    }

}
