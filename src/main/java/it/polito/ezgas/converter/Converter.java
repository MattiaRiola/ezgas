package it.polito.ezgas.converter;

public interface Converter<E, D> {
    E convertFromDto(D d);
    D convertToDto(E e);
}
