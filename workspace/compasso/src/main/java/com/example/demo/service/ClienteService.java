package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.example.demo.basica.Cliente;
import com.example.demo.controller.response.Response;

public interface ClienteService {

	ResponseEntity<Response<Cliente>> cadastrar(Cliente cliente, BindingResult result);

	Response<Cliente> consultarClientePor(String nomeCompleto);

	Response<Cliente> consultarClientePor(Integer id);
	
	void remover(Cliente cliente);
	
	ResponseEntity<Response<Cliente>> alterarNomeCliente(String nomeAntigo, String nomeNovo);
}
