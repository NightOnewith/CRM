package net.yuanma.exception;

/**
 * 
 * <数据访问异常>
 * @author zj  
 * @version [V1.0.0,2017-4-5]
 *
 */
public class DaoException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
