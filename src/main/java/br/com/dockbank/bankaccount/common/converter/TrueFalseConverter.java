package br.com.dockbank.bankaccount.common.converter;

import javax.persistence.AttributeConverter;

public class TrueFalseConverter implements AttributeConverter<Boolean, Character> {

    @Override
    public Character convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean ? 'T' : 'F';
    }

    @Override
    public Boolean convertToEntityAttribute(Character character) {
        return character == 'T';
    }
}
