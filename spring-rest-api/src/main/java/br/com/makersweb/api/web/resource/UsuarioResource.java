/**
 * 
 */
package br.com.makersweb.api.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.makersweb.api.web.entity.Usuario;
import br.com.makersweb.api.web.service.BaseService;
import br.com.makersweb.api.web.service.UsuarioService;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.url.base.api}/usuarios")
public class UsuarioResource extends BaseResource<Usuario> {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	protected BaseService<Usuario> getService() {
		return usuarioService;
	}

}
