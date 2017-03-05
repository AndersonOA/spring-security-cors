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

import br.com.makersweb.api.web.entity.RegraAcesso;
import br.com.makersweb.api.web.response.DefaultResponse;
import br.com.makersweb.api.web.service.IRegraAcessoService;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.url.base.api}")
public class RegraAcessoResource {
	
	@Autowired
	private IRegraAcessoService regraService;
	
	@RequestMapping(value = "/regra-acesso", method = RequestMethod.GET)
	public ResponseEntity<List<RegraAcesso>> listar() {
		return ResponseEntity.ok(regraService.listar());
	}
	
	@RequestMapping(value = "/regra-acesso/{id}", method = RequestMethod.GET)
	public ResponseEntity<RegraAcesso> buscar(@PathVariable("id") Long id) {
		return ResponseEntity.ok(regraService.buscar(id));
	}
	
	@RequestMapping(value = "/regra-acesso", method = RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody RegraAcesso regra) {
		DefaultResponse response= new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Regra de Acesso cadastrada com sucesso.");
		
		RegraAcesso r = regraService.salvar(regra);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(r.getId()).toUri();

		return ResponseEntity.created(uri).body(response);
	}
	
	@RequestMapping(value = "/regra-acesso", method = RequestMethod.PUT)
	public ResponseEntity<?> alterar(@RequestBody RegraAcesso regra) {
		DefaultResponse response= new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Regra de Acesso atualizada com sucesso.");
		
		RegraAcesso r = regraService.alterar(regra);
		response.setData(r);
		
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/regra-acesso/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) {
		DefaultResponse response= new DefaultResponse();
		response.setTypeError(false);
		response.setMessage("Regra de Acesso removida com sucesso.");
		
		regraService.deletar(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
	}

}
