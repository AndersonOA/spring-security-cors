/**
 * 
 */
package br.com.makersweb.api.web.service;

import java.util.List;

import br.com.makersweb.api.web.dto.UsuarioDTO;
import br.com.makersweb.api.web.entity.Usuario;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IUsuarioService {
	
	List<Usuario> listar() throws BusinessException;

	Usuario buscar(Long id) throws BusinessException;

	Usuario salvar(Usuario usuario) throws BusinessException;
	
	Usuario salvar(UsuarioDTO usuario) throws BusinessException;

	Usuario alterar(Usuario usuario) throws BusinessException;

	void deletar(Long id) throws BusinessException;

	void verificaExistencia(Usuario usuario) throws BusinessException;

}
