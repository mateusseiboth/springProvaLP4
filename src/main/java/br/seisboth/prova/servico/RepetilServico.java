package br.seisboth.prova.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.seisboth.prova.excecao.RepetilNotFoundException;
import br.seisboth.prova.modelo.Repetil;
import br.seisboth.prova.repositorio.RepetilRepositorio;


@Service
public class RepetilServico {

	@Autowired
	private RepetilRepositorio repetilRepositorio;
	
	public Repetil criarRepetil(Repetil repetil) {
		return repetilRepositorio.save(repetil);
	}

	public Repetil buscarRepetilPorId(Long id) throws RepetilNotFoundException {
		Optional<Repetil> opt = repetilRepositorio.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new RepetilNotFoundException("Repetil com id : " + id + " não existe");
		}
	}
	
	public List<Repetil> buscarRepetilsPorTamanho(String tamanho) {
		return repetilRepositorio.findByTamanhoContainingIgnoreCase(tamanho);
	}

	public List<Repetil> buscarTodosRepetils() {
		return repetilRepositorio.findAll();
	}

	public Repetil alterarRepetil(Repetil repetil) {
		return repetilRepositorio.save(repetil);
	}

	public void apagarRepetil(Long id) throws RepetilNotFoundException {
		Repetil repetil = buscarRepetilPorId(id);
		repetilRepositorio.delete(repetil);
	}
}
