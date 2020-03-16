package br.bb.desafio.bontempo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.bb.desafio.bontempo.controller.dto.ResultDTO;
import br.bb.desafio.bontempo.entity.CotacaoDolar;
import br.bb.desafio.bontempo.service.CotacaoDolarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/cotacao")
public class CotacaoDolarController {

	@Autowired
	private CotacaoDolarService service;

	@ApiOperation(value = "Recupera a cotação do dólar por data.", response = ResponseEntity.class, notes = "Recupera a cotação do dólar na data especificada no parâmetro. Formato esperado: DD-MM-AAAA.")
	@ApiResponses(value= {
			@ApiResponse(
					code=200, 
					message="Retorna um ResponseEntity com o objeto ResultDTO com o atributo obj preenchido",
					response=ResponseEntity.class
					),
			@ApiResponse(
					code=400, 
					message="Em casos de data inválida é retornada mensagem correspondente no objeto ResultDTO no atributo message",
					response=ResponseEntity.class
					),
			@ApiResponse(
					code=204, 
					message="Quando a cotação não é encontrada, nenhum dado é exibido",
					response=ResponseEntity.class
					)
	})
	@RequestMapping(value = "/buscar/{data}", method = RequestMethod.GET)
	public ResponseEntity<ResultDTO> buscarCotacaoPorDate(@PathVariable(value = "data", required = true) String data) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setStatus(HttpStatus.NO_CONTENT);

		try {
			validarData(data);

			CotacaoDolar cotacao = service.buscarCotacao(data);
			if (cotacao != null) {
				resultDTO.setDto(cotacao);
				resultDTO.setStatus(HttpStatus.OK);
			}
		} catch (ParseException e) {
			resultDTO.setMessage("Data inválida");
			resultDTO.setStatus(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<ResultDTO>(resultDTO, resultDTO.getStatus());
	}
	
	@RequestMapping(value = "/buscarPorMes/{mes}", method = RequestMethod.GET)
	public ResponseEntity<ResultDTO> buscarCotacaoPorMes(@PathVariable(value = "mes", required = true) String mes) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setStatus(HttpStatus.NO_CONTENT);

		try {
			validarMes(mes);

			List<CotacaoDolar> list = service.buscarCotacaoPorMes(mes);
			resultDTO.setList(list);
			resultDTO.setStatus(HttpStatus.OK);
		} catch (Exception e) {
			resultDTO.setMessage("Mês inválido");
			resultDTO.setStatus(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<ResultDTO>(resultDTO, resultDTO.getStatus());
	}

	private void validarData(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		sdf.parse(data);
	}
	
	private void validarMes(String mes) throws Exception {
		Integer nuMes = Integer.parseInt(mes);
		if (nuMes < 1 || nuMes > 12) {
			throw new Exception("Mês inválido.");
		}
	}
	
}
