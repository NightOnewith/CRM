package net.yuanma.exception;

/**
 * 
 * <服务异常>
 * @author zj  
 * @version [V1.0.0,2017-4-5]
 *
 */
public class ServiceException extends Exception{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
