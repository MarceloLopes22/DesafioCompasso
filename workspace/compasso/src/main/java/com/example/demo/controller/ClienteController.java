package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.basica.Cliente;
import com.example.demo.controller.response.Response;
import com.example.demo.service.ClienteService;

@RestController
@RequestMapping("/api/cliente/")
@CrossOrigin(value = "*")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@PostMapping(value = "cadastrar")
	public ResponseEntity<Response<Cliente>> cadastrar(HttpServletRequest request, @RequestBody Cliente cliente) {
		Response<Cliente> response = this.service.cadastrar(cliente);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}

	@GetMapping(value= "consultarClientePor/{nomeCompleto}")
	public ResponseEntity<Response<Cliente>> consultarClientePor(@PathVariable(value = "nomeCompleto") String nomeCompleto) {
		Response<Cliente> response = this.service.consultarClientePor(nomeCompleto);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}
	
	@RequestMapping(value = "pesquisarClientePor/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Cliente>> pesquisarClientePor(@PathVariable("id") Integer id) {
		Response<Cliente> response = this.service.consultarClientePor(id);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}
	
	@DeleteMapping(value = "remover/")
	public ResponseEntity<Response<Cliente>> remover(@RequestBody Cliente cliente) {
		this.service.remover(cliente);
		return new ResponseEntity<Response<Cliente>>(HttpStatus.OK);
	}
	

	@PutMapping(value = "alterarNomeCliente/{nomeAntigo}/{nomeNovo}")
	public ResponseEntity<Response<Cliente>> alterarNomeCliente(
				@PathVariable(value = "nomeAntigo") String nomeAntigo,
				@PathVariable(value = "nomeNovo") String nomeNovo) {
		Response<Cliente> response = new Response<Cliente>();
		
		response = this.service.alterarNomeCliente(nomeAntigo, nomeNovo);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}
}