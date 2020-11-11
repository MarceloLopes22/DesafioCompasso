package com.example.demo.service.imple;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.example.demo.basica.Cliente;
import com.example.demo.controller.response.Response;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@Service
public class ClienteServiceImple implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public ResponseEntity<Response<Cliente>> cadastrar(Cliente cliente, BindingResult result) {
		Response<Cliente> response = new Response<>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
			response.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(response);
		}
		
		if (response.getErros().isEmpty()) {
			Cliente clienteSalvo = clienteRepository.save(cliente);
			response.setDado(clienteSalvo);
			response.setStatus(HttpStatus.OK);
		}
		return ResponseEntity.ok(response);
	}

	@Override
	public Response<Cliente> consultarClientePor(String nomeCompleto) {
		Response<Cliente> response = new Response<>();
		
		if (StringUtils.isEmpty(nomeCompleto)) {
			response.getErros().add("O nome da cidade deve ser preenchida.");
			response.setStatus(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		Cliente cliente = clienteRepository.findClienteByNomeCompleto(nomeCompleto);
		response.setDado(cliente);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public Response<Cliente> consultarClientePor(Integer id) {
		Response<Cliente> response = new Response<>();
		
		if (id == null) {
			response.getErros().add("O id deve ser preenchido.");
			response.setStatus(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			response.setDado(optional.get());
			response.setStatus(HttpStatus.OK);
		} else {
			response.setDado(new Cliente());
			response.setStatus(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@Override
	public void remover(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	@Override
	public ResponseEntity<Response<Cliente>> alterarNomeCliente(String nomeAntigo, String nomeNovo) {
		Response<Cliente> response = new Response<>();
		
		if (StringUtils.isEmpty(nomeAntigo) || StringUtils.isEmpty(nomeNovo)) {
			response.getErros().add("Os nomes antigo e novo devem ser informados.");
			response.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(response);
		}
		
		response = this.consultarClientePor(nomeAntigo);
		Cliente clienteConsultado = response.getDado();
		
		if (Objects.isNull(clienteConsultado)) {
			response.getErros().add("Não existe nenhum cliente com esse nome = " + nomeAntigo);
			response.setStatus(HttpStatus.BAD_REQUEST);
			return ResponseEntity.badRequest().body(response);
		}
		
		if (clienteConsultado != null && response.getErros().isEmpty()) {
			clienteConsultado.setNomeCompleto(nomeNovo);
			Cliente clienteAlterado = clienteRepository.save(clienteConsultado);
			response.setDado(clienteAlterado);
			response.setStatus(HttpStatus.OK);
		}
		return ResponseEntity.ok(response);
	}

}
