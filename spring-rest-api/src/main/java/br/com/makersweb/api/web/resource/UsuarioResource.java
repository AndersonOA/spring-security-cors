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

import br.com.makersweb.api.web.dto.UsuarioDTO;
import br.com.makersweb.api.web.entity.Usuario;
import br.com.makersweb.api.web.response.DefaultResponse;
import br.com.makersweb.api.web.service.IUsuarioService;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.url.base.api}")
public class UsuarioResource {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listar() {
		return ResponseEntity.ok(usuarioService.listar());
	}
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscar(@PathVariable("id") Long id) {
		return ResponseEntity.ok(usuarioService.buscar(id));
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody UsuarioDTO usuario) {
		DefaultResponse response= new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Usuário cadastrado com sucesso.");
		
		Usuario u = usuarioService.salvar(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();

		return ResponseEntity.created(uri).body(response);
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody Usuario usuario) {
		DefaultResponse response= new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Usuário atualizado com sucesso.");
		
		Usuario u = usuarioService.alterar(usuario);
		response.setData(u);
		
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		DefaultResponse response= new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Usuário removido com sucesso.");
		
		usuarioService.deletar(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

}
