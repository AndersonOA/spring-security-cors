/**
 * 
 */
package br.com.makersweb.api.web.response;

/**
 *
 * @author anderson.aristides
 *
 */
public class DefaultResponse {

	private String message;
	private Boolean typeError;
	private Object data;

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the typeError
	 */
	public Boolean getTypeError() {
		return typeError;
	}

	/**
	 * @param typeError
	 *            the typeError to set
	 */
	public void setTypeError(Boolean typeError) {
		this.typeError = typeError;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
