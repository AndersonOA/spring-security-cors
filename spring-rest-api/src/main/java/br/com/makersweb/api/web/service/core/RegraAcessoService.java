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

import br.com.makersweb.api.web.entity.RegraAcesso;
import br.com.makersweb.api.web.repository.IRegraAcessoRepository;
import br.com.makersweb.api.web.service.IRegraAcessoService;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
@Service
@EnableTransactionManagement
public class RegraAcessoService implements IRegraAcessoService {

	private static final Logger _logger = LogManager.getLogger(RegraAcessoService.class);

	@Autowired
	private IRegraAcessoRepository regraRepository;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<RegraAcesso> listar() throws BusinessException {
		_logger.info("Iniciou Método listar regra de acesso...");

		List<RegraAcesso> regras = regraRepository.findAll();

		if (CollectionUtils.isEmpty(regras)) {
			throw new BusinessException("Regra de Acesso não encontrado.");
		}

		return regras;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public RegraAcesso buscar(Long id) throws BusinessException {
		_logger.info("Iniciou Método buscar regra de acesso...");

		if (ObjectUtils.isEmpty(id)) {
			throw new BusinessException("Regra de Acesso não encontrado.");
		}

		RegraAcesso regra = regraRepository.findOne(id);

		if (ObjectUtils.isEmpty(regra)) {
			throw new BusinessException("Regra de Acesso não encontrado.");
		}

		return regra;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public RegraAcesso salvar(RegraAcesso regra) throws BusinessException {
		_logger.info("Iniciou Método salvar regra de acesso...");

		return regraRepository.save(regra);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public RegraAcesso alterar(RegraAcesso regra) throws BusinessException {
		_logger.info("Iniciou Método alterar regra de acesso...");
		verificaExistencia(regra);

		return regraRepository.save(regra);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deletar(Long id) throws BusinessException {
		_logger.info("Iniciou Método deletar regra de acesso...");

		try {
			regraRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException("Regra de Acesso não encontrado.");
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void verificaExistencia(RegraAcesso regra) throws BusinessException {
		_logger.info("Iniciou Método verificaExistencia regra de acesso...");
		buscar(regra.getId());
	}

}
