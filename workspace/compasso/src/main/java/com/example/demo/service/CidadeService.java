package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.example.demo.basica.Cidade;
import com.example.demo.controller.response.Response;

public interface CidadeService {

	ResponseEntity<Response<Cidade>> cadastrar(Cidade cidade, BindingResult result);

	ResponseEntity<Response<Cidade>> consultarCidadePor(String nome);

	ResponseEntity<Response<List<Cidade>>> consultarEstado(String estado);
}
