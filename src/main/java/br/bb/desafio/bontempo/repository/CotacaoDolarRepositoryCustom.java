package br.bb.desafio.bontempo.repository;

import org.springframework.stereotype.Repository;

import br.bb.desafio.bontempo.entity.CotacaoDolar;

@Repository
public interface CotacaoDolarRepositoryCustom {

	CotacaoDolar recuperaCotacaoPorData(String dataCotacao);
}