/**
 * 
 */
package br.com.makersweb.api.web.service;

import java.util.List;

import br.com.makersweb.api.web.entity.Cliente;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IClienteService {

	List<Cliente> listar() throws BusinessException;

	Cliente buscar(Long id) throws BusinessException;

	Cliente salvar(Cliente cliente) throws BusinessException;

	Cliente alterar(Cliente cliente) throws BusinessException;

	void deletar(Long id) throws BusinessException;

	void verificaExistencia(Cliente cliente) throws BusinessException;

}
