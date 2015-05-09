package net.ostis.confman.model.datastore.service.converter;


class StorageConverterFactory {
    
    private static final ConverterTypes DEFAULT_CONVERTER_TYPE = ConverterTypes.SC_MEMORY;
    private static ConverterTypes converterType = DEFAULT_CONVERTER_TYPE;

    private StorageConverterFactory() {
        super();
    }
    
    public static BaseConverter getConverter() {
        
        switch (converterType) {
            case XML:
                return new ConverterFromXMLStorage();         
            case SC_MEMORY:
                return new ConverterFromSCStorage();
            default:
                throw new IllegalStateException("We are not supported this!");
        }
        
    }
    
    public void setConverterType(ConverterTypes converterType) {
        StorageConverterFactory.converterType = converterType;
    }

}
