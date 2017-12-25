package dev.paie.exception;

public class BulletinSalaireNotFoundException extends Exception {

	private static final long serialVersionUID = -3343158882559129336L;

	public BulletinSalaireNotFoundException() {
		super();
	}

	public BulletinSalaireNotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public BulletinSalaireNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public BulletinSalaireNotFoundException(String message) {
		super(message);
	}

	public BulletinSalaireNotFoundException(Throwable cause) {
		super(cause);
	}

}
