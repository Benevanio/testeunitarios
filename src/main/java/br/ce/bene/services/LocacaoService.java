package br.ce.bene.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.bene.entities.Filme;
import br.ce.bene.entities.Locacao;
import br.ce.bene.entities.Usuario;
import br.ce.bene.exceptions.FilmeSemEstoqueException;
import br.ce.bene.exceptions.LocadoraException;
import br.ce.bene.utils.DataUtils;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}

		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		for (Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new FilmeSemEstoqueException();
			}
		}

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		Double valorTotal = 0d;
		for (int i = 0; i < filmes.size(); i++) {
			Filme filme = filmes.get(i);
			Double valorFilme = filme.getPrecoLocacao();
			switch (i) {
				case 2:
					valorFilme = valorFilme * 0.75;
					break;
				case 3:
					valorFilme = valorFilme * 0.5;
					break;
				case 4:
					valorFilme = valorFilme * 0.25;
					break;
				case 5:
					valorFilme = 0d;
					break;
			}
			valorTotal += valorFilme;
		}
		locacao.setValor(valorTotal);
		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		if (DataUtils.verificarDiaSemana(locacao.getDataRetorno(), Calendar.MONDAY)) {
			locacao.setDataRetorno(adicionarDias(locacao.getDataRetorno(), 1));
		}

		return locacao;
	}

	private Date adicionarDias(Date dataEntrega, int i) {
		if (i == 0) {
			return dataEntrega;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataEntrega);
		cal.add(Calendar.DAY_OF_MONTH, i);
		return cal.getTime();
	}
}