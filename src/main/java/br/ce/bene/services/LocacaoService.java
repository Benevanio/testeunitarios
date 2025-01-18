package br.ce.bene.services;

import static br.ce.bene.utils.DataUtils.*;

import java.util.Date;

import br.ce.bene.entities.Filme;
import br.ce.bene.entities.Locacao;
import br.ce.bene.entities.Usuario;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		return locacao;
	}
}