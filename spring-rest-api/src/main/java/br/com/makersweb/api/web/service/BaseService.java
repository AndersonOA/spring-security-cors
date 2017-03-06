/**
 * 
 */
package br.com.makersweb.api.web.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import br.com.makersweb.api.web.entity.DefaultEntity;
import br.com.makersweb.api.web.message.MakersWebMessage;
import br.com.makersweb.api.web.repository.IBaseRepository;
import br.com.makersweb.api.web.service.exception.BusinessException;

/**
 *
 * @author anderson.aristides
 *
 */
public abstract class BaseService<T extends DefaultEntity> {

	protected static final int PAGE_SIZE = 15;

	@Autowired
	private EntityManager em;

	@Autowired
	private MessageSource messageSource;

	private Class<T> currentClass;

	/**
	 * @param currentClass
	 */
	public BaseService(Class<T> currentClass) {
		super();
		this.currentClass = currentClass;
	}

	public void deletar(Long id) throws BusinessException {
		T object = buscar(id);
		
		checkWriteAccess(object);
		getRepository().delete(id);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public T buscar(Long id) throws BusinessException {

		if (ObjectUtils.isEmpty(id)) {
			throw new BusinessException(
					new MakersWebMessage(messageSource).message("br.com.makersweb.text.campos.obrigatorio"));
		}

		T result = getRepository().findOne(id);

		if (ObjectUtils.isEmpty(result)) {
			throw new BusinessException(new MakersWebMessage(messageSource)
					.message("br.com.makersweb.text.nao.encontrado", currentClass.getCanonicalName()));
		}

		return result;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<T> listar() throws BusinessException {
		List<T> results = getRepository().findAll();
		
		if (CollectionUtils.isEmpty(results)) {
			throw new BusinessException(new MakersWebMessage(messageSource)
					.message("br.com.makersweb.text.nao.encontrado", currentClass.getCanonicalName()));
		}
		
		return results;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<T> listar(Integer page) throws BusinessException {
		PageRequest request = page >= 0 ? new PageRequest(page, PAGE_SIZE) : new PageRequest(0, Integer.MAX_VALUE);
		
		Page<T> results = getRepository().findAll(request);
		
		if (null == results) {
			throw new BusinessException(new MakersWebMessage(messageSource)
					.message("br.com.makersweb.text.nao.encontrado", currentClass.getCanonicalName()));
		}
		
		return getRepository().findAll(request);
	}

	public T salvar(T object) throws BusinessException {
		checkWriteAccess(object);
		prepareToSave(object);

		T saved = getRepository().save(object);
		return saved;
	}
	
	public T alterar(T object) throws BusinessException {
		verificaExistencia(object);
		
		T updated = getRepository().save(object);
		
		return updated;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Page<T> filtrar(Integer start, Integer length, String filter, String[] filterColumns,
			String[] orderColumns) {
		Query query = createQuery(filter, filterColumns, orderColumns, false);
		query.setMaxResults(length);
		query.setFirstResult(start);

		List<T> data = query.getResultList();

		Query countQuery = createQuery(filter, filterColumns, null, true);
		Long count = (Long) countQuery.getSingleResult();

		Integer currentPage = start / length;
		Pageable pageable = new PageRequest(currentPage, length);
		Page<T> page = new PageImpl<T>(data, pageable, count);

		return page;
	}

	protected Query createQuery(String filter, String[] filterColumns, String[] orderColumns, boolean count) {
		StringBuilder queryStr = new StringBuilder();

		if (count) {
			queryStr.append("SELECT COUNT(*) ");
		}

		queryStr.append("FROM " + currentClass.getCanonicalName()).append(" WHERE ").append(getDefaultFilter());
		if (StringUtils.isNotBlank(filter) && filterColumns != null && filterColumns.length > 0) {
			queryStr.append(" AND ( ");

			for (int i = 0; i < filterColumns.length; i++) {
				queryStr.append(i > 0 ? " OR " : "").append(filterColumns[i]).append(" LIKE ? ");
			}

			queryStr.append(" )");
		}

		if (orderColumns != null && orderColumns.length > 0) {
			queryStr.append(" ORDER BY ").append(ArrayUtils.toString(orderColumns).replaceAll("[\\{\\}]", ""));
		}

		Query query = em.createQuery(queryStr.toString());
		if (StringUtils.isNotBlank(filter)) {
			for (int i = 0; i < filterColumns.length; i++) {
				query.setParameter(i + 1, filter + "%");
			}
		}

		return query;
	}

	protected abstract IBaseRepository<T> getRepository();

	protected abstract void verificaExistencia(T entity) throws BusinessException;

	protected void checkWriteAccess(T object) {
	}

	protected void prepareToSave(T object) {
	}

	protected String getDefaultFilter() {
		return " 1 = 1 ";
	}

}
