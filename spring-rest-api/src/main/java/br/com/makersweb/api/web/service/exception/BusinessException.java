/**
 * 
 */
package br.com.makersweb.api.web.service.exception;

/**
 *
 * @author anderson.aristides
 *
 */
public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1231483272479216565L;

	/**
	 * 
	 */
	public BusinessException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public BusinessException(String message) {
		super(message);
	}

	/**
	 * @param message
	 */
	public BusinessException(String message, Boolean error, String typeError) {
		super(message);
	}

}
