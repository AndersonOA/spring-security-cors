package br.com.makersweb.api.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.makersweb.api.web.entity.Autenticacao;

public interface IAutenticacaoRepository  extends JpaRepository<Autenticacao, Long>, JpaSpecificationExecutor<Autenticacao>   {

}
