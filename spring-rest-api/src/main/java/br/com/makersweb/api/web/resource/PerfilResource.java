/**
 * 
 */
package br.com.makersweb.api.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.makersweb.api.web.entity.Perfil;
import br.com.makersweb.api.web.service.BaseService;
import br.com.makersweb.api.web.service.PerfilService;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.url.base.api}/perfis")
public class PerfilResource extends BaseResource<Perfil> {

	@Autowired
	private PerfilService perfilService;

	@Override
	protected BaseService<Perfil> getService() {
		return perfilService;
	}

}
