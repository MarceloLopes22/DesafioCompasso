package com.example.demo.service;

import com.example.demo.basica.Cidade;
import com.example.demo.controller.response.Response;

public interface CidadeService {

	Response<Cidade> cadastrar(Cidade cidade);

	Response<Cidade> consultarCidadePor(String nome);

	Response<Cidade> consultarCidade(String estado);
}
