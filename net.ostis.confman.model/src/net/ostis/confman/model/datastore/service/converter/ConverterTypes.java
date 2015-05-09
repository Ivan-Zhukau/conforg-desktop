package net.ostis.confman.model.datastore.service.converter;


enum ConverterTypes {
    
    XML("xml"),
    SC_MEMORY("sc_memory");

    private String converterType;

    ConverterTypes(String converterType) {
        this.converterType = converterType;
    }

    public String getConverterType() {
        return converterType;
    }

}
