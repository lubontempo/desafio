package br.bb.desafio.bontempo.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.bb.desafio.bontempo.entity.CotacaoDolar;

@Repository
@Transactional(readOnly = true)
public class CotacaoDolarRepositoryImpl implements CotacaoDolarRepositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public CotacaoDolar recuperaCotacaoPorData(String dataCotacao) {
		CotacaoDolar result = null;

        Query query = entityManager.createNativeQuery("SELECT em.* FROM tb_cotacao_dolar as em WHERE data_cotacao = :dtCotacao", CotacaoDolar.class);
        query.setParameter("dtCotacao", dataCotacao);
        query.setMaxResults(1);

        try {
        	 Object obj = query.getSingleResult();
             if (obj != null) {
             	result = (CotacaoDolar) obj;
             }
		} catch (NoResultException e) {
		}
       
        return result;
	}

}
