package com.example.demo.service;

import java.util.List;

import com.example.demo.basica.Cidade;
import com.example.demo.controller.response.Response;

public interface CidadeService {

	Response<Cidade> cadastrar(Cidade cidade);

	Response<Cidade> consultarCidadePor(String nome);

	Response<List<Cidade>> consultarEstado(String estado);
}
