package com.example.demo.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.basica.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@Service
public class ClienteServiceImple implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente consultarClientePor(String nomeCompleto) {
		return clienteRepository.findClienteByNomeCompleto(nomeCompleto);
	}

	@Override
	public Cliente consultarClientePor(Integer id) {
		return clienteRepository.findClienteById(id);
	}

	@Override
	public void remover(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	@Override
	public Cliente alterarNomeCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

}
