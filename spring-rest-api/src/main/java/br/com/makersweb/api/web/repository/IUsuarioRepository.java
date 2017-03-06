/**
 * 
 */
package br.com.makersweb.api.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.makersweb.api.web.entity.Usuario;

/**
*
* @author anderson.aristides
*
*/
public interface IUsuarioRepository extends IBaseRepository<Usuario> {

	public final static String PESQUISAR_EMAIL = "SELECT U " + "FROM Usuario U WHERE U.email = :email";

	@Query(PESQUISAR_EMAIL)
	public Usuario pesquisaPorEmail(@Param("email") String email);

}
