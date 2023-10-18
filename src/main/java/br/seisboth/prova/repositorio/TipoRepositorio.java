package br.seisboth.prova.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.seisboth.prova.modelo.Tipo;

public interface TipoRepositorio extends JpaRepository<Tipo, Long> {
}
