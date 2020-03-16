package br.bb.desafio.bontempo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

@RestController
@RequestMapping(value = "/cotacao")
public class CotacaoDolarController {

	@Autowired
	private CotacaoDolarService service;
	
	@RequestMapping(value = "/buscar/{data}", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> buscarCotacao(@PathVariable(value = "data", required = true) String data) {
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
		//TODO Validar serviço fora do ar
		//TODO Se a busca der errado
		//TODO Validar data (formato e dia não útil)
		
        return new ResponseEntity<ResultDTO>(resultDTO, resultDTO.getStatus());
    }

	private void validarData(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		sdf.setLenient(false);
		sdf.parse(data);
	}
}
