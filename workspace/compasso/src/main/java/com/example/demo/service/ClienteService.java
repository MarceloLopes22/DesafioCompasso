package com.example.demo.service;

import com.example.demo.basica.Cliente;

public interface ClienteService {

	Cliente cadastrar(Cliente cliente);

	Cliente consultarClientePor(String nomeCompleto);

	Cliente consultarClientePor(Integer id);
	
	void remover(Cliente cliente);
	
	Cliente alterarNomeCliente(Cliente cliente);
}
