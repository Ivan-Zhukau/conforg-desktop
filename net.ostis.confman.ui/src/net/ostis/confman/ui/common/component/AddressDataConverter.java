package net.ostis.confman.ui.common.component;

import net.ostis.confman.services.common.model.Address;

public class AddressDataConverter implements DataConverter {

    private static final int COUNTRY_POS      = 0;

    private static final int CITY_POS         = 1;

    private static final int STREET_POS       = 2;

    private static final int HOUSE_NUMBER_POS = 3;

    @Override
    public String convert(final Object val) {

        final Address address = (Address) val;
        final StringBuilder builder = new StringBuilder();
        convertAddressFields(address, builder);
        return builder.toString();
    }

    private void convertAddressFields(final Address address,
            final StringBuilder builder) {

        builder.append(
                address.getCountry() == null ? "_" : address.getCountry())
                .append(", ")
                .append(address.getCity() == null ? "_" : address.getCity())
                .append(", ")
                .append(address.getStreet() == null ? "_" : address.getStreet())
                .append(", ")
                .append(address.getHouseNumber() == null ? "_" : address
                        .getHouseNumber());
    }

    @Override
    public Object convert(final String val) {

        return parseAddress(val);
    }

    private Object parseAddress(final String val) {

        final String[] parts = val.split(", ");
        if (parts.length != 4) {
            throw new IllegalArgumentException(
                    "Incorrect address, input it in format \"COUNTRY, CITY, STREET, HOUSE_NUMBER\"");
        }
        return createAddress(parts);
    }

    private Address createAddress(final String[] parts) {

        final Address address = new Address();
        address.setCountry(parts[COUNTRY_POS]);
        address.setCity(parts[CITY_POS]);
        address.setStreet(parts[STREET_POS]);
        address.setHouseNumber(parts[HOUSE_NUMBER_POS]);
        return address;
    }
}
