package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.basica.Cliente;
import com.example.demo.controller.response.Response;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/v1/api/cliente/")
@CrossOrigin(value = "*")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@PostMapping(value = "cadastrar")
	public ResponseEntity<Response<Cliente>> cadastrar(HttpServletRequest request,@Valid @RequestBody Cliente cliente,
			BindingResult result) {
		ResponseEntity<Response<Cliente>> response = this.service.cadastrar(cliente, result);
		return response;
	}

	@GetMapping(value= "consultar-cliente")
	public ResponseEntity<Response<Cliente>> consultarClientePor(@RequestParam(value = "nomeCompleto") String nomeCompleto) {
		Response<Cliente> response = this.service.consultarClientePor(nomeCompleto);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}
	
	@RequestMapping(value = "pesquisar-cliente", method = RequestMethod.GET)
	public ResponseEntity<Response<Cliente>> pesquisarClientePor(@RequestParam("id") Integer id) {
		Response<Cliente> response = this.service.consultarClientePor(id);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}
	
	@DeleteMapping(value = "remover")
	public ResponseEntity<Response<Cliente>> remover(@RequestBody Cliente cliente) {
		this.service.remover(cliente);
		return new ResponseEntity<Response<Cliente>>(HttpStatus.OK);
	}
	

	@PutMapping(value = "alterar-nome-cliente")
	public ResponseEntity<Response<Cliente>> alterarNomeCliente(
			@RequestParam(value = "nome-antigo") String nomeAntigo,
			@RequestParam(value = "nome-novo") String nomeNovo) {
		ResponseEntity<Response<Cliente>> response = null;
		response = this.service.alterarNomeCliente(nomeAntigo, nomeNovo);
		return response;
	}
}