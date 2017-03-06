/**
 * 
 */
package br.com.makersweb.api.web.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.makersweb.api.web.entity.Cliente;
import br.com.makersweb.api.web.repository.IBaseRepository;
import br.com.makersweb.api.web.repository.IClienteRepository;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
@Service
@Transactional
public class ClienteService extends BaseService<Cliente> {

	@Autowired
	private IClienteRepository clienteRepository;

	public ClienteService() {
		super(Cliente.class);
	}

	@Override
	protected IBaseRepository<Cliente> getRepository() {
		return clienteRepository;
	}


	@Override
	protected void verificaExistencia(Cliente entity) throws BusinessException {
		buscar(entity.getId());
	}

}
