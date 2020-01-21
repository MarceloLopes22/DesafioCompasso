package com.example.demo.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.basica.Cidade;
import com.example.demo.repository.CidadeRepository;
import com.example.demo.service.CidadeService;

@Service
public class CidadeServiceImple implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public Cidade cadastrar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}

	@Override
	public Cidade consultarCidadePor(String nome) {
		return cidadeRepository.findCidadeByNome(nome);
	}

	@Override
	public Cidade consultarCidade(String estado) {
		return cidadeRepository.findCidadeByEstado(estado);
	}

}
