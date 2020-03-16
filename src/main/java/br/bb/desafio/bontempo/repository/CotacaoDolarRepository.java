package br.bb.desafio.bontempo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.bb.desafio.bontempo.entity.CotacaoDolar;

@Repository
public interface CotacaoDolarRepository extends JpaRepository<CotacaoDolar, Long>, CotacaoDolarRepositoryCustom {

}