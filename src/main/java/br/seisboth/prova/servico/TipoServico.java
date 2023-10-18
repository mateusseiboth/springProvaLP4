package br.seisboth.prova.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.seisboth.prova.modelo.Tipo;
import br.seisboth.prova.repositorio.TipoRepositorio;


@Service
public class TipoServico {

	@Autowired
	private TipoRepositorio tipoRepositorio;
	
	public Tipo criarTipo(Tipo tipo) {
		return tipoRepositorio.save(tipo);
	}
	
	public Tipo alterarTipo(Tipo tipo) {
		return tipoRepositorio.save(tipo);
	}
}
