package br.bb.desafio.bontempo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "cotacaoDolar")
@Table(name = "tb_cotacao_dolar")
public class CotacaoDolar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false, name = "data_requisicao")
	private String dataRequisicao;
	
	@Column(nullable = false, name = "data_cotacao")
	private String dataCotacao;
	
	@Column(nullable = false, name = "data_hora_cotacao")
	private String dataHoraCotacao;
	
	@Column(nullable = false, name = "vl_cotacao_compra")
	private String vlCotacaoCompra;
	
	@Column(nullable = false, name = "vl_cotacao_venda")
	private String vlCotacaoVenda;

	public CotacaoDolar() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDataRequisicao() {
		return dataRequisicao;
	}

	public void setDataRequisicao(String dataRequisicao) {
		this.dataRequisicao = dataRequisicao;
	}

	public String getDataCotacao() {
		return dataCotacao;
	}

	public void setDataCotacao(String dataCotacao) {
		this.dataCotacao = dataCotacao;
	}

	public String getDataHoraCotacao() {
		return dataHoraCotacao;
	}

	public void setDataHoraCotacao(String dataHoraCotacao) {
		this.dataHoraCotacao = dataHoraCotacao;
	}

	public String getVlCotacaoCompra() {
		return vlCotacaoCompra;
	}

	public void setVlCotacaoCompra(String vlCotacaoCompra) {
		this.vlCotacaoCompra = vlCotacaoCompra;
	}

	public String getVlCotacaoVenda() {
		return vlCotacaoVenda;
	}

	public void setVlCotacaoVenda(String vlCotacaoVenda) {
		this.vlCotacaoVenda = vlCotacaoVenda;
	}
	
}
