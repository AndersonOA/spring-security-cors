/**
 * 
 */
package br.com.makersweb.api.web.service.core;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.makersweb.api.web.entity.Perfil;
import br.com.makersweb.api.web.repository.IPerfilRepository;
import br.com.makersweb.api.web.service.IPerfilService;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
@Service
@EnableTransactionManagement
public class PerfilService implements IPerfilService {
	
	private static final Logger _logger = LogManager.getLogger(PerfilService.class);
	
	@Autowired
	private IPerfilRepository perfilRepository;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Perfil> listar() throws BusinessException {
		_logger.info("Iniciou Método listar perfil...");
		
		List<Perfil> perfis = perfilRepository.findAll();
		
		if (CollectionUtils.isEmpty(perfis)) {
			throw new BusinessException("Perfil não encontrado.");
		}
		
		return perfis;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Perfil buscar(Long id) throws BusinessException {
		_logger.info("Iniciou Método buscar perfil...");
		
		if (ObjectUtils.isEmpty(id)) {
			throw new BusinessException("Perfil não encontrado.");
		}
		
		Perfil perfil = perfilRepository.findOne(id);
		
		if (ObjectUtils.isEmpty(perfil)) {
			throw new BusinessException("Perfil não encontrado.");
		}
		
		return perfil;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Perfil salvar(Perfil perfil) throws BusinessException {
		_logger.info("Iniciou Método salvar perfil...");
		
		return perfilRepository.save(perfil);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Perfil alterar(Perfil perfil) throws BusinessException {
		_logger.info("Iniciou Método alterar perfil...");
		verificaExistencia(perfil);
		
		return perfilRepository.save(perfil);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deletar(Long id) throws BusinessException {
		_logger.info("Iniciou Método deletar perfil...");

		try {
			perfilRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException("Perfil não encontrado.");
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void verificaExistencia(Perfil perfil) throws BusinessException {
		_logger.info("Iniciou Método verificaExistencia perfil...");
		buscar(perfil.getId());
	}

}
