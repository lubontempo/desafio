package br.bb.desafio.bontempo.controller.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ResultDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object dto;
	private List list;
	private String message;
	private HttpStatus status;

	public Object getDto() {
		return dto;
	}

	public void setDto(Object dto) {
		this.dto = dto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
