/**
 * 
 */
package br.com.makersweb.api.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author andersonaristides
 *
 */
@Entity
@Table(name = "tb_cliente")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cliente extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = 4293421179859625534L;

	@NotBlank(message = "O campo nome é obrigatório.")
	@Column(nullable = false)
	private String nome;

	@NotBlank(message = "O campo email é obrigatório.")
	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "O campo telefone é obrigatório.")
	@Column(nullable = false)
	private String telefone;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
