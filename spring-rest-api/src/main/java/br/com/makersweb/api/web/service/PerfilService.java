/**
 * 
 */
package br.com.makersweb.api.web.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.makersweb.api.web.entity.Perfil;
import br.com.makersweb.api.web.repository.IBaseRepository;
import br.com.makersweb.api.web.repository.IPerfilRepository;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
@Service
@Transactional
public class PerfilService extends BaseService<Perfil> {

	@Autowired
	private IPerfilRepository perfilRepository;

	public PerfilService() {
		super(Perfil.class);
	}

	@Override
	protected IBaseRepository<Perfil> getRepository() {
		return perfilRepository;
	}


	@Override
	protected void verificaExistencia(Perfil entity) throws BusinessException {
		buscar(entity.getId());
	}

}
