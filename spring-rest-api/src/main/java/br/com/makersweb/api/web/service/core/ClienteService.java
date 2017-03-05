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

import br.com.makersweb.api.web.entity.Cliente;
import br.com.makersweb.api.web.repository.IClienteRepository;
import br.com.makersweb.api.web.service.IClienteService;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
@Service
@EnableTransactionManagement
public class ClienteService implements IClienteService {

	private static final Logger _logger = LogManager.getLogger(ClienteService.class);

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Cliente> listar() throws BusinessException {
		_logger.info("Iniciou Método listar cliente...");

		List<Cliente> clientes = clienteRepository.findAll();

		if (CollectionUtils.isEmpty(clientes)) {
			throw new BusinessException("Cliente não encontrado.");
		}

		return clientes;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Cliente buscar(Long id) throws BusinessException {
		_logger.info("Iniciou Método buscar cliente...");

		if (ObjectUtils.isEmpty(id)) {
			throw new BusinessException("Cliente não encontrado.");
		}

		Cliente cliente = clienteRepository.findOne(id);

		if (ObjectUtils.isEmpty(cliente)) {
			throw new BusinessException("Cliente não encontrado.");
		}

		return cliente;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Cliente salvar(Cliente cliente) throws BusinessException {
		_logger.info("Iniciou Método salvar cliente...");

		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Cliente alterar(Cliente cliente) throws BusinessException {
		_logger.info("Iniciou Método alterar cliente...");
		verificaExistencia(cliente);

		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deletar(Long id) throws BusinessException {
		_logger.info("Iniciou Método deletar cliente...");

		try {
			clienteRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException("Cliente não encontrado.");
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void verificaExistencia(Cliente cliente) throws BusinessException {
		_logger.info("Iniciou Método verificaExistencia cliente...");
		buscar(cliente.getId());
	}

}
