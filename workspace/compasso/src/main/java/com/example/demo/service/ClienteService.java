package com.example.demo.service;

import com.example.demo.basica.Cliente;
import com.example.demo.controller.response.Response;

public interface ClienteService {

	Response<Cliente> cadastrar(Cliente cliente);

	Response<Cliente> consultarClientePor(String nomeCompleto);

	Response<Cliente> consultarClientePor(Integer id);
	
	void remover(Cliente cliente);
	
	Response<Cliente> alterarNomeCliente(String nomeAntigo, String nomeNovo);
}
