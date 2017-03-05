/**
 * 
 */
package br.com.makersweb.api.web.service.core;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import br.com.makersweb.api.web.dto.UsuarioDTO;
import br.com.makersweb.api.web.entity.Autenticacao;
import br.com.makersweb.api.web.entity.Perfil;
import br.com.makersweb.api.web.entity.Usuario;
import br.com.makersweb.api.web.repository.IUsuarioRepository;
import br.com.makersweb.api.web.service.IPerfilService;
import br.com.makersweb.api.web.service.IUsuarioService;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
@Service
@EnableTransactionManagement
public class UsuarioService implements IUsuarioService {

	private static final Logger _logger = LogManager.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IPerfilService perfilService;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public List<Usuario> listar() throws BusinessException {
		_logger.info("Iniciou Método listar usuario...");

		List<Usuario> usuarios = usuarioRepository.findAll();

		if (CollectionUtils.isEmpty(usuarios)) {
			throw new BusinessException("Usuário não encontrado.");
		}

		return usuarios;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Usuario buscar(Long id) throws BusinessException {
		_logger.info("Iniciou Método buscar usuario...");

		if (ObjectUtils.isEmpty(id)) {
			throw new BusinessException("Usuário não encontrado.");
		}

		Usuario usuario = usuarioRepository.findOne(id);

		if (ObjectUtils.isEmpty(usuario)) {
			throw new BusinessException("Usuário não encontrado.");
		}

		return usuario;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Usuario salvar(Usuario usuario) throws BusinessException {
		_logger.info("Iniciou Método salvar usuario...");

		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario salvar(UsuarioDTO dto) throws BusinessException {
		_logger.info("Iniciou Método salvar usuario...");
		Usuario usuario = new Usuario();

		if (StringUtils.isEmpty(dto.getNome()) || StringUtils.isEmpty(dto.getEmail())
				|| StringUtils.isEmpty(dto.getSenha()) || ObjectUtils.isEmpty(dto.getStatus())
				|| ObjectUtils.isEmpty(dto.getPerfil())) {
			throw new BusinessException("Usuário não encontrado.");
		} else {
			Perfil perfil = perfilService.buscar(dto.getPerfil());
			usuario.setPerfil(perfil);

			usuario.setNome(dto.getNome());
			usuario.setEmail(dto.getEmail());
			usuario.setEnabled(true);
			usuario.setStatus(dto.getStatus());

			Autenticacao auth = new Autenticacao();
			auth.setCriado(LocalDateTime.now());
			auth.setCriadoPor(SecurityContextHolder.getContext().getAuthentication().getName());
			auth.setPassword(dto.getSenha());
			auth.setUsuario(usuario);
			usuario.setAutenticacao(auth);
		}

		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public Usuario alterar(Usuario usuario) throws BusinessException {
		_logger.info("Iniciou Método alterar usuario...");
		verificaExistencia(usuario);

		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deletar(Long id) throws BusinessException {
		_logger.info("Iniciou Método deletar usuario...");

		try {
			usuarioRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException("Usuário não encontrado.");
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void verificaExistencia(Usuario usuario) throws BusinessException {
		_logger.info("Iniciou Método verificaExistencia usuario...");
		buscar(usuario.getId());
	}

}
