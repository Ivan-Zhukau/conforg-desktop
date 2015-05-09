package net.ostis.confman.model.dao.exception;


public class DAOException extends Exception {

    private static final long serialVersionUID = -1456783054726755919L;

    public DAOException() {

        super();
    }

    public DAOException(String message, Throwable cause) {

        super(message, cause);
    }

    public DAOException(String message) {

        super(message);
    }

}
