package br.seisboth.prova.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.seisboth.prova.modelo.Repetil;

public interface RepetilRepositorio extends JpaRepository<Repetil, Long> {
	List<Repetil> findByTamanhoContainingIgnoreCase(String tamanho);

}
