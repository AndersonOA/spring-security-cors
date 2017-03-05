package br.com.makersweb.api.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.makersweb.api.web.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

	public final static String PESQUISAR_EMAIL = "SELECT U " + "FROM Usuario U WHERE U.email = :email";

	@Query(PESQUISAR_EMAIL)
	public Usuario pesquisaPorEmail(@Param("email") String email);

}
