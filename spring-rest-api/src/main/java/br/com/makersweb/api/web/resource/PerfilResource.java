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

import br.com.makersweb.api.web.entity.Perfil;
import br.com.makersweb.api.web.response.DefaultResponse;
import br.com.makersweb.api.web.service.IPerfilService;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.url.base.api}")
public class PerfilResource {

	@Autowired
	private IPerfilService perfilService;

	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	public ResponseEntity<List<Perfil>> listar() {
		return ResponseEntity.ok(perfilService.listar());
	}

	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET)
	public ResponseEntity<Perfil> buscar(@PathVariable("id") Long id) {
		return ResponseEntity.ok(perfilService.buscar(id));
	}

	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody Perfil perfil) {
		DefaultResponse response = new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Usuário cadastrado com sucesso.");

		Perfil p = perfilService.salvar(perfil);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();

		return ResponseEntity.created(uri).body(response);
	}

	@RequestMapping(value = "/perfil", method = RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody Perfil perfil) {
		DefaultResponse response = new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Usuário atualizado com sucesso.");

		Perfil p = perfilService.alterar(perfil);
		response.setData(p);

		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		DefaultResponse response = new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Perfil removido com sucesso.");

		perfilService.deletar(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

}
