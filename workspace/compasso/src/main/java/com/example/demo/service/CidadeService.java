package com.example.demo.service;

import com.example.demo.basica.Cidade;

public interface CidadeService {

	Cidade cadastrar(Cidade cidade);

	Cidade consultarCidadePor(String nome);

	Cidade consultarCidade(String estado);
}
