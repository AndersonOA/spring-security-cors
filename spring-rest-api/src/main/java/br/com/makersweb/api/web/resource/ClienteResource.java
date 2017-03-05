/**
 * 
 */
package br.com.makersweb.api.web.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.makersweb.api.web.entity.Cliente;
import br.com.makersweb.api.web.response.DefaultResponse;
import br.com.makersweb.api.web.service.IClienteService;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.url.base.api}")
public class ClienteResource {

	@Autowired
	private IClienteService clienteService;

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscar(@PathVariable("id") Long id) {
		return ResponseEntity.ok(clienteService.buscar(id));
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {
		DefaultResponse response = new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Cliente cadastrado com sucesso.");

		Cliente c = clienteService.salvar(cliente);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();

		return ResponseEntity.created(uri).body(response);
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody Cliente cliente) {
		DefaultResponse response = new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Cliente atualizado com sucesso.");

		Cliente c = clienteService.alterar(cliente);
		response.setData(c);

		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		DefaultResponse response = new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Cliente removido com sucesso.");

		clienteService.deletar(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

}
