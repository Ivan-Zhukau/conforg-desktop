package net.ostis.confman.model.convert;


public class EntityConverter {

    private static EntityConverter instance;

    private EntityConverter() {

        super();
    }

    public static EntityConverter getInstance() {

        if (instance == null) {
            instance = new EntityConverter();
        }
        return instance;
    }
}
