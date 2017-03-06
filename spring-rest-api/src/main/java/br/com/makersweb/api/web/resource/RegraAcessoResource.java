/**
 * 
 */
package br.com.makersweb.api.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.makersweb.api.web.entity.RegraAcesso;
import br.com.makersweb.api.web.service.BaseService;
import br.com.makersweb.api.web.service.RegraAcessoService;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.url.base.api}/regras")
public class RegraAcessoResource extends BaseResource<RegraAcesso> {

	@Autowired
	private RegraAcessoService regraService;

	@Override
	protected BaseService<RegraAcesso> getService() {
		return regraService;
	}

}
