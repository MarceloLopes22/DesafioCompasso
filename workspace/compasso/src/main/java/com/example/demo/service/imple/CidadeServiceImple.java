package com.example.demo.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.example.demo.basica.Cidade;
import com.example.demo.controller.response.Response;
import com.example.demo.repository.CidadeRepository;
import com.example.demo.service.CidadeService;

@Service
public class CidadeServiceImple implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public ResponseEntity<Response<Cidade>> cadastrar(Cidade cidade, BindingResult result) {
		Response<Cidade> response = new Response<>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			response.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(response);
		}
		
		if (response.getErros().isEmpty()) {
			Cidade cidadeSalva = cidadeRepository.save(cidade);
			response.setDado(cidadeSalva);
			response.setStatus(HttpStatus.OK);
		}
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<Cidade>> consultarCidadePor(String nome) {
		Response<Cidade> response = new Response<>();

		if (nome.isEmpty()) {
			response.getErros().add("O nome da cidade deve ser informado.");
			response.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(response);
		}
		
		if (response.getErros().isEmpty() && !StringUtils.isEmpty(nome)) {
			Cidade cidade = cidadeRepository.findCidadeByNome(nome);
			response.setDado(cidade);
			response.setStatus(HttpStatus.OK);
		}
		
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<Cidade>>> consultarEstado(String estado) {
		Response<List<Cidade>> response = new Response<>();
		
		if (estado.isEmpty()) {
			response.getErros().add("O nome do estado deve ser informado.");
			response.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(response);
		}
		
		if (response.getErros().isEmpty() && !estado.isEmpty()) {
			List<Cidade> cidades = cidadeRepository.findByEstado(estado);
			response.setDado(cidades);
			response.setStatus(HttpStatus.OK);
		}
		return ResponseEntity.ok(response);
	}

}
