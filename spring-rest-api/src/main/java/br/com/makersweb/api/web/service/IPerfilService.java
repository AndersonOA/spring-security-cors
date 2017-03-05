/**
 * 
 */
package br.com.makersweb.api.web.service;

import java.util.List;

import br.com.makersweb.api.web.entity.Perfil;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IPerfilService {
	
	List<Perfil> listar() throws BusinessException;

	Perfil buscar(Long id) throws BusinessException;

	Perfil salvar(Perfil perfil) throws BusinessException;

	Perfil alterar(Perfil perfil) throws BusinessException;

	void deletar(Long id) throws BusinessException;

	void verificaExistencia(Perfil perfil) throws BusinessException;

}
