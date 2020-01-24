package com.example.demo.service.imple;

import java.util.List;

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

		if (nome.isEmpty()) {
			response.getErros().put("nomeCidadeErro", "O nome da cidade deve ser informado.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		
		if (response.getErros().isEmpty() && StringUtils.isEmpty(nome)) {
			Cidade cidade = cidadeRepository.findCidadeByNome(nome);
			response.setDado(cidade);
			response.setStatus(HttpStatus.OK);
		}
		
		return response;
	}

	@Override
	public Response<List<Cidade>> consultarEstado(String estado) {
		Response<List<Cidade>> response = new Response<>();
		
		if (estado.isEmpty()) {
			response.getErros().put("nomeEstadoErro", "O nome do estado deve ser informado.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		
		if (response.getErros().isEmpty() && !estado.isEmpty()) {
			List<Cidade> cidades = cidadeRepository.findByEstado(estado);
			response.setDado(cidades);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

}
