package br.com.makersweb.api.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.makersweb.api.web.entity.RegraAcesso;

public interface IRegraAcessoRepository extends JpaRepository<RegraAcesso, Long>, JpaSpecificationExecutor<RegraAcesso> {

	List<RegraAcesso> findByNomeStartingWith(String nome);

}
