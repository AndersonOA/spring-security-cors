/**
 * 
 */
package br.com.makersweb.api.web.service;

import java.util.List;

import br.com.makersweb.api.web.entity.RegraAcesso;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IRegraAcessoService {
	
	List<RegraAcesso> listar() throws BusinessException;

	RegraAcesso buscar(Long id) throws BusinessException;

	RegraAcesso salvar(RegraAcesso regra) throws BusinessException;

	RegraAcesso alterar(RegraAcesso regra) throws BusinessException;

	void deletar(Long id) throws BusinessException;

	void verificaExistencia(RegraAcesso regra) throws BusinessException;

}
