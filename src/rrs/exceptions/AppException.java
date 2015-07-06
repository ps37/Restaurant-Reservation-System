package rrs.exceptions;

public class AppException extends Exception {
	
	private static final long serialVersionUID = 2579842882480137022L;

	public AppException (String errorMsg) {
		super(errorMsg);
	}
	
	public AppException (String errorMsg, Throwable cause) {
		super(errorMsg, cause);
	}

}
