package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.basica.Cidade;
import com.example.demo.controller.response.Response;
import com.example.demo.service.CidadeService;

@RestController
@RequestMapping("/v1/api/cidade/")
@CrossOrigin(value = "*")
public class CidadeController {
	
	@Autowired
	private CidadeService service;

	@PostMapping(value = "cadastrar")
	public ResponseEntity<Response<Cidade>> cadastrar(HttpServletRequest request,@Valid @RequestBody Cidade cidade,
			BindingResult result) {
		ResponseEntity<Response<Cidade>> response = this.service.cadastrar(cidade, result);
		return response;
	}
	
	@GetMapping(value = "consultar-cidade")
	public ResponseEntity<Response<Cidade>> consultarCidade(@RequestParam(value = "nomeCidade") String nomeCidade) {
		ResponseEntity<Response<Cidade>> response = this.service.consultarCidadePor(nomeCidade);
		return response;
	}
	
	@GetMapping(value = "consultar-estado")
	public ResponseEntity<Response<List<Cidade>>> consultarEstado(@RequestParam(value = "nomeEstado") String nomeEstado) {
		ResponseEntity<Response<List<Cidade>>> response = this.service.consultarEstado(nomeEstado);
		return response;
	}

	/*@RequestMapping(name = "consultar-cidade/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Response<Cidade>> consultarCidade(@RequestBody String nome) {
		ResponseEntity<Response<Cidade>> response = this.service.consultarCidadePor(nome);
		return response;
	}*/

//	@GetMapping("consultarEstado/{nomeEstado}")
//	public ResponseEntity<Response<List<Cidade>>> consultarEstado(@PathVariable("nomeEstado") String nomeEstado) {
//		ResponseEntity<Response<List<Cidade>>> response = this.service.consultarEstado(nomeEstado);
//		return response;
//	}
}
