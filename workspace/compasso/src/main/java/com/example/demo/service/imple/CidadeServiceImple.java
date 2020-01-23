package com.example.demo.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.basica.Cidade;
import com.example.demo.controller.response.Response;
import com.example.demo.repository.CidadeRepository;
import com.example.demo.service.CidadeService;

@Service
public class CidadeServiceImple implements CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Override
	public Response<Cidade> cadastrar(Cidade cidade) {
		Response<Cidade> response = new Response<>();
		
		validar(cidade, response);
		if (response.getErros().isEmpty()) {
			Cidade cidadeSalva = cidadeRepository.save(cidade);
			response.setDado(cidadeSalva);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	private void validar(Cidade cidade, Response<Cidade> response) {
		if (StringUtils.isEmpty(cidade.getNome())) {
			response.getErros().put("nomeErro", "O nome da cidade deve ser preenchida.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		if (StringUtils.isEmpty(cidade.getEstado())) {
			response.getErros().put("estadoErro", "O estado deve ser preenchido.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Response<Cidade> consultarCidadePor(String nome) {
		Response<Cidade> response = new Response<>();
		Cidade cidade = cidadeRepository.findCidadeByNome(nome);
		response.setDado(cidade);
		return response;
	}

	@Override
	public Response<Cidade> consultarEstado(String estado) {
		Response<Cidade> response = new Response<>();
		Cidade cidade = cidadeRepository.findCidadeByEstado(estado);
		response.setDado(cidade);
		return response;
	}

}
