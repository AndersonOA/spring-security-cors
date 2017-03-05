/**
 * 
 */
package br.com.makersweb.api.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.makersweb.api.web.entity.Cliente;

/**
 *
 * @author anderson.aristides
 *
 */
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
