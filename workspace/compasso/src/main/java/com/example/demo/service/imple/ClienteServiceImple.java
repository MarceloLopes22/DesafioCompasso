package com.example.demo.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.basica.Cliente;
import com.example.demo.controller.response.Response;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;

@Service
public class ClienteServiceImple implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Response<Cliente> cadastrar(Cliente cliente) {
		Response<Cliente> response = new Response<>();
		validar(cliente, response);
		if (response.getErros().isEmpty()) {
			Cliente clienteSalvo = clienteRepository.save(cliente);
			response.setDado(clienteSalvo);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

	private void validar(Cliente cliente, Response<Cliente> response) {
		if (StringUtils.isEmpty(cliente.getNomeCompleto())) {
			response.getErros().put("nomeErro", "O nome do cliente deve ser preenchido.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		
		if (cliente.getSexo() == null) {
			response.getErros().put("sexoErro", "O sexo deve ser preenchido.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		
		if (cliente.getIdade() == 0) {
			response.getErros().put("idadeErro", "A idade deve ser preenchida.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		
		if (cliente.getDataNascimento() == null) {
			response.getErros().put("dataNascimentoErro", "A data de nascimento deve ser preenchida.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}

		if (cliente.getCidades().isEmpty()) {
			response.getErros().put("cidadesErro", "A cidade devem ser preenchida.");
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Response<Cliente> consultarClientePor(String nomeCompleto) {
		Response<Cliente> response = new Response<>();
		
		if (StringUtils.isEmpty(nomeCompleto)) {
			response.getErros().put("nomeErro", "O nome da cidade deve ser preenchida.");
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
			response.getErros().put("idErro", "O id deve ser preenchido.");
			response.setStatus(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		Cliente cliente = clienteRepository.findById(id).get();
		response.setDado(cliente);
		response.setStatus(HttpStatus.OK);
		return response;
	}

	@Override
	public void remover(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	@Override
	public Response<Cliente> alterarNomeCliente(String nomeAntigo, String nomeNovo) {
		Response<Cliente> response = new Response<>();
		
		if (nomeAntigo.isEmpty() || nomeNovo.isEmpty()) {
			response.getErros().put("nomeErro", "Os nomes antigo e novo devem ser informados.");
			response.setStatus(HttpStatus.BAD_REQUEST);
			return response;
		}
		
		response = this.consultarClientePor(nomeAntigo);
		Cliente clienteConsultado = response.getDado();
		
		if (clienteConsultado == null) {
			response.getErros().put("clienteErro", "Não existe nenhum cliente com esse nome = " + nomeAntigo);
			response.setStatus(HttpStatus.BAD_REQUEST);
		}
		
		if (clienteConsultado != null && response.getErros().isEmpty()) {
			clienteConsultado.setNomeCompleto(nomeNovo);
			Cliente clienteAlterado = clienteRepository.save(clienteConsultado);
			response.setDado(clienteAlterado);
			response.setStatus(HttpStatus.OK);
		}
		return response;
	}

}
