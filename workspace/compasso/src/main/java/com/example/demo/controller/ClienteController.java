package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@RequestMapping(value = "consultarClientePor/{nome}", method = RequestMethod.GET)
	public ResponseEntity<Response<Cliente>> consultarClientePor(@PathVariable("nome") String nome) {
		Response<Cliente> response = this.service.consultarClientePor(nome);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}
	
	@RequestMapping(value = "consultarClientePor/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response<Cliente>> consultarClientePor(@PathVariable("id") int id) {
		Response<Cliente> response = this.service.consultarClientePor(id);
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}
	
	@DeleteMapping(value = "remover/")
	public ResponseEntity<Response<Cliente>> remover(@RequestBody Cliente cliente) {
		Boolean objetoRemovido = Boolean.FALSE;
		
		this.service.remover(cliente);
		Response<Cliente> response = this.service.consultarClientePor(cliente.getId());
		if (response.getDado() == null) {
			objetoRemovido = Boolean.TRUE;
		}
		if (!objetoRemovido) {
			response.getErros().put("clienteNaoExisteErro", "O Cliente informado não existe.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		HttpStatus status = response.getStatus();
		return new ResponseEntity<Response<Cliente>>(response, status);
	}
	

	// TODO - Parei aqui
	@PutMapping(value = "alterarNomeCliente/{nomeCliente}")
	public ResponseEntity<Response<Cliente>> alterarNomeCliente(@PathVariable("nomeCliente") String nomeCliente) {
		Boolean objetoRemovido = Boolean.FALSE;
		Response<Cliente> consultarClientePor = this.service.consultarClientePor(nomeCliente);
		//return new ResponseEntity<Response<Cliente>>(response, status);
		return null;
	}
}