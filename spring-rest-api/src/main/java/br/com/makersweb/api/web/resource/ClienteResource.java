/**
 * 
 */
package br.com.makersweb.api.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.makersweb.api.web.entity.Cliente;
import br.com.makersweb.api.web.service.BaseService;
import br.com.makersweb.api.web.service.ClienteService;

/**
 *
 * @author anderson.aristides
 *
 */
@RestController
@RequestMapping("${makersweb.url.base.api}/clientes")
public class ClienteResource extends BaseResource<Cliente> {

	@Autowired
	private ClienteService clienteService;

	@Override
	protected BaseService<Cliente> getService() {
		return clienteService;
	}

}
