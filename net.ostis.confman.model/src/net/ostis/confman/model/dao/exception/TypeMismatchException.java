package net.ostis.confman.model.dao.exception;

public class TypeMismatchException extends DAOException {

    private static final long serialVersionUID = -8034699463536223311L;

    public TypeMismatchException() {

        super();
    }

    public TypeMismatchException(String message) {

        super(message);
    }
}
