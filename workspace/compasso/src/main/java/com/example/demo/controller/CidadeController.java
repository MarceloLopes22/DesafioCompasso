package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.basica.Cidade;
import com.example.demo.controller.response.Response;
import com.example.demo.service.CidadeService;

@RestController
@RequestMapping("/api/cidade/")
@CrossOrigin(value = "*")
public class CidadeController {
	
	@Autowired
	private CidadeService service;

	@PostMapping(value = "cadastrar")
	public ResponseEntity<Response<Cidade>> cadastrar(HttpServletRequest request, @RequestBody Cidade cidade) {
		Response<Cidade> response = this.service.cadastrar(cidade);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cidade>>(response, status);
	}

	@RequestMapping(value = "consultarCidadePor/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Response<Cidade>> consultarCidadePor(@PathVariable("nome") String nome) {
		Response<Cidade> response = this.service.consultarCidadePor(nome);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cidade>>(response, status);
	}
	
	@RequestMapping(value = "consultarEstado/{estado}", method = RequestMethod.GET)
	public ResponseEntity<Response<Cidade>> consultarEstado(@PathVariable("estado") String estado) {
		Response<Cidade> response = this.service.consultarCidadePor(estado);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cidade>>(response, status);
	}
}
