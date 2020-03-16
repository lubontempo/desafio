package br.bb.desafio.bontempo.webservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CotacaoDolarWS {

	private String cotacaoCompra;
	private String cotacaoVenda;
	private String dataHoraCotacao;

	public String getCotacaoCompra() {
		return cotacaoCompra;
	}

	public void setCotacaoCompra(String cotacaoCompra) {
		this.cotacaoCompra = cotacaoCompra;
	}

	public String getCotacaoVenda() {
		return cotacaoVenda;
	}

	public void setCotacaoVenda(String cotacaoVenda) {
		this.cotacaoVenda = cotacaoVenda;
	}

	public String getDataHoraCotacao() {
		return dataHoraCotacao;
	}

	public void setDataHoraCotacao(String dataHoraCotacao) {
		this.dataHoraCotacao = dataHoraCotacao;
	}
}
