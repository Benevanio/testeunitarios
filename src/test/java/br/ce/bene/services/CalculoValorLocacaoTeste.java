package br.ce.bene.services;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.bene.build.BuilderFilme;
import br.ce.bene.entities.Filme;
import br.ce.bene.entities.Locacao;
import br.ce.bene.entities.Usuario;
import br.ce.bene.exceptions.FilmeSemEstoqueException;
import br.ce.bene.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTeste {
    private LocacaoService locacaoService;

    @Parameter
    public List<Filme> filmes;

    @Parameter(value = 1)
    public double valorLocacao;

    @Before
    public void setup() {
        locacaoService = new LocacaoService();
    }

    private static Filme filme1 = BuilderFilme.umFilme().agora();

    private static Filme filme2 = BuilderFilme.umFilme().agora();
    private static Filme filme3 = BuilderFilme.umFilme().agora();
    private static Filme filme4 = BuilderFilme.umFilme().agora();
    private static Filme filme5 = BuilderFilme.umFilme().agora();
    private static Filme filme6 = BuilderFilme.umFilme().agora();

    @Parameters(name = "Teste {index} = {0} - {1}")
    public static Collection<Object[]> parametros() {
        return Arrays.asList(new Object[][] {
                { Arrays.asList(filme1), 4.0 },
                { Arrays.asList(filme1, filme2), 8.0 },
                { Arrays.asList(filme1, filme2, filme3), 12.0 },
                { Arrays.asList(filme1, filme2, filme3, filme4), 16.0 },
                { Arrays.asList(filme1, filme2, filme3, filme4, filme5), 20.0 },
                { Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 24.0 }
        });
    }

    @Test
    public void deveCalcularValorLocacao() throws FilmeSemEstoqueException, LocadoraException {
        // cenario
        Usuario usuario = new Usuario("Usuario 1");
        // acao
        Locacao resultado = locacaoService.alugarFilme(usuario, filmes);
        // verificacao
        assertThat(resultado.getValor(), is(valorLocacao));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void deveLancarExcecaoFilmeSemEstoque() throws FilmeSemEstoqueException, LocadoraException {
        // cenario
        Filme filmeSemEstoque = new Filme("Filme Sem Estoque", 0, 4.0);
        List<Filme> filmesSemEstoque = Arrays.asList(filmeSemEstoque);
        Usuario usuario = new Usuario("Usuario 1");
        // acao
        locacaoService.alugarFilme(usuario, filmesSemEstoque);
    }
}
